package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CowshedCleanLogDao;
import com.cosmoplat.cowfarm.pojo.CowshedCleanLog;
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
public class CowshedCleanLogService {

	@Autowired
	private CowshedCleanLogDao cowshedCleanLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<CowshedCleanLog> findAll() {
		return cowshedCleanLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CowshedCleanLog> findSearch(Map whereMap, int page, int size) {
		Specification<CowshedCleanLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return cowshedCleanLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<CowshedCleanLog> findSearch(Map whereMap) {
		Specification<CowshedCleanLog> specification = createSpecification(whereMap);
		return cowshedCleanLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public CowshedCleanLog findById(String id) {
		return cowshedCleanLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param cowshedCleanLog
	 */
	public void add(CowshedCleanLog cowshedCleanLog) {
		cowshedCleanLog.setId( idWorker.nextId()+"" );
		cowshedCleanLogDao.save(cowshedCleanLog);
	}

	/**
	 * 修改
	 * @param cowshedCleanLog
	 */
	public void update(CowshedCleanLog cowshedCleanLog) {
		cowshedCleanLogDao.save(cowshedCleanLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		cowshedCleanLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<CowshedCleanLog> createSpecification(Map searchMap) {

		return new Specification<CowshedCleanLog>() {

			@Override
			public Predicate toPredicate(Root<CowshedCleanLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 牛舍id
                if (searchMap.get("cowshed_id")!=null && !"".equals(searchMap.get("cowshed_id"))) {
                	predicateList.add(cb.like(root.get("cowshed_id").as(String.class), "%"+(String)searchMap.get("cowshed_id")+"%"));
                }
                // 耗材id
                if (searchMap.get("material_id")!=null && !"".equals(searchMap.get("material_id"))) {
                	predicateList.add(cb.like(root.get("material_id").as(String.class), "%"+(String)searchMap.get("material_id")+"%"));
                }
                // 执行人id
                if (searchMap.get("operator_id")!=null && !"".equals(searchMap.get("operator_id"))) {
                	predicateList.add(cb.like(root.get("operator_id").as(String.class), "%"+(String)searchMap.get("operator_id")+"%"));
                }
                // 执行人
                if (searchMap.get("operator_name")!=null && !"".equals(searchMap.get("operator_name"))) {
                	predicateList.add(cb.like(root.get("operator_name").as(String.class), "%"+(String)searchMap.get("operator_name")+"%"));
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
