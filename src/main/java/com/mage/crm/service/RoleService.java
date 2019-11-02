package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mage.crm.base.CrmConstant;
import com.mage.crm.dao.ModuleDao;
import com.mage.crm.dao.PermissionDao;
import com.mage.crm.dao.RoleDao;
import com.mage.crm.query.RoleQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.vo.Module;
import com.mage.crm.vo.Permission;
import com.mage.crm.vo.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RoleService {
    @Resource
    private RoleDao roleDao;
    @Resource
    private PermissionDao permissionDao;
    @Resource
    private ModuleDao moduleDao;

    public Map<String,Object> queryRolesByParams(RoleQuery roleQuery) {
        PageHelper.startPage(roleQuery.getPage(),roleQuery.getRows());
        List<Role> list = roleDao.queryRolesByParams(roleQuery);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    public void insert(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"角色名为空！");
        AssertUtil.isTrue(null!=roleDao.queryRoleByRoleName(role.getRoleName()), "角色已存在!");
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        role.setIsValid(1);
        AssertUtil.isTrue(roleDao.insert(role)<1,"角色记录添加失败");
    }

    public void update(Role role) {
        /*
		 * 1.参数校验
		 *    角色名非空
		 *    id 记录必须存在
		 * 2.角色名重复性校验，角色名称已存在
		 * 3.额外字段值设置
		 *    updateDate
		 * 4.执行更新
		 */
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"角色名非空");
        AssertUtil.isTrue(null==role.getId()||null==roleDao.queryRoleById(role.getId()),"更新角色不存在了！");
        AssertUtil.isTrue(null!=roleDao.queryRoleByRoleName(role.getRoleName()),"角色名已存在");
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleDao.update(role)<1,"角色记录修改失败");
    }

    public void delete(Integer id) {
        AssertUtil.isTrue(roleDao.delete(id)<1,"角色记录删除失败");
    }

    public List<Role> queryAllRoles() {
       return roleDao.queryAllRoles();
    }

    public void addPermission(Integer rid, Integer[] moduleIds) {
        /**
         * 1.参数合法性校验
         *    rid 角色记录必须存在
         *    moduleIds  可空
         * 2.删除原始权限
         *     查询原始权限
         *         原始权限存在  执行删除操作
         * 3. 执行批量添加
         *     根据moduleId  查询每个模块  权限值
         *     List<Permission>
         */
        AssertUtil.isTrue(null==rid||null==roleDao.queryRoleById(rid+""),"待授权的角色不存在！");
        int count = permissionDao.queryPermissionCountByRid(rid);
        if(count>0){
            AssertUtil.isTrue(permissionDao.deletePermissionByRid(rid)<count, CrmConstant.OPS_FAILED_MSG);
        }
        List<Permission> permissions=null;
        if(null!=moduleIds&&moduleIds.length>0){
            permissions = new ArrayList<Permission>();
            Module module = null;
            for(Integer moduleId : moduleIds){
                module = moduleDao.queryModuleById(moduleId);
                Permission permission = new Permission();
                if(null!=module){
                    permission.setAclValue(module.getOptValue());
                }
                permission.setCreateDate(new Date());
                permission.setUpdateDate(new Date());
                permission.setModuleId(moduleId);
                permission.setRoleId(rid);
                permission.setIsValid(1);
                permissions.add(permission);
            }
            AssertUtil.isTrue(permissionDao.insertBatch(permissions)<moduleIds.length,CrmConstant.OPS_FAILED_MSG);
        }
    }
}
