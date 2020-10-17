package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CowQuarantineLogDao;
import com.cosmoplat.cowfarm.pojo.CowQuarantineLog;
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
public class CowQuarantineLogService {

	@Autowired
	private CowQuarantineLogDao cowQuarantineLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<CowQuarantineLog> findAll() {
		return cowQuarantineLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CowQuarantineLog> findSearch(Map whereMap, int page, int size) {
		Specification<CowQuarantineLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return cowQuarantineLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<CowQuarantineLog> findSearch(Map whereMap) {
		Specification<CowQuarantineLog> specification = createSpecification(whereMap);
		return cowQuarantineLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public CowQuarantineLog findById(String id) {
		return cowQuarantineLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param cowQuarantineLog
	 */
	public void add(CowQuarantineLog cowQuarantineLog) {
		cowQuarantineLog.setId( idWorker.nextId()+"" );
		cowQuarantineLogDao.save(cowQuarantineLog);
	}

	/**
	 * 修改
	 * @param cowQuarantineLog
	 */
	public void update(CowQuarantineLog cowQuarantineLog) {
		cowQuarantineLogDao.save(cowQuarantineLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		cowQuarantineLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<CowQuarantineLog> createSpecification(Map searchMap) {

		return new Specification<CowQuarantineLog>() {

			@Override
			public Predicate toPredicate(Root<CowQuarantineLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
                // 检疫名称
                if (searchMap.get("quarantine_name")!=null && !"".equals(searchMap.get("quarantine_name"))) {
                	predicateList.add(cb.like(root.get("quarantine_name").as(String.class), "%"+(String)searchMap.get("quarantine_name")+"%"));
                }
                // 执行人id
                if (searchMap.get("operator_id")!=null && !"".equals(searchMap.get("operator_id"))) {
                	predicateList.add(cb.like(root.get("operator_id").as(String.class), "%"+(String)searchMap.get("operator_id")+"%"));
                }
                // 执行人名称
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
