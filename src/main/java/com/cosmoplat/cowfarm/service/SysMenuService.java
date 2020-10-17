package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.SysMenuDao;
import com.cosmoplat.cowfarm.pojo.SysMenu;
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
public class SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<SysMenu> findAll() {
		return sysMenuDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SysMenu> findSearch(Map whereMap, int page, int size) {
		Specification<SysMenu> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return sysMenuDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<SysMenu> findSearch(Map whereMap) {
		Specification<SysMenu> specification = createSpecification(whereMap);
		return sysMenuDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public SysMenu findById(String id) {
		return sysMenuDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param sysMenu
	 */
	public void add(SysMenu sysMenu) {
		sysMenu.setMenu_id( idWorker.nextId()+"" );
		sysMenuDao.save(sysMenu);
	}

	/**
	 * 修改
	 * @param sysMenu
	 */
	public void update(SysMenu sysMenu) {
		sysMenuDao.save(sysMenu);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		sysMenuDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<SysMenu> createSpecification(Map searchMap) {

		return new Specification<SysMenu>() {

			@Override
			public Predicate toPredicate(Root<SysMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("menu_id")!=null && !"".equals(searchMap.get("menu_id"))) {
                	predicateList.add(cb.like(root.get("menu_id").as(String.class), "%"+(String)searchMap.get("menu_id")+"%"));
                }
                // 父菜单ID，一级菜单为0
                if (searchMap.get("parent_id")!=null && !"".equals(searchMap.get("parent_id"))) {
                	predicateList.add(cb.like(root.get("parent_id").as(String.class), "%"+(String)searchMap.get("parent_id")+"%"));
                }
                // 菜单名称
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 菜单URL
                if (searchMap.get("url")!=null && !"".equals(searchMap.get("url"))) {
                	predicateList.add(cb.like(root.get("url").as(String.class), "%"+(String)searchMap.get("url")+"%"));
                }
                // 授权(多个用逗号分隔，如：user:list,user:create)
                if (searchMap.get("perms")!=null && !"".equals(searchMap.get("perms"))) {
                	predicateList.add(cb.like(root.get("perms").as(String.class), "%"+(String)searchMap.get("perms")+"%"));
                }
                // 菜单图标
                if (searchMap.get("icon")!=null && !"".equals(searchMap.get("icon"))) {
                	predicateList.add(cb.like(root.get("icon").as(String.class), "%"+(String)searchMap.get("icon")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
