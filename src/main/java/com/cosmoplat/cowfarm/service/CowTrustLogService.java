package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CowTrustLogDao;
import com.cosmoplat.cowfarm.pojo.CowTrustLog;
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
public class CowTrustLogService {

	@Autowired
	private CowTrustLogDao cowTrustLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<CowTrustLog> findAll() {
		return cowTrustLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CowTrustLog> findSearch(Map whereMap, int page, int size) {
		Specification<CowTrustLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return cowTrustLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<CowTrustLog> findSearch(Map whereMap) {
		Specification<CowTrustLog> specification = createSpecification(whereMap);
		return cowTrustLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public CowTrustLog findById(String id) {
		return cowTrustLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param cowTrustLog
	 */
	public void add(CowTrustLog cowTrustLog) {
		cowTrustLog.setId( idWorker.nextId()+"" );
		cowTrustLogDao.save(cowTrustLog);
	}

	/**
	 * 修改
	 * @param cowTrustLog
	 */
	public void update(CowTrustLog cowTrustLog) {
		cowTrustLogDao.save(cowTrustLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		cowTrustLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<CowTrustLog> createSpecification(Map searchMap) {

		return new Specification<CowTrustLog>() {

			@Override
			public Predicate toPredicate(Root<CowTrustLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 农户名称
                if (searchMap.get("farmer_name")!=null && !"".equals(searchMap.get("farmer_name"))) {
                	predicateList.add(cb.like(root.get("farmer_name").as(String.class), "%"+(String)searchMap.get("farmer_name")+"%"));
                }
                // 牛号
                if (searchMap.get("cow_id")!=null && !"".equals(searchMap.get("cow_id"))) {
                	predicateList.add(cb.like(root.get("cow_id").as(String.class), "%"+(String)searchMap.get("cow_id")+"%"));
                }
                // 牛舍id
                if (searchMap.get("cowshed_id")!=null && !"".equals(searchMap.get("cowshed_id"))) {
                	predicateList.add(cb.like(root.get("cowshed_id").as(String.class), "%"+(String)searchMap.get("cowshed_id")+"%"));
                }
                // 操作人
                if (searchMap.get("operate_user_id")!=null && !"".equals(searchMap.get("operate_user_id"))) {
                	predicateList.add(cb.like(root.get("operate_user_id").as(String.class), "%"+(String)searchMap.get("operate_user_id")+"%"));
                }
                // 操作人姓名
                if (searchMap.get("operate_username")!=null && !"".equals(searchMap.get("operate_username"))) {
                	predicateList.add(cb.like(root.get("operate_username").as(String.class), "%"+(String)searchMap.get("operate_username")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
