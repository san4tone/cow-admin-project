package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.WareInfoDao;
import com.cosmoplat.cowfarm.pojo.WareInfo;
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
public class WareInfoService {

	@Autowired
	private WareInfoDao wareInfoDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<WareInfo> findAll() {
		return wareInfoDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<WareInfo> findSearch(Map whereMap, int page, int size) {
		Specification<WareInfo> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return wareInfoDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<WareInfo> findSearch(Map whereMap) {
		Specification<WareInfo> specification = createSpecification(whereMap);
		return wareInfoDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public WareInfo findById(String id) {
		return wareInfoDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param wareInfo
	 */
	public void add(WareInfo wareInfo) {
		wareInfo.setWare_id( idWorker.nextId()+"" );
		wareInfoDao.save(wareInfo);
	}

	/**
	 * 修改
	 * @param wareInfo
	 */
	public void update(WareInfo wareInfo) {
		wareInfoDao.save(wareInfo);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		wareInfoDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<WareInfo> createSpecification(Map searchMap) {

		return new Specification<WareInfo>() {

			@Override
			public Predicate toPredicate(Root<WareInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 仓库id
                if (searchMap.get("ware_id")!=null && !"".equals(searchMap.get("ware_id"))) {
                	predicateList.add(cb.like(root.get("ware_id").as(String.class), "%"+(String)searchMap.get("ware_id")+"%"));
                }
                // 仓库名称
                if (searchMap.get("ware_name")!=null && !"".equals(searchMap.get("ware_name"))) {
                	predicateList.add(cb.like(root.get("ware_name").as(String.class), "%"+(String)searchMap.get("ware_name")+"%"));
                }
                // 仓库类型（str）
                if (searchMap.get("ware_type_str")!=null && !"".equals(searchMap.get("ware_type_str"))) {
                	predicateList.add(cb.like(root.get("ware_type_str").as(String.class), "%"+(String)searchMap.get("ware_type_str")+"%"));
                }
                // 仓库状态（str）
                if (searchMap.get("ware_state_str")!=null && !"".equals(searchMap.get("ware_state_str"))) {
                	predicateList.add(cb.like(root.get("ware_state_str").as(String.class), "%"+(String)searchMap.get("ware_state_str")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
