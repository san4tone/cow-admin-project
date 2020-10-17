package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.SysRoleMenuDao;
import com.cosmoplat.cowfarm.pojo.SysRoleMenu;
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
public class SysRoleMenuService {

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<SysRoleMenu> findAll() {
		return sysRoleMenuDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SysRoleMenu> findSearch(Map whereMap, int page, int size) {
		Specification<SysRoleMenu> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return sysRoleMenuDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<SysRoleMenu> findSearch(Map whereMap) {
		Specification<SysRoleMenu> specification = createSpecification(whereMap);
		return sysRoleMenuDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public SysRoleMenu findById(String id) {
		return sysRoleMenuDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param sysRoleMenu
	 */
	public void add(SysRoleMenu sysRoleMenu) {
		sysRoleMenu.setId( idWorker.nextId()+"" );
		sysRoleMenuDao.save(sysRoleMenu);
	}

	/**
	 * 修改
	 * @param sysRoleMenu
	 */
	public void update(SysRoleMenu sysRoleMenu) {
		sysRoleMenuDao.save(sysRoleMenu);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		sysRoleMenuDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<SysRoleMenu> createSpecification(Map searchMap) {

		return new Specification<SysRoleMenu>() {

			@Override
			public Predicate toPredicate(Root<SysRoleMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 角色ID
                if (searchMap.get("role_id")!=null && !"".equals(searchMap.get("role_id"))) {
                	predicateList.add(cb.like(root.get("role_id").as(String.class), "%"+(String)searchMap.get("role_id")+"%"));
                }
                // 菜单ID
                if (searchMap.get("menu_id")!=null && !"".equals(searchMap.get("menu_id"))) {
                	predicateList.add(cb.like(root.get("menu_id").as(String.class), "%"+(String)searchMap.get("menu_id")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
