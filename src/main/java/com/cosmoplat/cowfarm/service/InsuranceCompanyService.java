package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.InsuranceCompanyDao;
import com.cosmoplat.cowfarm.pojo.InsuranceCompany;
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
public class InsuranceCompanyService {

	@Autowired
	private InsuranceCompanyDao insuranceCompanyDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<InsuranceCompany> findAll() {
		return insuranceCompanyDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<InsuranceCompany> findSearch(Map whereMap, int page, int size) {
		Specification<InsuranceCompany> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return insuranceCompanyDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<InsuranceCompany> findSearch(Map whereMap) {
		Specification<InsuranceCompany> specification = createSpecification(whereMap);
		return insuranceCompanyDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public InsuranceCompany findById(String id) {
		return insuranceCompanyDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param insuranceCompany
	 */
	public void add(InsuranceCompany insuranceCompany) {
		insuranceCompany.setId( idWorker.nextId()+"" );
		insuranceCompanyDao.save(insuranceCompany);
	}

	/**
	 * 修改
	 * @param insuranceCompany
	 */
	public void update(InsuranceCompany insuranceCompany) {
		insuranceCompanyDao.save(insuranceCompany);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		insuranceCompanyDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<InsuranceCompany> createSpecification(Map searchMap) {

		return new Specification<InsuranceCompany>() {

			@Override
			public Predicate toPredicate(Root<InsuranceCompany> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 公司名称
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 工商注册号
                if (searchMap.get("gongszch")!=null && !"".equals(searchMap.get("gongszch"))) {
                	predicateList.add(cb.like(root.get("gongszch").as(String.class), "%"+(String)searchMap.get("gongszch")+"%"));
                }
                // 网址
                if (searchMap.get("website")!=null && !"".equals(searchMap.get("website"))) {
                	predicateList.add(cb.like(root.get("website").as(String.class), "%"+(String)searchMap.get("website")+"%"));
                }
                // 省
                if (searchMap.get("province")!=null && !"".equals(searchMap.get("province"))) {
                	predicateList.add(cb.like(root.get("province").as(String.class), "%"+(String)searchMap.get("province")+"%"));
                }
                // 市
                if (searchMap.get("city")!=null && !"".equals(searchMap.get("city"))) {
                	predicateList.add(cb.like(root.get("city").as(String.class), "%"+(String)searchMap.get("city")+"%"));
                }
                // 邮编
                if (searchMap.get("zipcode")!=null && !"".equals(searchMap.get("zipcode"))) {
                	predicateList.add(cb.like(root.get("zipcode").as(String.class), "%"+(String)searchMap.get("zipcode")+"%"));
                }
                // 邮箱
                if (searchMap.get("mail")!=null && !"".equals(searchMap.get("mail"))) {
                	predicateList.add(cb.like(root.get("mail").as(String.class), "%"+(String)searchMap.get("mail")+"%"));
                }
                // 联系人
                if (searchMap.get("lianxr")!=null && !"".equals(searchMap.get("lianxr"))) {
                	predicateList.add(cb.like(root.get("lianxr").as(String.class), "%"+(String)searchMap.get("lianxr")+"%"));
                }
                // 联系电话
                if (searchMap.get("lianxdh")!=null && !"".equals(searchMap.get("lianxdh"))) {
                	predicateList.add(cb.like(root.get("lianxdh").as(String.class), "%"+(String)searchMap.get("lianxdh")+"%"));
                }
                // 地址
                if (searchMap.get("address")!=null && !"".equals(searchMap.get("address"))) {
                	predicateList.add(cb.like(root.get("address").as(String.class), "%"+(String)searchMap.get("address")+"%"));
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
