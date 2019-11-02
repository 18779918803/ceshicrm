package com.mage.crm.dao;

import com.mage.crm.dto.UserDto;
import com.mage.crm.query.UserQuery;
import com.mage.crm.vo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    User queryUserByName(String userName);

    User  queryUserById(String id);

    int updatePwd(@Param("id") String id,@Param("userPwd") String userPwd);

    @Select("SELECT u.true_name as trueName from t_user u LEFT JOIN t_user_role ur on u.id=ur.user_id LEFT JOIN t_role r on ur.role_id=r.id where u.is_valid=1 AND ur.is_valid=1 and r.is_valid=1 AND r.role_name='客户经理'")
    List<User> queryAllCustomerManager();

    List<UserDto> queryUsersByParams(UserQuery userQuery);

    int insert(User user);

    int update(User user);

    @Delete("update t_user set is_valid=0 where id=#{id}")
    int delete(Integer id);
}
