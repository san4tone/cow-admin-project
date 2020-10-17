package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.InsuranceRegistryDao;
import com.cosmoplat.cowfarm.pojo.InsuranceRegistry;
import com.cosmoplat.cowfarm.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务层
 *
 * @author Administrator
 *
 */
@Service
public class InsuranceRegistryService {

	@Autowired
	private InsuranceRegistryDao insuranceRegistryDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<InsuranceRegistry> findAll() {
		return insuranceRegistryDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<InsuranceRegistry> findSearch(Map whereMap, int page, int size) {
		Specification<InsuranceRegistry> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return insuranceRegistryDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<InsuranceRegistry> findSearch(Map whereMap) {
		Specification<InsuranceRegistry> specification = createSpecification(whereMap);
		return insuranceRegistryDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public InsuranceRegistry findById(String id) {
		return insuranceRegistryDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param insuranceRegistry
	 */
	public void add(InsuranceRegistry insuranceRegistry) {
		insuranceRegistry.setId( idWorker.nextId()+"" );
		insuranceRegistryDao.save(insuranceRegistry);
	}

	/**
	 * 修改
	 * @param insuranceRegistry
	 */
	public void update(InsuranceRegistry insuranceRegistry) {
		insuranceRegistryDao.save(insuranceRegistry);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		insuranceRegistryDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<InsuranceRegistry> createSpecification(Map searchMap) {

		return new Specification<InsuranceRegistry>() {

			@Override
			public Predicate toPredicate(Root<InsuranceRegistry> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 牛号
                if (searchMap.get("cow_id")!=null && !"".equals(searchMap.get("cow_id"))) {
                	predicateList.add(cb.like(root.get("cow_id").as(String.class), "%"+(String)searchMap.get("cow_id")+"%"));
                }
                // 牛舍id
                if (searchMap.get("cowshed_id")!=null && !"".equals(searchMap.get("cowshed_id"))) {
                	predicateList.add(cb.like(root.get("cowshed_id").as(String.class), "%"+(String)searchMap.get("cowshed_id")+"%"));
                }
                // 保险公司
                if (searchMap.get("company")!=null && !"".equals(searchMap.get("company"))) {
                	predicateList.add(cb.like(root.get("company").as(String.class), "%"+(String)searchMap.get("company")+"%"));
                }
                // 保单号
                if (searchMap.get("policy_no")!=null && !"".equals(searchMap.get("policy_no"))) {
                	predicateList.add(cb.like(root.get("policy_no").as(String.class), "%"+(String)searchMap.get("policy_no")+"%"));
                }
                // 保险业务员
                if (searchMap.get("agent_name")!=null && !"".equals(searchMap.get("agent_name"))) {
                	predicateList.add(cb.like(root.get("agent_name").as(String.class), "%"+(String)searchMap.get("agent_name")+"%"));
                }
                // 保险金额
                if (searchMap.get("insured_amount")!=null && !"".equals(searchMap.get("insured_amount"))) {
                	predicateList.add(cb.like(root.get("insured_amount").as(String.class), "%"+(String)searchMap.get("insured_amount")+"%"));
                }
                // 操作人
                if (searchMap.get("operator_name")!=null && !"".equals(searchMap.get("operator_name"))) {
                	predicateList.add(cb.like(root.get("operator_name").as(String.class), "%"+(String)searchMap.get("operator_name")+"%"));
                }
                // 保险单文件（地址）
                if (searchMap.get("bxd_file")!=null && !"".equals(searchMap.get("bxd_file"))) {
                	predicateList.add(cb.like(root.get("bxd_file").as(String.class), "%"+(String)searchMap.get("bxd_file")+"%"));
                }
                // 备注
                if (searchMap.get("remark")!=null && !"".equals(searchMap.get("remark"))) {
                	predicateList.add(cb.like(root.get("remark").as(String.class), "%"+(String)searchMap.get("remark")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
