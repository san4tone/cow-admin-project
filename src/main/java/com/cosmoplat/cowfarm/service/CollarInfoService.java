package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CollarInfoDao;
import com.cosmoplat.cowfarm.pojo.CollarInfo;
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
public class CollarInfoService {

	@Autowired
	private CollarInfoDao collarInfoDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<CollarInfo> findAll() {
		return collarInfoDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CollarInfo> findSearch(Map whereMap, int page, int size) {
		Specification<CollarInfo> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return collarInfoDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<CollarInfo> findSearch(Map whereMap) {
		Specification<CollarInfo> specification = createSpecification(whereMap);
		return collarInfoDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public CollarInfo findById(String id) {
		return collarInfoDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param collarInfo
	 */
	public void add(CollarInfo collarInfo) {
		collarInfo.setId( idWorker.nextId()+"" );
		collarInfoDao.save(collarInfo);
	}

	/**
	 * 修改
	 * @param collarInfo
	 */
	public void update(CollarInfo collarInfo) {
		collarInfoDao.save(collarInfo);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		collarInfoDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<CollarInfo> createSpecification(Map searchMap) {

		return new Specification<CollarInfo>() {

			@Override
			public Predicate toPredicate(Root<CollarInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
