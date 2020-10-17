package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.InsurancePolicyDao;
import com.cosmoplat.cowfarm.pojo.InsurancePolicy;
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
public class InsurancePolicyService {

	@Autowired
	private InsurancePolicyDao insurancePolicyDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<InsurancePolicy> findAll() {
		return insurancePolicyDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<InsurancePolicy> findSearch(Map whereMap, int page, int size) {
		Specification<InsurancePolicy> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return insurancePolicyDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<InsurancePolicy> findSearch(Map whereMap) {
		Specification<InsurancePolicy> specification = createSpecification(whereMap);
		return insurancePolicyDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public InsurancePolicy findById(String id) {
		return insurancePolicyDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param insurancePolicy
	 */
	public void add(InsurancePolicy insurancePolicy) {
		insurancePolicy.setId( idWorker.nextId()+"" );
		insurancePolicyDao.save(insurancePolicy);
	}

	/**
	 * 修改
	 * @param insurancePolicy
	 */
	public void update(InsurancePolicy insurancePolicy) {
		insurancePolicyDao.save(insurancePolicy);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		insurancePolicyDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<InsurancePolicy> createSpecification(Map searchMap) {

		return new Specification<InsurancePolicy>() {

			@Override
			public Predicate toPredicate(Root<InsurancePolicy> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
