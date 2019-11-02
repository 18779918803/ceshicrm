package com.mage.crm.dao;

import com.mage.crm.vo.UserRole;

import java.util.List;

public interface UserRoleDao {
    int insertBacth(List<UserRole> roleList);

    int queryRoleCountsByUserId(String id);

    int deleteRolesByUserId(String id);
}
