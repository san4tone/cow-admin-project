package com.cosmoplat.cowfarm.dao;

import com.cosmoplat.cowfarm.pojo.SysRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface SysRoleMenuDao extends JpaRepository<SysRoleMenu,String>,JpaSpecificationExecutor<SysRoleMenu>{

}
