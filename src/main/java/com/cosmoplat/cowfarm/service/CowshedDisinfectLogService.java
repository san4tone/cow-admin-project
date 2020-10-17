package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CowshedDisinfectLogDao;
import com.cosmoplat.cowfarm.pojo.CowshedDisinfectLog;
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
public class CowshedDisinfectLogService {

	@Autowired
	private CowshedDisinfectLogDao cowshedDisinfectLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<CowshedDisinfectLog> findAll() {
		return cowshedDisinfectLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CowshedDisinfectLog> findSearch(Map whereMap, int page, int size) {
		Specification<CowshedDisinfectLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return cowshedDisinfectLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<CowshedDisinfectLog> findSearch(Map whereMap) {
		Specification<CowshedDisinfectLog> specification = createSpecification(whereMap);
		return cowshedDisinfectLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public CowshedDisinfectLog findById(String id) {
		return cowshedDisinfectLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param cowshedDisinfectLog
	 */
	public void add(CowshedDisinfectLog cowshedDisinfectLog) {
		cowshedDisinfectLog.setId( idWorker.nextId()+"" );
		cowshedDisinfectLogDao.save(cowshedDisinfectLog);
	}

	/**
	 * 修改
	 * @param cowshedDisinfectLog
	 */
	public void update(CowshedDisinfectLog cowshedDisinfectLog) {
		cowshedDisinfectLogDao.save(cowshedDisinfectLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		cowshedDisinfectLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<CowshedDisinfectLog> createSpecification(Map searchMap) {

		return new Specification<CowshedDisinfectLog>() {

			@Override
			public Predicate toPredicate(Root<CowshedDisinfectLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 牛舍id
                if (searchMap.get("cowshed_id")!=null && !"".equals(searchMap.get("cowshed_id"))) {
                	predicateList.add(cb.like(root.get("cowshed_id").as(String.class), "%"+(String)searchMap.get("cowshed_id")+"%"));
                }
                // 消毒液
                if (searchMap.get("disinfectant")!=null && !"".equals(searchMap.get("disinfectant"))) {
                	predicateList.add(cb.like(root.get("disinfectant").as(String.class), "%"+(String)searchMap.get("disinfectant")+"%"));
                }
                // 配比
                if (searchMap.get("ratio")!=null && !"".equals(searchMap.get("ratio"))) {
                	predicateList.add(cb.like(root.get("ratio").as(String.class), "%"+(String)searchMap.get("ratio")+"%"));
                }
                // 备注
                if (searchMap.get("remark")!=null && !"".equals(searchMap.get("remark"))) {
                	predicateList.add(cb.like(root.get("remark").as(String.class), "%"+(String)searchMap.get("remark")+"%"));
                }
                // 执行人id
                if (searchMap.get("operator_id")!=null && !"".equals(searchMap.get("operator_id"))) {
                	predicateList.add(cb.like(root.get("operator_id").as(String.class), "%"+(String)searchMap.get("operator_id")+"%"));
                }
                // 执行人
                if (searchMap.get("operator_name")!=null && !"".equals(searchMap.get("operator_name"))) {
                	predicateList.add(cb.like(root.get("operator_name").as(String.class), "%"+(String)searchMap.get("operator_name")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
