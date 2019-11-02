package com.mage.crm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mage.crm.dao.ModuleDao;
import com.mage.crm.dao.PermissionDao;
import com.mage.crm.dto.ModuleDto;
import com.mage.crm.query.ModuleQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.vo.Module;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ModuleService {
    @Resource
    private ModuleDao moduleDao;
    @Resource
    private PermissionDao permissionDao;

    public List<ModuleDto> queryAllsModuleDtos(Integer rid) {
        List<ModuleDto> moduleDtos = moduleDao.queryAllsModuleDtos();
        List<Integer> moduleIds = permissionDao.queryPermissionModuleIdsByRid(rid);
        if(moduleIds.size()>0&&null!=moduleIds){
            for(ModuleDto moduleDto : moduleDtos){
                Integer mid = moduleDto.getId();
                if(moduleIds.contains(mid)){
                    moduleDto.setChecked(true);
                }
            }
        }
        return moduleDtos;
    }

    public Map<String,Object> queryModulesByParams(ModuleQuery moduleQuery) {
        PageHelper.startPage(moduleQuery.getPage(),moduleQuery.getRows());
        List<Module> list = moduleDao.queryModulesByParams(moduleQuery);
        PageInfo<Module> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    public void insert(Module module) {
        /**
         * 1.参数校验
         *    模块名称非空
         *    层级 非空
         *    模块权限值 非空
         * 2.权限值不能重复
         * 3.每一层  模块名称不能重复
         * 4.非根级菜单 上级菜单必须存在
         * 5.设置额外字段
         *     isValid
         *     createDate
         *     updateDate
         * 6.执行添加
         */
        checkModuleParams(module.getModuleName(),module.getGrade(),module.getOptValue());
        AssertUtil.isTrue(null!=moduleDao.queryModuleByOptValue(module.getOptValue()),"权限值不能重复");
        AssertUtil.isTrue(null!=moduleDao.queryModuleByGradeAndModuleName(module.getGrade(),module.getModuleName()),"该层级下模块模块名已存在");
        if(module.getGrade()!=0){
            AssertUtil.isTrue(null==moduleDao.queryModuleByPid(module.getParentId()),"非根级菜单上级菜单必须存在!");
        }
        module.setIsValid(1);
        module.setCreateDate(new Date());
        module.setUpdateDate(new Date());
        AssertUtil.isTrue(moduleDao.insert(module)<1,"模块添加失败！");
    }

    private void checkModuleParams(String moduleName,Integer grade,String optValue) {
        AssertUtil.isTrue(StringUtils.isBlank(moduleName), "模块名非空!");
        AssertUtil.isTrue(null == grade, "层级值非法!");
        Boolean flag = (grade != 0 && grade != 1 && grade != 2);
        AssertUtil.isTrue(flag, "层级值非法!");
        AssertUtil.isTrue(StringUtils.isBlank(optValue), "权限值非空!");
    }

    public List<Module> queryModulesByGrade(Integer grade) {
        return moduleDao.queryModulesByGrade(grade);
    }

    public void update(Module module) {
        /**
         * 1.参数校验
         *    模块名称非空
         *    层级 非空
         *    模块权限值 非空
         *    id 记录必须存在
         * 2.权限值不能重复
         * 3.每一层  模块名称不能重复
         * 4.非根级菜单 上级菜单必须存在
         * 5.设置额外字段
         *     updateDate
         * 6.执行更新
         */
        checkModuleParams(module.getModuleName(),module.getGrade(),module.getOptValue(),module.getId());
        Module m = moduleDao.queryModuleByOptValue(module.getOptValue());
        AssertUtil.isTrue(null!=m&&!m.getId().equals(module.getId()),"权限值不能重复!");
        m = moduleDao.queryModuleByGradeAndModuleName(module.getGrade(),module.getModuleName());
        AssertUtil.isTrue(null!=m&&!m.getId().equals(module.getId()),"该层级下模块名已存在");
        if(module.getGrade()!=0){
            AssertUtil.isTrue(null==moduleDao.queryModuleByPid(module.getParentId()),"非根级菜单上级菜单必须存在!");
        }
        module.setUpdateDate(new Date());
        AssertUtil.isTrue(moduleDao.update(module)<1,"更新模块失败！");
    }
    private void checkModuleParams(String moduleName, Integer grade,
                                   String optValue, Integer id) {
        checkModuleParams(moduleName, grade, optValue);
        AssertUtil.isTrue(null == id || null == moduleDao.queryModuleById(id), "待更新模块不存在!");
    }

    public void delete(Integer id) {
        AssertUtil.isTrue(null==id||null==moduleDao.queryModuleById(id),"要删除的模块不存在了！");
        List<Integer> list = new ArrayList<>();
        list = getSubModuleIds(id,list);
        AssertUtil.isTrue(moduleDao.delete(list)<list.size(),"删除模块失败！");
    }

    private List<Integer> getSubModuleIds(Integer id, List<Integer> list) {
        Module module = moduleDao.queryModuleById(id);
        if(null!=module){
            list.add(module.getId());
            List<Module> modules = moduleDao.querySubModuleaByPid(module.getId());
            if(null!=modules&&modules.size()>0){
                for(Module module1 : modules){
                    list = getSubModuleIds(module.getId(),list);
                }
            }
        }
        return list;
    }
}
