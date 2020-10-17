package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CowCowshedDao;
import com.cosmoplat.cowfarm.pojo.CowCowshed;
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
public class CowCowshedService {

	@Autowired
	private CowCowshedDao cowCowshedDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<CowCowshed> findAll() {
		return cowCowshedDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CowCowshed> findSearch(Map whereMap, int page, int size) {
		Specification<CowCowshed> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return cowCowshedDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<CowCowshed> findSearch(Map whereMap) {
		Specification<CowCowshed> specification = createSpecification(whereMap);
		return cowCowshedDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public CowCowshed findById(String id) {
		return cowCowshedDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param cowCowshed
	 */
	public void add(CowCowshed cowCowshed) {
		cowCowshed.setId( idWorker.nextId()+"" );
		cowCowshedDao.save(cowCowshed);
	}

	/**
	 * 修改
	 * @param cowCowshed
	 */
	public void update(CowCowshed cowCowshed) {
		cowCowshedDao.save(cowCowshed);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		cowCowshedDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<CowCowshed> createSpecification(Map searchMap) {

		return new Specification<CowCowshed>() {

			@Override
			public Predicate toPredicate(Root<CowCowshed> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
