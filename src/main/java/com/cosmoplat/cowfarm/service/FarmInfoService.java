package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.FarmInfoDao;
import com.cosmoplat.cowfarm.pojo.FarmInfo;
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
public class FarmInfoService {

	@Autowired
	private FarmInfoDao farmInfoDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<FarmInfo> findAll() {
		return farmInfoDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<FarmInfo> findSearch(Map whereMap, int page, int size) {
		Specification<FarmInfo> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return farmInfoDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<FarmInfo> findSearch(Map whereMap) {
		Specification<FarmInfo> specification = createSpecification(whereMap);
		return farmInfoDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public FarmInfo findById(String id) {
		return farmInfoDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param farmInfo
	 */
	public void add(FarmInfo farmInfo) {
		farmInfo.setFarm_id( idWorker.nextId()+"" );
		farmInfoDao.save(farmInfo);
	}

	/**
	 * 修改
	 * @param farmInfo
	 */
	public void update(FarmInfo farmInfo) {
		farmInfoDao.save(farmInfo);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		farmInfoDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<FarmInfo> createSpecification(Map searchMap) {

		return new Specification<FarmInfo>() {

			@Override
			public Predicate toPredicate(Root<FarmInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 牛场id
                if (searchMap.get("farm_id")!=null && !"".equals(searchMap.get("farm_id"))) {
                	predicateList.add(cb.like(root.get("farm_id").as(String.class), "%"+(String)searchMap.get("farm_id")+"%"));
                }
                // 标准场号
                if (searchMap.get("standard_id")!=null && !"".equals(searchMap.get("standard_id"))) {
                	predicateList.add(cb.like(root.get("standard_id").as(String.class), "%"+(String)searchMap.get("standard_id")+"%"));
                }
                // 牛场名称
                if (searchMap.get("farm_name")!=null && !"".equals(searchMap.get("farm_name"))) {
                	predicateList.add(cb.like(root.get("farm_name").as(String.class), "%"+(String)searchMap.get("farm_name")+"%"));
                }
                // 负责人
                if (searchMap.get("principal")!=null && !"".equals(searchMap.get("principal"))) {
                	predicateList.add(cb.like(root.get("principal").as(String.class), "%"+(String)searchMap.get("principal")+"%"));
                }
                // 手机号
                if (searchMap.get("mobile")!=null && !"".equals(searchMap.get("mobile"))) {
                	predicateList.add(cb.like(root.get("mobile").as(String.class), "%"+(String)searchMap.get("mobile")+"%"));
                }
                // 省
                if (searchMap.get("province")!=null && !"".equals(searchMap.get("province"))) {
                	predicateList.add(cb.like(root.get("province").as(String.class), "%"+(String)searchMap.get("province")+"%"));
                }
                // 市
                if (searchMap.get("city")!=null && !"".equals(searchMap.get("city"))) {
                	predicateList.add(cb.like(root.get("city").as(String.class), "%"+(String)searchMap.get("city")+"%"));
                }
                // 县/区
                if (searchMap.get("country")!=null && !"".equals(searchMap.get("country"))) {
                	predicateList.add(cb.like(root.get("country").as(String.class), "%"+(String)searchMap.get("country")+"%"));
                }
                // 乡镇
                if (searchMap.get("township")!=null && !"".equals(searchMap.get("township"))) {
                	predicateList.add(cb.like(root.get("township").as(String.class), "%"+(String)searchMap.get("township")+"%"));
                }
                // 村庄
                if (searchMap.get("village")!=null && !"".equals(searchMap.get("village"))) {
                	predicateList.add(cb.like(root.get("village").as(String.class), "%"+(String)searchMap.get("village")+"%"));
                }
                // 经度
                if (searchMap.get("longitude")!=null && !"".equals(searchMap.get("longitude"))) {
                	predicateList.add(cb.like(root.get("longitude").as(String.class), "%"+(String)searchMap.get("longitude")+"%"));
                }
                // 纬度
                if (searchMap.get("latitude")!=null && !"".equals(searchMap.get("latitude"))) {
                	predicateList.add(cb.like(root.get("latitude").as(String.class), "%"+(String)searchMap.get("latitude")+"%"));
                }
                // 距离
                if (searchMap.get("distance")!=null && !"".equals(searchMap.get("distance"))) {
                	predicateList.add(cb.like(root.get("distance").as(String.class), "%"+(String)searchMap.get("distance")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
