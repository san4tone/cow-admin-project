package com.cosmoplat.cowfarm.dao;

import com.cosmoplat.cowfarm.pojo.InsuranceParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface InsuranceParamsDao extends JpaRepository<InsuranceParams,String>,JpaSpecificationExecutor<InsuranceParams>{

}
