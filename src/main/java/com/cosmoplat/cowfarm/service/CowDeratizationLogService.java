package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CowDeratizationLogDao;
import com.cosmoplat.cowfarm.pojo.CowDeratizationLog;
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
public class CowDeratizationLogService {

	@Autowired
	private CowDeratizationLogDao cowDeratizationLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<CowDeratizationLog> findAll() {
		return cowDeratizationLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CowDeratizationLog> findSearch(Map whereMap, int page, int size) {
		Specification<CowDeratizationLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return cowDeratizationLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<CowDeratizationLog> findSearch(Map whereMap) {
		Specification<CowDeratizationLog> specification = createSpecification(whereMap);
		return cowDeratizationLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public CowDeratizationLog findById(String id) {
		return cowDeratizationLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param cowDeratizationLog
	 */
	public void add(CowDeratizationLog cowDeratizationLog) {
		cowDeratizationLog.setId( idWorker.nextId()+"" );
		cowDeratizationLogDao.save(cowDeratizationLog);
	}

	/**
	 * 修改
	 * @param cowDeratizationLog
	 */
	public void update(CowDeratizationLog cowDeratizationLog) {
		cowDeratizationLogDao.save(cowDeratizationLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		cowDeratizationLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<CowDeratizationLog> createSpecification(Map searchMap) {

		return new Specification<CowDeratizationLog>() {

			@Override
			public Predicate toPredicate(Root<CowDeratizationLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 牛号
                if (searchMap.get("cow_id")!=null && !"".equals(searchMap.get("cow_id"))) {
                	predicateList.add(cb.like(root.get("cow_id").as(String.class), "%"+(String)searchMap.get("cow_id")+"%"));
                }
                // 牛舍id
                if (searchMap.get("cowshed_id")!=null && !"".equals(searchMap.get("cowshed_id"))) {
                	predicateList.add(cb.like(root.get("cowshed_id").as(String.class), "%"+(String)searchMap.get("cowshed_id")+"%"));
                }
                // 投掷地点
                if (searchMap.get("cast_site")!=null && !"".equals(searchMap.get("cast_site"))) {
                	predicateList.add(cb.like(root.get("cast_site").as(String.class), "%"+(String)searchMap.get("cast_site")+"%"));
                }
                // 执行人id
                if (searchMap.get("operator_id")!=null && !"".equals(searchMap.get("operator_id"))) {
                	predicateList.add(cb.like(root.get("operator_id").as(String.class), "%"+(String)searchMap.get("operator_id")+"%"));
                }
                // 执行人name
                if (searchMap.get("operator_name")!=null && !"".equals(searchMap.get("operator_name"))) {
                	predicateList.add(cb.like(root.get("operator_name").as(String.class), "%"+(String)searchMap.get("operator_name")+"%"));
                }
                // 药品id
                if (searchMap.get("drug_id")!=null && !"".equals(searchMap.get("drug_id"))) {
                	predicateList.add(cb.like(root.get("drug_id").as(String.class), "%"+(String)searchMap.get("drug_id")+"%"));
                }
                // 用量
                if (searchMap.get("dosage")!=null && !"".equals(searchMap.get("dosage"))) {
                	predicateList.add(cb.like(root.get("dosage").as(String.class), "%"+(String)searchMap.get("dosage")+"%"));
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
