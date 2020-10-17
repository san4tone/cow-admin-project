package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.SysRoleDao;
import com.cosmoplat.cowfarm.pojo.SysRole;
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
public class SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;

	@Autowired
	private IdWorker idWorker;

	public List<SysRole> findByUserId(String userId) {
		return sysRoleDao.findByUserId(userId);
	}

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<SysRole> findAll() {
		return sysRoleDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SysRole> findSearch(Map whereMap, int page, int size) {
		Specification<SysRole> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return sysRoleDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<SysRole> findSearch(Map whereMap) {
		Specification<SysRole> specification = createSpecification(whereMap);
		return sysRoleDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public SysRole findById(String id) {
		return sysRoleDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param sysRole
	 */
	public void add(SysRole sysRole) {
		sysRole.setRole_id( idWorker.nextId()+"" );
		sysRoleDao.save(sysRole);
	}

	/**
	 * 修改
	 * @param sysRole
	 */
	public void update(SysRole sysRole) {
		sysRoleDao.save(sysRole);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		sysRoleDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<SysRole> createSpecification(Map searchMap) {

		return new Specification<SysRole>() {

			@Override
			public Predicate toPredicate(Root<SysRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("role_id")!=null && !"".equals(searchMap.get("role_id"))) {
                	predicateList.add(cb.like(root.get("role_id").as(String.class), "%"+(String)searchMap.get("role_id")+"%"));
                }
                // 角色名称
                if (searchMap.get("role_name")!=null && !"".equals(searchMap.get("role_name"))) {
                	predicateList.add(cb.like(root.get("role_name").as(String.class), "%"+(String)searchMap.get("role_name")+"%"));
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
