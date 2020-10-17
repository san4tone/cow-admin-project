package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CowSpayLogDao;
import com.cosmoplat.cowfarm.pojo.CowSpayLog;
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
public class CowSpayLogService {

	@Autowired
	private CowSpayLogDao cowSpayLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<CowSpayLog> findAll() {
		return cowSpayLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CowSpayLog> findSearch(Map whereMap, int page, int size) {
		Specification<CowSpayLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return cowSpayLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<CowSpayLog> findSearch(Map whereMap) {
		Specification<CowSpayLog> specification = createSpecification(whereMap);
		return cowSpayLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public CowSpayLog findById(String id) {
		return cowSpayLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param cowSpayLog
	 */
	public void add(CowSpayLog cowSpayLog) {
		cowSpayLog.setId( idWorker.nextId()+"" );
		cowSpayLogDao.save(cowSpayLog);
	}

	/**
	 * 修改
	 * @param cowSpayLog
	 */
	public void update(CowSpayLog cowSpayLog) {
		cowSpayLogDao.save(cowSpayLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		cowSpayLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<CowSpayLog> createSpecification(Map searchMap) {

		return new Specification<CowSpayLog>() {

			@Override
			public Predicate toPredicate(Root<CowSpayLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
