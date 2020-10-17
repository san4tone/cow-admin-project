package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.common.response.PageResult;
import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dao.CowHealthLogDao;
import com.cosmoplat.cowfarm.dto.IllnessReqDto;
import com.cosmoplat.cowfarm.mapper.CowHealthLogMapper;
import com.cosmoplat.cowfarm.pojo.CowHealthLog;
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
public class CowHealthLogService {

    @Autowired
    private CowHealthLogDao cowHealthLogDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CowHealthLogMapper cowHealthLogMapper;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<CowHealthLog> findAll() {
        return cowHealthLogMapper.findAllByStatus();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<CowHealthLog> findSearch(Map whereMap, int page, int size) {
        Specification<CowHealthLog> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return cowHealthLogDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<CowHealthLog> findSearch(Map whereMap) {
        Specification<CowHealthLog> specification = createSpecification(whereMap);
        return cowHealthLogDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public CowHealthLog findById(String id) {
        return cowHealthLogMapper.findByIdAndStatus(id);
    }

    /**
     * 增加
     *
     * @param cowHealthLog
     */
    public void add(CowHealthLog cowHealthLog) {
        cowHealthLog.setId(idWorker.nextId() + "");
        cowHealthLog.setStatus(1);
        cowHealthLog.setGmt_create(new Date());
        cowHealthLogDao.save(cowHealthLog);
    }

    /**
     * 修改
     *
     * @param cowHealthLog
     */
    public void update(CowHealthLog cowHealthLog) {
        cowHealthLogDao.save(cowHealthLog);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
//        cowHealthLogDao.deleteById(id);
        cowHealthLogMapper.updateStatusById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<CowHealthLog> createSpecification(Map searchMap) {

        return new Specification<CowHealthLog>() {

            @Override
            public Predicate toPredicate(Root<CowHealthLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 牛号
                if (searchMap.get("cow_id") != null && !"".equals(searchMap.get("cow_id"))) {
                    predicateList.add(cb.like(root.get("cow_id").as(String.class), "%" + (String) searchMap.get("cow_id") + "%"));
                }
                // 牛舍编号
                if (searchMap.get("cowshed_id") != null && !"".equals(searchMap.get("cowshed_id"))) {
                    predicateList.add(cb.like(root.get("cowshed_id").as(String.class), "%" + (String) searchMap.get("cowshed_id") + "%"));
                }
                // 疾病名称
                if (searchMap.get("disease_name") != null && !"".equals(searchMap.get("disease_name"))) {
                    predicateList.add(cb.like(root.get("disease_name").as(String.class), "%" + (String) searchMap.get("disease_name") + "%"));
                }
                // 处方id
                if (searchMap.get("prescription") != null && !"".equals(searchMap.get("prescription"))) {
                    predicateList.add(cb.like(root.get("prescription").as(String.class), "%" + (String) searchMap.get("prescription") + "%"));
                }
                // 执行人id
                if (searchMap.get("operator_id") != null && !"".equals(searchMap.get("operator_id"))) {
                    predicateList.add(cb.like(root.get("operator_id").as(String.class), "%" + (String) searchMap.get("operator_id") + "%"));
                }
                // 执行人姓名
                if (searchMap.get("operator_name") != null && !"".equals(searchMap.get("operator_name"))) {
                    predicateList.add(cb.like(root.get("operator_name").as(String.class), "%" + (String) searchMap.get("operator_name") + "%"));
                }
                // 调往牛舍
                if (searchMap.get("to_cowshed_id") != null && !"".equals(searchMap.get("to_cowshed_id"))) {
                    predicateList.add(cb.like(root.get("to_cowshed_id").as(String.class), "%" + (String) searchMap.get("to_cowshed_id") + "%"));
                }
                // 备注
                if (searchMap.get("remark") != null && !"".equals(searchMap.get("remark"))) {
                    predicateList.add(cb.like(root.get("remark").as(String.class), "%" + (String) searchMap.get("remark") + "%"));
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
    public Result searchList(IllnessReqDto dto) {
        if (dto == null) {
            return new Result(false, StatusCode.ERROR, "无效参数");
        }
        dto.checkParam();
        if (StringUtils.isNotBlank(dto.getSearchDate())) {
            String[] arr = dto.getSearchDate().split(" 至 ");
            dto.setStartDate(DateUtils.stringToDate(arr[0], DateUtils.DATE_FORMAT));
            dto.setEndDate(DateUtils.stringToDate(arr[1], DateUtils.DATE_FORMAT));
        }
        List<CowHealthLog> datas = cowHealthLogMapper.selectBySelective(dto);
        int total = cowHealthLogMapper.countSelectBySelective(dto);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<CowHealthLog>((long) total, datas));
    }

    /**
     * 修改
     *
     * @param id
     * @param cowHealthLog
     * @return
     */
    public Result updateCowInfo(String id, CowHealthLog cowHealthLog) {
        CowHealthLog one = this.findById(id);
        if (one == null) {
            return new Result(false, StatusCode.ERROR, "该记录不存在");
        }
        // 修改
        if (StringUtils.isNotBlank(cowHealthLog.getCow_id())) {
            one.setCow_id(cowHealthLog.getCow_id());
        }
        if (StringUtils.isNotBlank(cowHealthLog.getCowshed_id())) {
            one.setCowshed_id(cowHealthLog.getCowshed_id());
        }
        if (cowHealthLog.getIllness_id() != null) {
            one.setIllness_id(cowHealthLog.getIllness_id());
        }
        if (StringUtils.isNotBlank(cowHealthLog.getIllness_name())) {
            one.setIllness_name(cowHealthLog.getIllness_name());
        }
        if (cowHealthLog.getDisease_date() != null) {
            one.setDisease_date(cowHealthLog.getDisease_date());
        }
        if (StringUtils.isNotBlank(cowHealthLog.getPrescription())) {
            one.setPrescription(cowHealthLog.getPrescription());
        }
        if (cowHealthLog.getTreatment_effect() != null) {
            one.setTreatment_effect(cowHealthLog.getTreatment_effect());
        }
        if (StringUtils.isNotBlank(cowHealthLog.getOperator_id())) {
            one.setOperator_id(cowHealthLog.getOperator_id());
        }
        if (StringUtils.isNotBlank(cowHealthLog.getOperator_name())) {
            one.setOperator_name(cowHealthLog.getOperator_name());
        }
        if (StringUtils.isNotBlank(cowHealthLog.getTo_cowshed_id())) {
            one.setTo_cowshed_id(cowHealthLog.getTo_cowshed_id());
        }
        if (StringUtils.isNotBlank(cowHealthLog.getRemark())) {
            one.setRemark(cowHealthLog.getRemark());
        }
        one.setStatus(1);
        one.setGmt_modified(new Date());
        CowHealthLog save = cowHealthLogDao.save(one);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 批量删除（逻辑删除）
     *
     * @param list
     * @return
     */
    public Result updateBatch(List<String> list) {
        cowHealthLogMapper.updateBatch(list);
        return new Result(true, StatusCode.OK, "批量删除成功");
    }
}
