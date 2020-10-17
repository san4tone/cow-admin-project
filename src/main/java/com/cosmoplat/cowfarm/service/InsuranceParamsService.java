package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.InsuranceParamsDao;
import com.cosmoplat.cowfarm.pojo.InsuranceParams;
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
public class InsuranceParamsService {

	@Autowired
	private InsuranceParamsDao insuranceParamsDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<InsuranceParams> findAll() {
		return insuranceParamsDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<InsuranceParams> findSearch(Map whereMap, int page, int size) {
		Specification<InsuranceParams> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return insuranceParamsDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<InsuranceParams> findSearch(Map whereMap) {
		Specification<InsuranceParams> specification = createSpecification(whereMap);
		return insuranceParamsDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public InsuranceParams findById(String id) {
		return insuranceParamsDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param insuranceParams
	 */
	public void add(InsuranceParams insuranceParams) {
		insuranceParams.setId( idWorker.nextId()+"" );
		insuranceParamsDao.save(insuranceParams);
	}

	/**
	 * 修改
	 * @param insuranceParams
	 */
	public void update(InsuranceParams insuranceParams) {
		insuranceParamsDao.save(insuranceParams);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		insuranceParamsDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<InsuranceParams> createSpecification(Map searchMap) {

		return new Specification<InsuranceParams>() {

			@Override
			public Predicate toPredicate(Root<InsuranceParams> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 参数名称
                if (searchMap.get("prewarning_name")!=null && !"".equals(searchMap.get("prewarning_name"))) {
                	predicateList.add(cb.like(root.get("prewarning_name").as(String.class), "%"+(String)searchMap.get("prewarning_name")+"%"));
                }
                // 参数描述
                if (searchMap.get("prewarning_condition_name")!=null && !"".equals(searchMap.get("prewarning_condition_name"))) {
                	predicateList.add(cb.like(root.get("prewarning_condition_name").as(String.class), "%"+(String)searchMap.get("prewarning_condition_name")+"%"));
                }
                // 参数a
                if (searchMap.get("prewarning_value_a")!=null && !"".equals(searchMap.get("prewarning_value_a"))) {
                	predicateList.add(cb.like(root.get("prewarning_value_a").as(String.class), "%"+(String)searchMap.get("prewarning_value_a")+"%"));
                }
                // 参数b
                if (searchMap.get("prewarning_value_b")!=null && !"".equals(searchMap.get("prewarning_value_b"))) {
                	predicateList.add(cb.like(root.get("prewarning_value_b").as(String.class), "%"+(String)searchMap.get("prewarning_value_b")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
