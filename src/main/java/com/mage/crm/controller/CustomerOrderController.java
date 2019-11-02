package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import com.mage.crm.query.CustomerOrderQuery;
import com.mage.crm.service.CustomerOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer_order")
public class CustomerOrderController extends BaseController{
    @Resource
    private CustomerOrderService customerOrderService;

    @RequestMapping("queryOrdersByCid")
    @ResponseBody
    public Map<String,Object> queryOrdersByCid(CustomerOrderQuery customerOrderQuery){
        return customerOrderService.queryOrdersByCid(customerOrderQuery);
    }

    @RequestMapping("queryOrderInfoById")
    @ResponseBody
    public Map<String,Object> queryOrderInfoById(Integer orderId){
        return customerOrderService.queryOrderInfoById(orderId);
    }
}
