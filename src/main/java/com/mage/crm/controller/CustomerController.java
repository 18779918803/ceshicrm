package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import com.mage.crm.model.MessageModel;
import com.mage.crm.query.CustomerGCQuery;
import com.mage.crm.query.CustomerQuery;
import com.mage.crm.service.CustomerService;
import com.mage.crm.vo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController{
    @Resource
    private CustomerService customerService;
    @RequestMapping("queryAllCustomers")
    @ResponseBody
    public List<Customer> queryAllCustomers(){
        return customerService.queryAllCustomers();
    }

    @RequestMapping("index")
    public String index(){
        return "customer";
    }

    @RequestMapping("queryCustomersByParams")
    @ResponseBody
    public Map<String,Object> queryCustomersByParams(CustomerQuery customerQuery){
        return customerService.queryCustomersByParams(customerQuery);
    }

    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(Customer customer){
        customerService.insert(customer);
        return createMessageModel("添加客户信息成功！");
    }
    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(Customer customer){
        customerService.update(customer);
        return createMessageModel("修改客户信息成功！");
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer[] id){
        customerService.delete(id);
        return createMessageModel("删除客户信息成功！");
    }

    @RequestMapping("openCustomerOtherInfo/{type}/{id}")
    public String openCustomerOtherInfo(@PathVariable("type")String type, @PathVariable("id") Integer id , Model model){
        model.addAttribute("customer",customerService.queryCustomerById(id));
        switch (type){
            case "1":
                return "customer_linkMan";
            case "2":
                return "customer_concat";
            case "3":
                return "customer_order";
                default:
                    return "error";
        }
    }

    @RequestMapping("queryCustomersContribution")
    @ResponseBody
    public Map<String,Object> queryCustomersContribution(CustomerGCQuery customerGCQuery){
        return customerService.queryCustomersContribution(customerGCQuery);
    }

    @RequestMapping("queryCustomerGC")
    @ResponseBody
    public Map<String,Object> queryCustomerGC(){
        return customerService.queryCustomerGC();
    }
}
