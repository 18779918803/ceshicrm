package com.mage.crm.controller;

import com.mage.crm.base.CrmConstant;
import com.mage.crm.base.exceptions.ParamsException;
import com.mage.crm.model.MessageModel;
import com.mage.crm.model.UserModel;
import com.mage.crm.query.UserQuery;
import com.mage.crm.service.UserService;
import com.mage.crm.util.CookieUtil;
import com.mage.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends IndexController{
    @Resource
    private UserService userService;

    @RequestMapping("userLogin")
    @ResponseBody
    public MessageModel login(String userName, String userPwd){
        MessageModel messageModel = new MessageModel();
            UserModel userModel = userService.login(userName,userPwd);
            messageModel.setResult(userModel);
        return messageModel;
    }
    @RequestMapping("updatePwd")
    @ResponseBody
    public MessageModel updatePwd(HttpServletRequest request,String oldPassword,String newPassword,String confirmPassword){
        MessageModel messageModel = new MessageModel();
        String id = CookieUtil.getCookieValue(request,"id");
        try {
            userService.updatePwd(id,oldPassword,newPassword,confirmPassword);
            messageModel.setMsg("用户密码修改成功！");
        }catch (ParamsException pm){
            pm.printStackTrace();
            messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
            messageModel.setMsg(pm.getMsg());
        }catch(Exception e){
            e.printStackTrace();
            messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
            messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
        }
        return messageModel;
    }
    @ResponseBody
    @RequestMapping("queryAllCustomerManager")
    public List<User> queryAllCustomerManager(){
        return userService.queryAllCustomerManager();
    }

    @RequestMapping("index")
    public String index(){
        return "role";
    }

    @RequestMapping("queryUsersByParams")
    @ResponseBody
    public Map<String,Object> queryUsersByParams(UserQuery userQuery){
        return userService.queryUsersByParams(userQuery);
    }

    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(User user){
        userService.insert(user);
        return createMessageModel("用户添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(User user){
        userService.update(user);
        return createMessageModel("修改用户成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer id){
        userService.delete(id);
        return createMessageModel("删除用户成功");
    }
}
