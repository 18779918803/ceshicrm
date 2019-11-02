package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mage.crm.dao.PermissionDao;
import com.mage.crm.dao.UserDao;
import com.mage.crm.dao.UserRoleDao;
import com.mage.crm.dto.UserDto;
import com.mage.crm.model.UserModel;
import com.mage.crm.query.UserQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.util.Base64Util;
import com.mage.crm.util.Md5Util;
import com.mage.crm.vo.User;
import com.mage.crm.vo.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private PermissionDao permissionDao;
    @Resource
    private HttpSession session;
    /**
     * 登录
     * @param userName
     * @param userPwd
     * @return
     */
    public UserModel login(String userName, String userPwd){
        User user = userDao.queryUserByName(userName);
        AssertUtil.isTrue(null==user,"用户不存在！");
        userPwd = Md5Util.encode(userPwd);
        AssertUtil.isTrue(!userPwd.equals(user.getUserPwd()),"用户名或密码错误！");
        AssertUtil.isTrue("0".equals(user.getIsValid()),"用户已经注销！");
        List<String> permissions = permissionDao.queryPermissionByUserId(user.getId()+"");
        if(null!=permissions&&permissions.size()>0){
            session.setAttribute("userPermission",permissions);
        }
        return createUserModel(user);
    }

    public UserModel createUserModel(User user){
        UserModel userModel = new UserModel();
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        userModel.setId(Base64Util.encode(user.getId()));
        return userModel;
    }

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    public void updatePwd(String id, String oldPassword, String newPassword, String confirmPassword) {
        AssertUtil.isTrue(StringUtils.isBlank(id),"用户不存在！");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码不能为空！");
        AssertUtil.isTrue(oldPassword.equals(newPassword),"新密码不能和原密码一样！");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword),"新密码与确认的密码不一致！");
        User user = userDao.queryUserById(Base64Util.decode(id));
        AssertUtil.isTrue(null==user,"用户不存在了！");
        AssertUtil.isTrue("0".equals(user.getIsValid()),"用户已经被注销了！");
        AssertUtil.isTrue(!Md5Util.encode(oldPassword).equals(user.getUserPwd()),"原密码输入错误！");
        AssertUtil.isTrue(userDao.updatePwd(user.getId(),Md5Util.encode(newPassword))<1,"密码修改失败！");
    }

    public User queryUserById(String id) {
        return userDao.queryUserById(id);
    }

    public List<User> queryAllCustomerManager() {
        return userDao.queryAllCustomerManager();
    }


    public Map<String,Object> queryUsersByParams(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPage(),userQuery.getRows());
        List<UserDto> list = userDao.queryUsersByParams(userQuery);
        if(list!=null&&list.size()>0){
            for(UserDto userDto : list){
                String roleIdsStr = userDto.getRoleIdsStr();
                if(!StringUtils.isEmpty(roleIdsStr)){
                    String[] roleIdArray = roleIdsStr.split(",");
                    for(int i= 0;i<roleIdArray.length;i++){
                        List<Integer> roleIds = userDto.getRoleIds();
                        roleIds.add(Integer.parseInt(roleIdArray[i]));
                    }
                }
            }
        }
        PageInfo<UserDto> userPageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("rows",userPageInfo.getList());
        map.put("total",userPageInfo.getTotal());
        return map;
    }

    public void insert(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getTrueName()),"真实姓名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getPhone()),"手机号码不能为空");
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setIsValid(1);
        user.setUserPwd(Md5Util.encode("123456"));
        User u = userDao.queryUserByName(user.getUserName());
        AssertUtil.isTrue(u!=null&&u.getUserName().equals(user.getUserName()),"不能有相同用户名");
        AssertUtil.isTrue(userDao.insert(user)<1,"添加用户失败！");
        List<Integer> roleIds = user.getRoleIds();
        if(roleIds!=null&&roleIds.size()>0){
            relateRoles(roleIds,Integer.parseInt(user.getId()));
        }

    }

    private void relateRoles(List<Integer> roleIds, int userId) {
        List<UserRole> roleList=new ArrayList<UserRole>();
        for (Integer roleId:roleIds){
            UserRole userRole = new UserRole();
            userRole.setIsValid(1);
            userRole.setCreateDate(new Date());
            userRole.setUpdateDate(new Date());
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            roleList.add(userRole);
        }
        AssertUtil.isTrue(userRoleDao.insertBacth(roleList)<1,"用户角色添加失败");
    }

    public void update(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getTrueName()),"真实姓名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getPhone()),"手机号码不能为空");
        user.setUpdateDate(new Date());
        User u = userDao.queryUserByName(user.getUserName());
        AssertUtil.isTrue(u!=null&&!u.getId().equals(user.getId()),"不能有相同用户名");
        AssertUtil.isTrue(userDao.update(user)<1,"用户修改失败");
        List<Integer> roleIds = user.getRoleIds();
        if(null!=roleIds&&roleIds.size()>0){
            //先删除，在插入
            //先查询，原来是否有用户角色
            int count = userRoleDao.queryRoleCountsByUserId(user.getId());
            if(count>0){
                AssertUtil.isTrue(userRoleDao.deleteRolesByUserId(user.getId())<1,"用户更新失败");
            }
            //插入
            relateRoles(roleIds,Integer.parseInt(user.getId()));
        }
    }

    public void delete(Integer id) {
        AssertUtil.isTrue(userDao.delete(id)<1,"删除用户失败！");
        int count = userRoleDao.queryRoleCountsByUserId(String.valueOf(id));
        if(count>0){
            AssertUtil.isTrue(userRoleDao.deleteRolesByUserId(String.valueOf(id))<count,"用户角色删除失败");
        }
    }
}
