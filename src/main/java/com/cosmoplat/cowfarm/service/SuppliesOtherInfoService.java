package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.SuppliesOtherInfoDao;
import com.cosmoplat.cowfarm.pojo.SuppliesOtherInfo;
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
public class SuppliesOtherInfoService {

	@Autowired
	private SuppliesOtherInfoDao suppliesOtherInfoDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<SuppliesOtherInfo> findAll() {
		return suppliesOtherInfoDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SuppliesOtherInfo> findSearch(Map whereMap, int page, int size) {
		Specification<SuppliesOtherInfo> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return suppliesOtherInfoDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<SuppliesOtherInfo> findSearch(Map whereMap) {
		Specification<SuppliesOtherInfo> specification = createSpecification(whereMap);
		return suppliesOtherInfoDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public SuppliesOtherInfo findById(String id) {
		return suppliesOtherInfoDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param suppliesOtherInfo
	 */
	public void add(SuppliesOtherInfo suppliesOtherInfo) {
		suppliesOtherInfo.setId( idWorker.nextId()+"" );
		suppliesOtherInfoDao.save(suppliesOtherInfo);
	}

	/**
	 * 修改
	 * @param suppliesOtherInfo
	 */
	public void update(SuppliesOtherInfo suppliesOtherInfo) {
		suppliesOtherInfoDao.save(suppliesOtherInfo);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		suppliesOtherInfoDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<SuppliesOtherInfo> createSpecification(Map searchMap) {

		return new Specification<SuppliesOtherInfo>() {

			@Override
			public Predicate toPredicate(Root<SuppliesOtherInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 耗材名称
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 耗材编号
                if (searchMap.get("no")!=null && !"".equals(searchMap.get("no"))) {
                	predicateList.add(cb.like(root.get("no").as(String.class), "%"+(String)searchMap.get("no")+"%"));
                }
                // 耗材类别（str）
                if (searchMap.get("type_str")!=null && !"".equals(searchMap.get("type_str"))) {
                	predicateList.add(cb.like(root.get("type_str").as(String.class), "%"+(String)searchMap.get("type_str")+"%"));
                }
                // 规格
                if (searchMap.get("norms")!=null && !"".equals(searchMap.get("norms"))) {
                	predicateList.add(cb.like(root.get("norms").as(String.class), "%"+(String)searchMap.get("norms")+"%"));
                }
                // 存储单位（str）
                if (searchMap.get("unit_str")!=null && !"".equals(searchMap.get("unit_str"))) {
                	predicateList.add(cb.like(root.get("unit_str").as(String.class), "%"+(String)searchMap.get("unit_str")+"%"));
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
