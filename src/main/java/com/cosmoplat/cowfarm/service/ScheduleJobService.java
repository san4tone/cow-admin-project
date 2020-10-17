package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.ScheduleJobDao;
import com.cosmoplat.cowfarm.pojo.ScheduleJob;
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
public class ScheduleJobService {

	@Autowired
	private ScheduleJobDao scheduleJobDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<ScheduleJob> findAll() {
		return scheduleJobDao.findAll();
	}


	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<ScheduleJob> findSearch(Map whereMap, int page, int size) {
		Specification<ScheduleJob> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return scheduleJobDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<ScheduleJob> findSearch(Map whereMap) {
		Specification<ScheduleJob> specification = createSpecification(whereMap);
		return scheduleJobDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public ScheduleJob findById(String id) {
		return scheduleJobDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param scheduleJob
	 */
	public void add(ScheduleJob scheduleJob) {
		scheduleJob.setJob_id( idWorker.nextId()+"" );
		scheduleJobDao.save(scheduleJob);
	}

	/**
	 * 修改
	 * @param scheduleJob
	 */
	public void update(ScheduleJob scheduleJob) {
		scheduleJobDao.save(scheduleJob);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		scheduleJobDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<ScheduleJob> createSpecification(Map searchMap) {

		return new Specification<ScheduleJob>() {

			@Override
			public Predicate toPredicate(Root<ScheduleJob> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 任务id
                if (searchMap.get("job_id")!=null && !"".equals(searchMap.get("job_id"))) {
                	predicateList.add(cb.like(root.get("job_id").as(String.class), "%"+(String)searchMap.get("job_id")+"%"));
                }
                // spring bean名称
                if (searchMap.get("bean_name")!=null && !"".equals(searchMap.get("bean_name"))) {
                	predicateList.add(cb.like(root.get("bean_name").as(String.class), "%"+(String)searchMap.get("bean_name")+"%"));
                }
                // 参数
                if (searchMap.get("params")!=null && !"".equals(searchMap.get("params"))) {
                	predicateList.add(cb.like(root.get("params").as(String.class), "%"+(String)searchMap.get("params")+"%"));
                }
                // cron表达式
                if (searchMap.get("cron_expression")!=null && !"".equals(searchMap.get("cron_expression"))) {
                	predicateList.add(cb.like(root.get("cron_expression").as(String.class), "%"+(String)searchMap.get("cron_expression")+"%"));
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
