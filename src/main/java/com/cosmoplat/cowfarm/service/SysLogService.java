package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.SysLogDao;
import com.cosmoplat.cowfarm.pojo.SysLog;
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
public class SysLogService {

	@Autowired
	private SysLogDao sysLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<SysLog> findAll() {
		return sysLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SysLog> findSearch(Map whereMap, int page, int size) {
		Specification<SysLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return sysLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<SysLog> findSearch(Map whereMap) {
		Specification<SysLog> specification = createSpecification(whereMap);
		return sysLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public SysLog findById(String id) {
		return sysLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param sysLog
	 */
	public void add(SysLog sysLog) {
		sysLog.setId( idWorker.nextId()+"" );
		sysLogDao.save(sysLog);
	}

	/**
	 * 修改
	 * @param sysLog
	 */
	public void update(SysLog sysLog) {
		sysLogDao.save(sysLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		sysLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<SysLog> createSpecification(Map searchMap) {

		return new Specification<SysLog>() {

			@Override
			public Predicate toPredicate(Root<SysLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 用户名
                if (searchMap.get("username")!=null && !"".equals(searchMap.get("username"))) {
                	predicateList.add(cb.like(root.get("username").as(String.class), "%"+(String)searchMap.get("username")+"%"));
                }
                // 用户操作
                if (searchMap.get("operation")!=null && !"".equals(searchMap.get("operation"))) {
                	predicateList.add(cb.like(root.get("operation").as(String.class), "%"+(String)searchMap.get("operation")+"%"));
                }
                // 请求方法
                if (searchMap.get("method")!=null && !"".equals(searchMap.get("method"))) {
                	predicateList.add(cb.like(root.get("method").as(String.class), "%"+(String)searchMap.get("method")+"%"));
                }
                // 请求参数
                if (searchMap.get("params")!=null && !"".equals(searchMap.get("params"))) {
                	predicateList.add(cb.like(root.get("params").as(String.class), "%"+(String)searchMap.get("params")+"%"));
                }
                // IP地址
                if (searchMap.get("ip")!=null && !"".equals(searchMap.get("ip"))) {
                	predicateList.add(cb.like(root.get("ip").as(String.class), "%"+(String)searchMap.get("ip")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
