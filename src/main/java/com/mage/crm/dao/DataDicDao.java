package com.mage.crm.dao;

import com.mage.crm.vo.DataDic;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DataDicDao {

    @Select("SELECT data_dic_value 'dataDicValue' from t_datadic WHERE data_dic_name=#{dataDicName} AND is_valid=1")
    List<DataDic> queryDataDicValueByDataDicName(String dataDicName);
}
