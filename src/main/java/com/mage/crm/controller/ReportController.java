package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController{

    @RequestMapping("/{type}")
    public String index(@PathVariable("type") String type){
        switch (type){
            case "0": return "customer_contribution";
            case "1": return "customer_gc";
            case "2": return "customer_serve";
            case "3": return "customer_loss";
            default: return "error";
        }
    }
}
