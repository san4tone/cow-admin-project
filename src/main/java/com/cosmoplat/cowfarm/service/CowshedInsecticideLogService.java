package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CowshedInsecticideLogDao;
import com.cosmoplat.cowfarm.pojo.CowshedInsecticideLog;
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
public class CowshedInsecticideLogService {

	@Autowired
	private CowshedInsecticideLogDao cowshedInsecticideLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<CowshedInsecticideLog> findAll() {
		return cowshedInsecticideLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CowshedInsecticideLog> findSearch(Map whereMap, int page, int size) {
		Specification<CowshedInsecticideLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return cowshedInsecticideLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<CowshedInsecticideLog> findSearch(Map whereMap) {
		Specification<CowshedInsecticideLog> specification = createSpecification(whereMap);
		return cowshedInsecticideLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public CowshedInsecticideLog findById(String id) {
		return cowshedInsecticideLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param cowshedInsecticideLog
	 */
	public void add(CowshedInsecticideLog cowshedInsecticideLog) {
		cowshedInsecticideLog.setId( idWorker.nextId()+"" );
		cowshedInsecticideLogDao.save(cowshedInsecticideLog);
	}

	/**
	 * 修改
	 * @param cowshedInsecticideLog
	 */
	public void update(CowshedInsecticideLog cowshedInsecticideLog) {
		cowshedInsecticideLogDao.save(cowshedInsecticideLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		cowshedInsecticideLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<CowshedInsecticideLog> createSpecification(Map searchMap) {

		return new Specification<CowshedInsecticideLog>() {

			@Override
			public Predicate toPredicate(Root<CowshedInsecticideLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
                // 药品id
                if (searchMap.get("drug_id")!=null && !"".equals(searchMap.get("drug_id"))) {
                	predicateList.add(cb.like(root.get("drug_id").as(String.class), "%"+(String)searchMap.get("drug_id")+"%"));
                }
                // 用量
                if (searchMap.get("dosage")!=null && !"".equals(searchMap.get("dosage"))) {
                	predicateList.add(cb.like(root.get("dosage").as(String.class), "%"+(String)searchMap.get("dosage")+"%"));
                }
                // 执行人id
                if (searchMap.get("operator_id")!=null && !"".equals(searchMap.get("operator_id"))) {
                	predicateList.add(cb.like(root.get("operator_id").as(String.class), "%"+(String)searchMap.get("operator_id")+"%"));
                }
                // 执行人姓名
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
