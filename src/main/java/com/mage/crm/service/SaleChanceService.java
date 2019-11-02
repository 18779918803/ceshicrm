package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mage.crm.dao.SaleChanceDao;
import com.mage.crm.query.SaleChanceQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.vo.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleChanceService {
    @Resource
    private SaleChanceDao saleChanceDao;

    public Map<String,Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery) {
        PageHelper.startPage(saleChanceQuery.getPage(),saleChanceQuery.getRows());
        List<SaleChance> saleChanceList= saleChanceDao.querySaleChancesByParams(saleChanceQuery);
        PageInfo<SaleChance> saleChancePageInfo = new PageInfo<>(saleChanceList);
        List<SaleChance> list = saleChancePageInfo.getList();
        Map<String,Object> map = new HashMap<>();
        map.put("rows",saleChancePageInfo.getList());
        map.put("total",saleChancePageInfo.getTotal());
        return map;
    }

    /**
     *   插入数据前的检测
     * 1.客户名称不能为空
     * 2.联系人不能为空
     * 3.联系电话不能为空
     * 额外添加的数据.
     *  is_valid=1
     *  createDate
     *  updateDate
     *  如果分配人不为空state=0 否在为1
     *  state 0 未分配 1 已分配
     *  开发结果 0-3代表 未开发 开发中 开发成功 开发失败
     * @param saleChance
     */
    public void insert(SaleChance saleChance) {
        checkParams(saleChance.getLinkMan(),saleChance.getLinkPhone(),saleChance.getCgjl());
        saleChance.setIsValid(1);
        saleChance.setUpdateDate(new Date());
        saleChance.setCreateDate(new Date());
        saleChance.setDevResult(0);
        if(StringUtils.isBlank(saleChance.getAssignMan())){
            saleChance.setState(0);
        }else{
            saleChance.setState(1);
            saleChance.setAssignTime(new Date());
        }
        AssertUtil.isTrue(saleChanceDao.insert(saleChance)<1,"营销机会添加失败");
    }

    //做非空检验，联系人，联系电话，成功几率
    public void checkParams (String linkMan, String linkPhone,String cgjl){
        AssertUtil.isTrue(StringUtils.isBlank(linkMan),"联系人不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone),"联系电话不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(cgjl),"成功几率不能为空！");
    }

    public void update(SaleChance saleChance) {
        checkParams(saleChance.getLinkMan(),saleChance.getLinkPhone(),saleChance.getCgjl());
        saleChance.setUpdateDate(new Date());
        if(StringUtils.isBlank(saleChance.getAssignMan())){
            saleChance.setState(0);
        }else{
            saleChance.setState(1);
            saleChance.setAssignTime(new Date());
        }
        AssertUtil.isTrue(saleChanceDao.update(saleChance)<1,"营销机会修改失败");
    }

    public void delete(Integer[] id) {
        AssertUtil.isTrue(saleChanceDao.delete(id)<id.length,"营销机会删除失败");
    }

    public SaleChance querySaleChancesById(Integer id) {
        return saleChanceDao.querySaleChancesById(id);
    }

    public void updateSaleChanceDevResult(Integer devResult, Integer saleChanceId) {
        saleChanceDao.updateDevResult(devResult,saleChanceId);
    }
}
