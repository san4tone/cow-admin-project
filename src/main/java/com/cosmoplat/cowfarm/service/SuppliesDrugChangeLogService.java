package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.SuppliesDrugChangeLogDao;
import com.cosmoplat.cowfarm.pojo.SuppliesDrugChangeLog;
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

;

/**
 * 服务层
 *
 * @author Administrator
 *
 */
@Service
public class SuppliesDrugChangeLogService {

	@Autowired
	private SuppliesDrugChangeLogDao suppliesDrugChangeLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<SuppliesDrugChangeLog> findAll() {
		return suppliesDrugChangeLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SuppliesDrugChangeLog> findSearch(Map whereMap, int page, int size) {
		Specification<SuppliesDrugChangeLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return suppliesDrugChangeLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<SuppliesDrugChangeLog> findSearch(Map whereMap) {
		Specification<SuppliesDrugChangeLog> specification = createSpecification(whereMap);
		return suppliesDrugChangeLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public SuppliesDrugChangeLog findById(String id) {
		return suppliesDrugChangeLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param suppliesDrugChangeLog
	 */
	public void add(SuppliesDrugChangeLog suppliesDrugChangeLog) {
		suppliesDrugChangeLog.setId( idWorker.nextId()+"" );
		suppliesDrugChangeLogDao.save(suppliesDrugChangeLog);
	}

	/**
	 * 修改
	 * @param suppliesDrugChangeLog
	 */
	public void update(SuppliesDrugChangeLog suppliesDrugChangeLog) {
		suppliesDrugChangeLogDao.save(suppliesDrugChangeLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		suppliesDrugChangeLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<SuppliesDrugChangeLog> createSpecification(Map searchMap) {

		return new Specification<SuppliesDrugChangeLog>() {

			@Override
			public Predicate toPredicate(Root<SuppliesDrugChangeLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
