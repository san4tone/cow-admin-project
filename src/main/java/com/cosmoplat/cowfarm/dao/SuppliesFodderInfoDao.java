package com.cosmoplat.cowfarm.dao;

import com.cosmoplat.cowfarm.pojo.SuppliesFodderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface SuppliesFodderInfoDao extends JpaRepository<SuppliesFodderInfo, String>, JpaSpecificationExecutor<SuppliesFodderInfo> {

    List<SuppliesFodderInfo> findByStatus(int status);
}
