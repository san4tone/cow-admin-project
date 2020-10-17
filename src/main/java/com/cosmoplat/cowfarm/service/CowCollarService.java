package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CowCollarDao;
import com.cosmoplat.cowfarm.pojo.CowCollar;
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
public class CowCollarService {

	@Autowired
	private CowCollarDao cowCollarDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<CowCollar> findAll() {
		return cowCollarDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CowCollar> findSearch(Map whereMap, int page, int size) {
		Specification<CowCollar> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return cowCollarDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<CowCollar> findSearch(Map whereMap) {
		Specification<CowCollar> specification = createSpecification(whereMap);
		return cowCollarDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public CowCollar findById(String id) {
		return cowCollarDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param cowCollar
	 */
	public void add(CowCollar cowCollar) {
		cowCollar.setId( idWorker.nextId()+"" );
		cowCollarDao.save(cowCollar);
	}

	/**
	 * 修改
	 * @param cowCollar
	 */
	public void update(CowCollar cowCollar) {
		cowCollarDao.save(cowCollar);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		cowCollarDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<CowCollar> createSpecification(Map searchMap) {

		return new Specification<CowCollar>() {

			@Override
			public Predicate toPredicate(Root<CowCollar> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 牛号
                if (searchMap.get("cow_id")!=null && !"".equals(searchMap.get("cow_id"))) {
                	predicateList.add(cb.like(root.get("cow_id").as(String.class), "%"+(String)searchMap.get("cow_id")+"%"));
                }
                // 项圈号
                if (searchMap.get("collar_id")!=null && !"".equals(searchMap.get("collar_id"))) {
                	predicateList.add(cb.like(root.get("collar_id").as(String.class), "%"+(String)searchMap.get("collar_id")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
