package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import com.mage.crm.model.MessageModel;
import com.mage.crm.query.CustomerDevQuery;
import com.mage.crm.service.CustomerDevPlanService;
import com.mage.crm.service.SaleChanceService;
import com.mage.crm.vo.CustomerDevPlan;
import com.mage.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("cus_dev_plan")
public class CustomerDevPlanController extends BaseController {
    @Resource
    private CustomerDevPlanService customerDevPlanService;
    @Resource
    private SaleChanceService saleChanceService;
    @RequestMapping("index")
    public String index(Integer id, Model model){
        SaleChance saleChance = saleChanceService.querySaleChancesById(id);
        model.addAttribute("saleChance",saleChance);
        return "cus_dev_plan_detail";
    }

    @RequestMapping("queryCusDevPlans")
    @ResponseBody
    public Map<String,Object> queryCusDevPlans(CustomerDevQuery c){
        return customerDevPlanService.queryCusDevPlans(c);
    }

    @RequestMapping("insert")
    @ResponseBody
    public void save(CustomerDevPlan customerDevPlan){
        customerDevPlanService.insert(customerDevPlan);
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(CustomerDevPlan customerDevPlan){
        customerDevPlanService.update(customerDevPlan);
        return createMessageModel("客户开发计划更新成功！");
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer id){
        customerDevPlanService.delete(id);
        return createMessageModel("客户开发计划删除成功！");
    }
}
