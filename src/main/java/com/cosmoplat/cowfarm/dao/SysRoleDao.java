package com.cosmoplat.cowfarm.dao;

import com.cosmoplat.cowfarm.pojo.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface SysRoleDao extends JpaRepository<SysRole, String>, JpaSpecificationExecutor<SysRole> {

    @Query(value = "SELECT * FROM sys_role WHERE role_id IN ( SELECT role_id FROM `sys_user_role` WHERE user_id = ? AND `status` =1 ) AND `status` = 1", nativeQuery = true)
    List<SysRole> findByUserId(String userId);
}
