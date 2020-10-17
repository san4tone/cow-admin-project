package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.SysUserRoleDao;
import com.cosmoplat.cowfarm.pojo.SysUserRole;
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
public class SysUserRoleService {

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<SysUserRole> findAll() {
		return sysUserRoleDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SysUserRole> findSearch(Map whereMap, int page, int size) {
		Specification<SysUserRole> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return sysUserRoleDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<SysUserRole> findSearch(Map whereMap) {
		Specification<SysUserRole> specification = createSpecification(whereMap);
		return sysUserRoleDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public SysUserRole findById(String id) {
		return sysUserRoleDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param sysUserRole
	 */
	public void add(SysUserRole sysUserRole) {
		sysUserRole.setId( idWorker.nextId()+"" );
		sysUserRoleDao.save(sysUserRole);
	}

	/**
	 * 修改
	 * @param sysUserRole
	 */
	public void update(SysUserRole sysUserRole) {
		sysUserRoleDao.save(sysUserRole);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		sysUserRoleDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<SysUserRole> createSpecification(Map searchMap) {

		return new Specification<SysUserRole>() {

			@Override
			public Predicate toPredicate(Root<SysUserRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
