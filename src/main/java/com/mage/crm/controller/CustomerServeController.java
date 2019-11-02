package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import com.mage.crm.model.MessageModel;
import com.mage.crm.query.CustomerServeQuery;
import com.mage.crm.service.CustomerServeService;
import com.mage.crm.vo.CustomerServe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("customer_serve")
public class CustomerServeController extends BaseController{
    @Resource
    private CustomerServeService customerServeService;

    @RequestMapping("index/{type}")
    public String index(@PathVariable("type") Integer type){
        switch(type){
            case 1:
                return"customer_serve_create";
            case 2:
                return "customer_serve_assign";
            case 3:
                return "customer_serve_proceed";
            case 4:
                return "customer_serve_feed_back";
            case 5:
                return "customer_serve_archive";
            default:
                return "error";
        }
    }
    @ResponseBody
    @RequestMapping("insert")
    public MessageModel insert(CustomerServe customerServe){
        customerServeService.insert(customerServe);
        return createMessageModel("客户服务创建成功！");
    }

    @ResponseBody
    @RequestMapping("queryCustomerServesByParams")
    public Map<String,Object> queryCustomerServesByParams(CustomerServeQuery customerServeQuery){
        return customerServeService.queryCustomerServesByParams(customerServeQuery);
    }

    @ResponseBody
    @RequestMapping("update")
    public MessageModel update(CustomerServe customerServe,HttpServletRequest httpServletRequest){
        customerServeService.update(customerServe,httpServletRequest);
        return createMessageModel("客户服务分配成功");
    }

    @ResponseBody
    @RequestMapping("queryCustomerServeType")
    public Map<String,Object> queryCustomerServeType(){
        return customerServeService.queryCustomerServeType();
    }
}
