package com.mage.crm.dao;

import com.mage.crm.query.CustomerDevQuery;
import com.mage.crm.vo.CustomerDevPlan;

import java.util.List;

public interface CustomerDevPlanDao {

    List<CustomerDevPlan> queryCusDevPlans(Integer saleChanceId);

    int insert(CustomerDevPlan customerDevPlan);

    int update(CustomerDevPlan customerDevPlan);

    int delete(Integer id);
}
