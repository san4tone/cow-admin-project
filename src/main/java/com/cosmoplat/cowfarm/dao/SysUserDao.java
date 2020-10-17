package com.cosmoplat.cowfarm.dao;

import com.cosmoplat.cowfarm.pojo.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface SysUserDao extends JpaRepository<SysUser,String>,JpaSpecificationExecutor<SysUser>{

    SysUser findByMobile(String mobile);

}
