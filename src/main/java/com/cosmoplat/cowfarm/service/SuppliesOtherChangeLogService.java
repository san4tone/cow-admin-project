package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.SuppliesOtherChangeLogDao;
import com.cosmoplat.cowfarm.pojo.SuppliesOtherChangeLog;
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
public class SuppliesOtherChangeLogService {

	@Autowired
	private SuppliesOtherChangeLogDao suppliesOtherChangeLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<SuppliesOtherChangeLog> findAll() {
		return suppliesOtherChangeLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SuppliesOtherChangeLog> findSearch(Map whereMap, int page, int size) {
		Specification<SuppliesOtherChangeLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return suppliesOtherChangeLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<SuppliesOtherChangeLog> findSearch(Map whereMap) {
		Specification<SuppliesOtherChangeLog> specification = createSpecification(whereMap);
		return suppliesOtherChangeLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public SuppliesOtherChangeLog findById(String id) {
		return suppliesOtherChangeLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param suppliesOtherChangeLog
	 */
	public void add(SuppliesOtherChangeLog suppliesOtherChangeLog) {
		suppliesOtherChangeLog.setId( idWorker.nextId()+"" );
		suppliesOtherChangeLogDao.save(suppliesOtherChangeLog);
	}

	/**
	 * 修改
	 * @param suppliesOtherChangeLog
	 */
	public void update(SuppliesOtherChangeLog suppliesOtherChangeLog) {
		suppliesOtherChangeLogDao.save(suppliesOtherChangeLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		suppliesOtherChangeLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<SuppliesOtherChangeLog> createSpecification(Map searchMap) {

		return new Specification<SuppliesOtherChangeLog>() {

			@Override
			public Predicate toPredicate(Root<SuppliesOtherChangeLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
