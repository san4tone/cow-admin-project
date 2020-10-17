package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.SuppliesFodderChangeLogDao;
import com.cosmoplat.cowfarm.pojo.SuppliesFodderChangeLog;
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
public class SuppliesFodderChangeLogService {

	@Autowired
	private SuppliesFodderChangeLogDao suppliesFodderChangeLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<SuppliesFodderChangeLog> findAll() {
		return suppliesFodderChangeLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SuppliesFodderChangeLog> findSearch(Map whereMap, int page, int size) {
		Specification<SuppliesFodderChangeLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return suppliesFodderChangeLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<SuppliesFodderChangeLog> findSearch(Map whereMap) {
		Specification<SuppliesFodderChangeLog> specification = createSpecification(whereMap);
		return suppliesFodderChangeLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public SuppliesFodderChangeLog findById(String id) {
		return suppliesFodderChangeLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param suppliesFodderChangeLog
	 */
	public void add(SuppliesFodderChangeLog suppliesFodderChangeLog) {
		suppliesFodderChangeLog.setId( idWorker.nextId()+"" );
		suppliesFodderChangeLogDao.save(suppliesFodderChangeLog);
	}

	/**
	 * 修改
	 * @param suppliesFodderChangeLog
	 */
	public void update(SuppliesFodderChangeLog suppliesFodderChangeLog) {
		suppliesFodderChangeLogDao.save(suppliesFodderChangeLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		suppliesFodderChangeLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<SuppliesFodderChangeLog> createSpecification(Map searchMap) {

		return new Specification<SuppliesFodderChangeLog>() {

			@Override
			public Predicate toPredicate(Root<SuppliesFodderChangeLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
