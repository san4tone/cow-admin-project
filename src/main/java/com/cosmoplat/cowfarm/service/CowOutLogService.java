package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CowOutLogDao;
import com.cosmoplat.cowfarm.pojo.CowOutLog;
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
public class CowOutLogService {

	@Autowired
	private CowOutLogDao cowOutLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<CowOutLog> findAll() {
		return cowOutLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CowOutLog> findSearch(Map whereMap, int page, int size) {
		Specification<CowOutLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return cowOutLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<CowOutLog> findSearch(Map whereMap) {
		Specification<CowOutLog> specification = createSpecification(whereMap);
		return cowOutLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public CowOutLog findById(String id) {
		return cowOutLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param cowOutLog
	 */
	public void add(CowOutLog cowOutLog) {
		cowOutLog.setId( idWorker.nextId()+"" );
		cowOutLogDao.save(cowOutLog);
	}

	/**
	 * 修改
	 * @param cowOutLog
	 */
	public void update(CowOutLog cowOutLog) {
		cowOutLogDao.save(cowOutLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		cowOutLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<CowOutLog> createSpecification(Map searchMap) {

		return new Specification<CowOutLog>() {

			@Override
			public Predicate toPredicate(Root<CowOutLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 牛号
                if (searchMap.get("cow_id")!=null && !"".equals(searchMap.get("cow_id"))) {
                	predicateList.add(cb.like(root.get("cow_id").as(String.class), "%"+(String)searchMap.get("cow_id")+"%"));
                }
                // 牛舍编号
                if (searchMap.get("cowshed_id")!=null && !"".equals(searchMap.get("cowshed_id"))) {
                	predicateList.add(cb.like(root.get("cowshed_id").as(String.class), "%"+(String)searchMap.get("cowshed_id")+"%"));
                }
                // 离场原因
                if (searchMap.get("out_reason")!=null && !"".equals(searchMap.get("out_reason"))) {
                	predicateList.add(cb.like(root.get("out_reason").as(String.class), "%"+(String)searchMap.get("out_reason")+"%"));
                }
                // 去向
                if (searchMap.get("out_destination")!=null && !"".equals(searchMap.get("out_destination"))) {
                	predicateList.add(cb.like(root.get("out_destination").as(String.class), "%"+(String)searchMap.get("out_destination")+"%"));
                }
                // 执行人id
                if (searchMap.get("operator_id")!=null && !"".equals(searchMap.get("operator_id"))) {
                	predicateList.add(cb.like(root.get("operator_id").as(String.class), "%"+(String)searchMap.get("operator_id")+"%"));
                }
                // 执行人name
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
