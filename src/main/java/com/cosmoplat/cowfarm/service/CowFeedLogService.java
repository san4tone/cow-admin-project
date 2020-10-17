package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.common.response.PageResult;
import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dao.CowFeedLogDao;
import com.cosmoplat.cowfarm.dto.CowFeedLogReqDto;
import com.cosmoplat.cowfarm.dto.CowFeedLogRespDto;
import com.cosmoplat.cowfarm.mapper.CowFeedLogMapper;
import com.cosmoplat.cowfarm.pojo.CowFeedLog;
import com.cosmoplat.cowfarm.utils.DateUtils;
import com.cosmoplat.cowfarm.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
public class CowFeedLogService {

    @Autowired
    private CowFeedLogDao cowFeedLogDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CowFeedLogMapper cowFeedLogMapper;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<CowFeedLogRespDto> findAll() {
        return cowFeedLogMapper.findAllByStatus();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<CowFeedLog> findSearch(Map whereMap, int page, int size) {
        Specification<CowFeedLog> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return cowFeedLogDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<CowFeedLog> findSearch(Map whereMap) {
        Specification<CowFeedLog> specification = createSpecification(whereMap);
        return cowFeedLogDao.findAll(specification);
    }

    /**
     * 根据牛舍ID查询实体
     *
     * @param id
     * @return
     */
    public CowFeedLogRespDto findById(String id) {
        return cowFeedLogMapper.findByCowshedId(id);
    }

    /**
     * 增加
     *
     * @param cowFeedLog
     */
    public void add(CowFeedLog cowFeedLog) {
        cowFeedLog.setId(idWorker.nextId() + "");
        cowFeedLog.setStatus(1);
        cowFeedLog.setGmt_create(new Date());
        cowFeedLogDao.save(cowFeedLog);
    }

    /**
     * 修改
     *
     * @param cowFeedLog
     */
    public void update(CowFeedLog cowFeedLog) {
        cowFeedLogDao.save(cowFeedLog);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        cowFeedLogDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<CowFeedLog> createSpecification(Map searchMap) {

        return new Specification<CowFeedLog>() {

            @Override
            public Predicate toPredicate(Root<CowFeedLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 牛舍id
                if (searchMap.get("cowshed_id") != null && !"".equals(searchMap.get("cowshed_id"))) {
                    predicateList.add(cb.like(root.get("cowshed_id").as(String.class), "%" + (String) searchMap.get("cowshed_id") + "%"));
                }
                // 牛舍名称
                if (searchMap.get("cowshed_name") != null && !"".equals(searchMap.get("cowshed_name"))) {
                    predicateList.add(cb.like(root.get("cowshed_name").as(String.class), "%" + (String) searchMap.get("cowshed_name") + "%"));
                }
                // 执行人id
                if (searchMap.get("operator_id") != null && !"".equals(searchMap.get("operator_id"))) {
                    predicateList.add(cb.like(root.get("operator_id").as(String.class), "%" + (String) searchMap.get("operator_id") + "%"));
                }
                // 执行人名称
                if (searchMap.get("operator_name") != null && !"".equals(searchMap.get("operator_name"))) {
                    predicateList.add(cb.like(root.get("operator_name").as(String.class), "%" + (String) searchMap.get("operator_name") + "%"));
                }
                // 配方
                if (searchMap.get("feed_formula") != null && !"".equals(searchMap.get("feed_formula"))) {
                    predicateList.add(cb.like(root.get("feed_formula").as(String.class), "%" + (String) searchMap.get("feed_formula") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    /**
     * 分页+条件查询
     *
     * @param dto
     * @return
     */
    public Result searchList(CowFeedLogReqDto dto) {
        if (dto == null) {
            return new Result(false, StatusCode.ERROR, "无效参数");
        }
        dto.checkParam();
        if (StringUtils.isNotBlank(dto.getSearchDate())) {
            String[] arr = dto.getSearchDate().split(" 至 ");
            dto.setStartDate(DateUtils.stringToDate(arr[0], DateUtils.DATE_FORMAT));
            dto.setEndDate(DateUtils.stringToDate(arr[1], DateUtils.DATE_FORMAT));
        }
        List<CowFeedLogRespDto> datas = cowFeedLogMapper.selectBySelective(dto);
        int total = cowFeedLogMapper.countSelectBySelective(dto);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<CowFeedLogRespDto>((long) total, datas));
    }

    /**
     * 撤销
     * @param list
     * @return
     */
    public Result updateBatch(List<String> list) {
        cowFeedLogMapper.updateBatch(list);
        return new Result(true, StatusCode.OK, "撤销成功");
    }
}
