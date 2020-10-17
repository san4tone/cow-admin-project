package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.SuppliesDrugInfoDao;
import com.cosmoplat.cowfarm.pojo.SuppliesDrugInfo;
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
public class SuppliesDrugInfoService {

	@Autowired
	private SuppliesDrugInfoDao suppliesDrugInfoDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<SuppliesDrugInfo> findAll() {
		return suppliesDrugInfoDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SuppliesDrugInfo> findSearch(Map whereMap, int page, int size) {
		Specification<SuppliesDrugInfo> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return suppliesDrugInfoDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<SuppliesDrugInfo> findSearch(Map whereMap) {
		Specification<SuppliesDrugInfo> specification = createSpecification(whereMap);
		return suppliesDrugInfoDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public SuppliesDrugInfo findById(String id) {
		return suppliesDrugInfoDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param suppliesDrugInfo
	 */
	public void add(SuppliesDrugInfo suppliesDrugInfo) {
		suppliesDrugInfo.setId( idWorker.nextId()+"" );
		suppliesDrugInfoDao.save(suppliesDrugInfo);
	}

	/**
	 * 修改
	 * @param suppliesDrugInfo
	 */
	public void update(SuppliesDrugInfo suppliesDrugInfo) {
		suppliesDrugInfoDao.save(suppliesDrugInfo);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		suppliesDrugInfoDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<SuppliesDrugInfo> createSpecification(Map searchMap) {

		return new Specification<SuppliesDrugInfo>() {

			@Override
			public Predicate toPredicate(Root<SuppliesDrugInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 药品编号
                if (searchMap.get("drug_id")!=null && !"".equals(searchMap.get("drug_id"))) {
                	predicateList.add(cb.like(root.get("drug_id").as(String.class), "%"+(String)searchMap.get("drug_id")+"%"));
                }
                // 药品名称
                if (searchMap.get("drug_name")!=null && !"".equals(searchMap.get("drug_name"))) {
                	predicateList.add(cb.like(root.get("drug_name").as(String.class), "%"+(String)searchMap.get("drug_name")+"%"));
                }
                // 药品类别（str）
                if (searchMap.get("drug_type_str")!=null && !"".equals(searchMap.get("drug_type_str"))) {
                	predicateList.add(cb.like(root.get("drug_type_str").as(String.class), "%"+(String)searchMap.get("drug_type_str")+"%"));
                }
                // 规格
                if (searchMap.get("norms")!=null && !"".equals(searchMap.get("norms"))) {
                	predicateList.add(cb.like(root.get("norms").as(String.class), "%"+(String)searchMap.get("norms")+"%"));
                }
                // 生产厂家
                if (searchMap.get("manufacturer")!=null && !"".equals(searchMap.get("manufacturer"))) {
                	predicateList.add(cb.like(root.get("manufacturer").as(String.class), "%"+(String)searchMap.get("manufacturer")+"%"));
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
