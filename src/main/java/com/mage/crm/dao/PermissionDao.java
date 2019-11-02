package com.mage.crm.dao;

import com.mage.crm.vo.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Select("SELECT count(1) from t_permission where role_id=#{rid}")
    int queryPermissionCountByRid(Integer rid);

    @Delete("delete from t_permission where role_id=#{rid}")
    int deletePermissionByRid(Integer rid);

    int insertBatch(List<Permission> permissions);

    @Select("SELECT module_id from t_permission where role_id=#{rid}")
    List<Integer> queryPermissionModuleIdsByRid(Integer rid);

    @Select("select acl_value FROM t_user u"
            + " LEFT JOIN t_user_role ur ON u.id = ur.user_id"
            + " LEFT JOIN t_permission p ON ur.role_id = p.role_id"
            + " where u.id=#{uid} and u.is_valid=1")
    List<String> queryPermissionByUserId(String uid);
}
