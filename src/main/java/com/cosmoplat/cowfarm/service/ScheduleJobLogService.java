package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.ScheduleJobLogDao;
import com.cosmoplat.cowfarm.pojo.ScheduleJobLog;
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
public class ScheduleJobLogService {

	@Autowired
	private ScheduleJobLogDao scheduleJobLogDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<ScheduleJobLog> findAll() {
		return scheduleJobLogDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<ScheduleJobLog> findSearch(Map whereMap, int page, int size) {
		Specification<ScheduleJobLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return scheduleJobLogDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<ScheduleJobLog> findSearch(Map whereMap) {
		Specification<ScheduleJobLog> specification = createSpecification(whereMap);
		return scheduleJobLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public ScheduleJobLog findById(String id) {
		return scheduleJobLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param scheduleJobLog
	 */
	public void add(ScheduleJobLog scheduleJobLog) {
		scheduleJobLog.setJob_id( idWorker.nextId()+"" );
		scheduleJobLogDao.save(scheduleJobLog);
	}

	/**
	 * 修改
	 * @param scheduleJobLog
	 */
	public void update(ScheduleJobLog scheduleJobLog) {
		scheduleJobLogDao.save(scheduleJobLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		scheduleJobLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<ScheduleJobLog> createSpecification(Map searchMap) {

		return new Specification<ScheduleJobLog>() {

			@Override
			public Predicate toPredicate(Root<ScheduleJobLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 任务日志id
                if (searchMap.get("log_id")!=null && !"".equals(searchMap.get("log_id"))) {
                	predicateList.add(cb.like(root.get("log_id").as(String.class), "%"+(String)searchMap.get("log_id")+"%"));
                }
                // spring bean名称
                if (searchMap.get("bean_name")!=null && !"".equals(searchMap.get("bean_name"))) {
                	predicateList.add(cb.like(root.get("bean_name").as(String.class), "%"+(String)searchMap.get("bean_name")+"%"));
                }
                // 参数
                if (searchMap.get("params")!=null && !"".equals(searchMap.get("params"))) {
                	predicateList.add(cb.like(root.get("params").as(String.class), "%"+(String)searchMap.get("params")+"%"));
                }
                // 失败信息
                if (searchMap.get("error")!=null && !"".equals(searchMap.get("error"))) {
                	predicateList.add(cb.like(root.get("error").as(String.class), "%"+(String)searchMap.get("error")+"%"));
                }

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
