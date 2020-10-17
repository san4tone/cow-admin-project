package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.InsuranceWarningDao;
import com.cosmoplat.cowfarm.pojo.InsuranceWarning;
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
public class InsuranceWarningService {

	@Autowired
	private InsuranceWarningDao insuranceWarningDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<InsuranceWarning> findAll() {
		return insuranceWarningDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<InsuranceWarning> findSearch(Map whereMap, int page, int size) {
		Specification<InsuranceWarning> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return insuranceWarningDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<InsuranceWarning> findSearch(Map whereMap) {
		Specification<InsuranceWarning> specification = createSpecification(whereMap);
		return insuranceWarningDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public InsuranceWarning findById(String id) {
		return insuranceWarningDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param insuranceWarning
	 */
	public void add(InsuranceWarning insuranceWarning) {
		insuranceWarning.setId( idWorker.nextId()+"" );
		insuranceWarningDao.save(insuranceWarning);
	}

	/**
	 * 修改
	 * @param insuranceWarning
	 */
	public void update(InsuranceWarning insuranceWarning) {
		insuranceWarningDao.save(insuranceWarning);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		insuranceWarningDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<InsuranceWarning> createSpecification(Map searchMap) {

		return new Specification<InsuranceWarning>() {

			@Override
			public Predicate toPredicate(Root<InsuranceWarning> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 牛号
                if (searchMap.get("cow_id")!=null && !"".equals(searchMap.get("cow_id"))) {
                	predicateList.add(cb.like(root.get("cow_id").as(String.class), "%"+(String)searchMap.get("cow_id")+"%"));
                }
                // 牛舍
                if (searchMap.get("cowshed_id")!=null && !"".equals(searchMap.get("cowshed_id"))) {
                	predicateList.add(cb.like(root.get("cowshed_id").as(String.class), "%"+(String)searchMap.get("cowshed_id")+"%"));
                }
                // 本次预警
                if (searchMap.get("this_warning")!=null && !"".equals(searchMap.get("this_warning"))) {
                	predicateList.add(cb.like(root.get("this_warning").as(String.class), "%"+(String)searchMap.get("this_warning")+"%"));
                }
                // 下次预警
                if (searchMap.get("next_warning")!=null && !"".equals(searchMap.get("next_warning"))) {
                	predicateList.add(cb.like(root.get("next_warning").as(String.class), "%"+(String)searchMap.get("next_warning")+"%"));
                }
                // 日龄
                if (searchMap.get("cow_age")!=null && !"".equals(searchMap.get("cow_age"))) {
                	predicateList.add(cb.like(root.get("cow_age").as(String.class), "%"+(String)searchMap.get("cow_age")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
