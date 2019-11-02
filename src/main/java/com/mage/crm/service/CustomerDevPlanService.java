package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mage.crm.dao.CustomerDevPlanDao;
import com.mage.crm.dao.SaleChanceDao;
import com.mage.crm.query.CustomerDevQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.vo.CustomerDevPlan;
import com.mage.crm.vo.SaleChance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CustomerDevPlanService {
    @Resource
    private CustomerDevPlanDao customerDevPlanDao;
    @Resource
    private SaleChanceDao saleChanceDao;

    public Map<String,Object> queryCusDevPlans(CustomerDevQuery c) {
        PageHelper.startPage(c.getPage(),c.getRows());
        List<CustomerDevPlan> customerDevPlanList = customerDevPlanDao.queryCusDevPlans(c.getSaleChanceId());
        PageInfo<CustomerDevPlan> customerDevPlanPageInfo = new PageInfo<>(customerDevPlanList);
        Map<String,Object> map = new HashMap<>();
        map.put("total",customerDevPlanPageInfo.getTotal());
        map.put("rows",customerDevPlanPageInfo.getList());
        return map;
    }

    public void insert(CustomerDevPlan customerDevPlan) {
        SaleChance saleChance = saleChanceDao.querySaleChancesById(customerDevPlan.getSaleChanceId());
        AssertUtil.isTrue(null==saleChance,"营销机会已经不存在了");
        AssertUtil.isTrue(saleChance.getDevResult()==2,"营销机会已经完成了！");
        AssertUtil.isTrue(saleChance.getDevResult()==3,"营销机会已经失败了！");
        customerDevPlan.setCreateDate(new Date());
        customerDevPlan.setUpdateDate(new Date());
        customerDevPlan.setIsValid(1);
        AssertUtil.isTrue(customerDevPlanDao.insert(customerDevPlan)<1,"添加客户开发失败了");
        if(saleChance.getDevResult()==0){
            saleChanceDao.updateDevResult(customerDevPlan.getSaleChanceId(),1);
        }
    }

    public void update(CustomerDevPlan customerDevPlan) {
        SaleChance saleChance = saleChanceDao.querySaleChancesById(customerDevPlan.getSaleChanceId());
        AssertUtil.isTrue(null==saleChance,"营销机会已经不存在了");
        AssertUtil.isTrue(saleChance.getDevResult()==2,"营销机会已经完成了！");
        AssertUtil.isTrue(saleChance.getDevResult()==3,"营销机会已经失败了！");
        customerDevPlan.setUpdateDate(new Date());
        AssertUtil.isTrue(customerDevPlanDao.update(customerDevPlan)<1,"客户开发计划修改失败！");
    }

    public void delete(Integer id) {
        AssertUtil.isTrue(customerDevPlanDao.delete(id)<1,"客户开发计划删除失败！");
    }
}
