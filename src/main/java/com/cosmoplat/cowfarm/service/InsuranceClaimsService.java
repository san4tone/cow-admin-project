package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.InsuranceClaimsDao;
import com.cosmoplat.cowfarm.pojo.InsuranceClaims;
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
public class InsuranceClaimsService {

	@Autowired
	private InsuranceClaimsDao insuranceClaimsDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<InsuranceClaims> findAll() {
		return insuranceClaimsDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<InsuranceClaims> findSearch(Map whereMap, int page, int size) {
		Specification<InsuranceClaims> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return insuranceClaimsDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<InsuranceClaims> findSearch(Map whereMap) {
		Specification<InsuranceClaims> specification = createSpecification(whereMap);
		return insuranceClaimsDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public InsuranceClaims findById(String id) {
		return insuranceClaimsDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param insuranceClaims
	 */
	public void add(InsuranceClaims insuranceClaims) {
		insuranceClaims.setId( idWorker.nextId()+"" );
		insuranceClaimsDao.save(insuranceClaims);
	}

	/**
	 * 修改
	 * @param insuranceClaims
	 */
	public void update(InsuranceClaims insuranceClaims) {
		insuranceClaimsDao.save(insuranceClaims);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		insuranceClaimsDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<InsuranceClaims> createSpecification(Map searchMap) {

		return new Specification<InsuranceClaims>() {

			@Override
			public Predicate toPredicate(Root<InsuranceClaims> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 牛号
                if (searchMap.get("cow_id")!=null && !"".equals(searchMap.get("cow_id"))) {
                	predicateList.add(cb.like(root.get("cow_id").as(String.class), "%"+(String)searchMap.get("cow_id")+"%"));
                }
                // 牛舍
                if (searchMap.get("cowshed_id")!=null && !"".equals(searchMap.get("cowshed_id"))) {
                	predicateList.add(cb.like(root.get("cowshed_id").as(String.class), "%"+(String)searchMap.get("cowshed_id")+"%"));
                }
                // 赔/退款凭证号
                if (searchMap.get("voucher_no")!=null && !"".equals(searchMap.get("voucher_no"))) {
                	predicateList.add(cb.like(root.get("voucher_no").as(String.class), "%"+(String)searchMap.get("voucher_no")+"%"));
                }
                // 电子保险号/生物识别码
                if (searchMap.get("electricbxh")!=null && !"".equals(searchMap.get("electricbxh"))) {
                	predicateList.add(cb.like(root.get("electricbxh").as(String.class), "%"+(String)searchMap.get("electricbxh")+"%"));
                }
                // 保单号
                if (searchMap.get("policy_no")!=null && !"".equals(searchMap.get("policy_no"))) {
                	predicateList.add(cb.like(root.get("policy_no").as(String.class), "%"+(String)searchMap.get("policy_no")+"%"));
                }
                // 登记人
                if (searchMap.get("registrar")!=null && !"".equals(searchMap.get("registrar"))) {
                	predicateList.add(cb.like(root.get("registrar").as(String.class), "%"+(String)searchMap.get("registrar")+"%"));
                }
                // 赔付金额
                if (searchMap.get("compensate")!=null && !"".equals(searchMap.get("compensate"))) {
                	predicateList.add(cb.like(root.get("compensate").as(String.class), "%"+(String)searchMap.get("compensate")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
