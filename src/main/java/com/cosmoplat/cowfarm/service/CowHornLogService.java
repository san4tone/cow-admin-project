package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CowHornLogDao;
import com.cosmoplat.cowfarm.pojo.CowHornLog;
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
public class CowHornLogService {

	@Autowired
	private CowHornLogDao cowHornLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<CowHornLog> findAll() {
		return cowHornLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CowHornLog> findSearch(Map whereMap, int page, int size) {
		Specification<CowHornLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return cowHornLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<CowHornLog> findSearch(Map whereMap) {
		Specification<CowHornLog> specification = createSpecification(whereMap);
		return cowHornLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public CowHornLog findById(String id) {
		return cowHornLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param cowHornLog
	 */
	public void add(CowHornLog cowHornLog) {
		cowHornLog.setId( idWorker.nextId()+"" );
		cowHornLogDao.save(cowHornLog);
	}

	/**
	 * 修改
	 * @param cowHornLog
	 */
	public void update(CowHornLog cowHornLog) {
		cowHornLogDao.save(cowHornLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		cowHornLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<CowHornLog> createSpecification(Map searchMap) {

		return new Specification<CowHornLog>() {

			@Override
			public Predicate toPredicate(Root<CowHornLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
