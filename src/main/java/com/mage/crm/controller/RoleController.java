package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import com.mage.crm.model.MessageModel;
import com.mage.crm.query.RoleQuery;
import com.mage.crm.service.RoleService;
import com.mage.crm.vo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController{
    @Resource
    private RoleService roleService;

    @RequestMapping("index")
    public String index(){
        return "user";
    }

    @RequestMapping("queryRolesByParams")
    @ResponseBody
    public Map<String,Object> queryRolesByParams(RoleQuery roleQuery){
        return roleService.queryRolesByParams(roleQuery);
    }

    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(Role role){
        roleService.insert(role);
        return createMessageModel("添加用户信息成功！");
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(Role role){
        roleService.update(role);
        return createMessageModel("修改用户信息成功！");
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer id){
        roleService.delete(id);
        return createMessageModel("删除用户信息成功！");
    }

    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Role> queryAllRoles(){
        return roleService.queryAllRoles();
    }


    @RequestMapping("addPermission")
    @ResponseBody
    public MessageModel addPermission(Integer rid,Integer[] moduleIds){
        roleService.addPermission(rid,moduleIds);
        return createMessageModel("角色授权成功");
    }
}
