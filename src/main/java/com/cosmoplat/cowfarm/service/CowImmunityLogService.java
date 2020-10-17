package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.common.response.PageResult;
import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dao.CowImmunityLogDao;
import com.cosmoplat.cowfarm.dto.ImmunityReqDto;
import com.cosmoplat.cowfarm.mapper.CowImmunityLogMapper;
import com.cosmoplat.cowfarm.pojo.CowImmunityLog;
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
public class CowImmunityLogService {

    @Autowired
    private CowImmunityLogDao cowImmunityLogDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CowImmunityLogMapper cowImmunityLogMapper;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<CowImmunityLog> findAll() {
        return cowImmunityLogMapper.findAllByStatus();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<CowImmunityLog> findSearch(Map whereMap, int page, int size) {
        Specification<CowImmunityLog> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return cowImmunityLogDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<CowImmunityLog> findSearch(Map whereMap) {
        Specification<CowImmunityLog> specification = createSpecification(whereMap);
        return cowImmunityLogDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public CowImmunityLog findById(String id) {
        return cowImmunityLogMapper.findByIdAndStatus(id);
    }

    /**
     * 增加
     *
     * @param cowImmunityLog
     */
    public void add(CowImmunityLog cowImmunityLog) {
        cowImmunityLog.setId(idWorker.nextId() + "");
        cowImmunityLog.setStatus(1);
        cowImmunityLog.setGmt_create(new Date());
        cowImmunityLogDao.save(cowImmunityLog);
    }

    /**
     * 修改
     *
     * @param id
     * @param cowImmunityLog
     */
    public Result update(String id, CowImmunityLog cowImmunityLog) {
        CowImmunityLog one = this.findById(id);
        if (one == null) {
            return new Result(false, StatusCode.ERROR, "该记录不存在");
        }
        // 修改
        if (StringUtils.isNotBlank(cowImmunityLog.getCow_id())) {
            one.setCow_id(cowImmunityLog.getCow_id());
        }
        if (StringUtils.isNotBlank(cowImmunityLog.getCowshed_id())) {
            one.setCowshed_id(cowImmunityLog.getCowshed_id());
        }
        if (cowImmunityLog.getCow_type() != null) {
            one.setCow_type(cowImmunityLog.getCow_type());
        }
        if (cowImmunityLog.getImmune_mode() != null) {
            one.setImmune_mode(cowImmunityLog.getImmune_mode());
        }
        if (cowImmunityLog.getImmune_name() != null) {
            one.setImmune_name(cowImmunityLog.getImmune_name());
        }
        if (StringUtils.isNotBlank(cowImmunityLog.getVaccine_name())) {
            one.setVaccine_name(cowImmunityLog.getVaccine_name());
        }
        if (StringUtils.isNotBlank(cowImmunityLog.getInjection_volume())) {
            one.setInjection_volume(cowImmunityLog.getInjection_volume());
        }
        if (StringUtils.isNotBlank(cowImmunityLog.getBatch_num())) {
            one.setBatch_num(cowImmunityLog.getBatch_num());
        }
        if (cowImmunityLog.getImmune_date() != null) {
            one.setImmune_date(cowImmunityLog.getImmune_date());
        }
        if (StringUtils.isNotBlank(cowImmunityLog.getOperator_id())) {
            one.setOperator_id(cowImmunityLog.getOperator_id());
        }
        if (StringUtils.isNotBlank(cowImmunityLog.getOperator_name())) {
            one.setOperator_name(cowImmunityLog.getOperator_name());
        }
        if (StringUtils.isNotBlank(cowImmunityLog.getRemark())) {
            one.setRemark(cowImmunityLog.getRemark());
        }
        one.setStatus(1);
        one.setGmt_modified(new Date());
        CowImmunityLog save = cowImmunityLogDao.save(cowImmunityLog);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
//        cowImmunityLogDao.deleteById(id);
        cowImmunityLogMapper.updateStatusById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<CowImmunityLog> createSpecification(Map searchMap) {

        return new Specification<CowImmunityLog>() {

            @Override
            public Predicate toPredicate(Root<CowImmunityLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
                // 疫苗名称
                if (searchMap.get("vaccine_name") != null && !"".equals(searchMap.get("vaccine_name"))) {
                    predicateList.add(cb.like(root.get("vaccine_name").as(String.class), "%" + (String) searchMap.get("vaccine_name") + "%"));
                }
                // 注射量
                if (searchMap.get("injection_volume") != null && !"".equals(searchMap.get("injection_volume"))) {
                    predicateList.add(cb.like(root.get("injection_volume").as(String.class), "%" + (String) searchMap.get("injection_volume") + "%"));
                }
                // 批号
                if (searchMap.get("batch_num") != null && !"".equals(searchMap.get("batch_num"))) {
                    predicateList.add(cb.like(root.get("batch_num").as(String.class), "%" + (String) searchMap.get("batch_num") + "%"));
                }
                // 执行人id
                if (searchMap.get("operator_id") != null && !"".equals(searchMap.get("operator_id"))) {
                    predicateList.add(cb.like(root.get("operator_id").as(String.class), "%" + (String) searchMap.get("operator_id") + "%"));
                }
                // 执行人姓名
                if (searchMap.get("operator_name") != null && !"".equals(searchMap.get("operator_name"))) {
                    predicateList.add(cb.like(root.get("operator_name").as(String.class), "%" + (String) searchMap.get("operator_name") + "%"));
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
    public Result searchList(ImmunityReqDto dto) {
        if (dto == null) {
            return new Result(false, StatusCode.ERROR, "无效参数");
        }
        dto.checkParam();
        if (StringUtils.isNotBlank(dto.getSearchDate())) {
            String[] arr = dto.getSearchDate().split(" 至 ");
            dto.setStartDate(DateUtils.stringToDate(arr[0], DateUtils.DATE_FORMAT));
            dto.setEndDate(DateUtils.stringToDate(arr[1], DateUtils.DATE_FORMAT));
        }
        List<CowImmunityLog> datas = cowImmunityLogMapper.selectBySelective(dto);
        int total = cowImmunityLogMapper.countSelectBySelective(dto);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<CowImmunityLog>((long) total, datas));
    }

    /**
     * 批量删除（逻辑删除）
     *
     * @param list
     * @return
     */
    public Result updateBatch(List<String> list) {
        cowImmunityLogMapper.updateBatch(list);
        return new Result(true, StatusCode.OK, "批量删除成功");
    }
}
