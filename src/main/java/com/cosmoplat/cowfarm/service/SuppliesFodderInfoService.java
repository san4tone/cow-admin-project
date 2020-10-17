package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.common.response.PageResult;
import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dao.SuppliesFodderInfoDao;
import com.cosmoplat.cowfarm.dto.SuppliesFodderReqDto;
import com.cosmoplat.cowfarm.mapper.SuppliesFodderInfoMapper;
import com.cosmoplat.cowfarm.pojo.SuppliesFodderInfo;
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
public class SuppliesFodderInfoService {

    @Autowired
    private SuppliesFodderInfoDao suppliesFodderInfoDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SuppliesFodderInfoMapper suppliesFodderInfoMapper;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<SuppliesFodderInfo> findAll() {
        return suppliesFodderInfoDao.findByStatus(1);
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<SuppliesFodderInfo> findSearch(Map whereMap, int page, int size) {
        Specification<SuppliesFodderInfo> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return suppliesFodderInfoDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<SuppliesFodderInfo> findSearch(Map whereMap) {
        Specification<SuppliesFodderInfo> specification = createSpecification(whereMap);
        return suppliesFodderInfoDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public SuppliesFodderInfo findById(String id) {
        return suppliesFodderInfoMapper.findById(id);
    }

    /**
     * 增加
     *
     * @param suppliesFodderInfo
     */
    public void add(SuppliesFodderInfo suppliesFodderInfo) {
        suppliesFodderInfo.setId(idWorker.nextId() + "");
        suppliesFodderInfo.setStatus(1);
        suppliesFodderInfo.setGmt_create(new Date());
        suppliesFodderInfoDao.save(suppliesFodderInfo);
    }

    /**
     * 修改
     *
     * @param suppliesFodderInfo
     */
    public Result update(String id, SuppliesFodderInfo suppliesFodderInfo) {
        SuppliesFodderInfo one = this.findById(id);
        if (one == null) {
            return new Result(false, StatusCode.ERROR, "饲料不存在");
        }
        // 修改饲料信息
        if (StringUtils.isNotBlank(suppliesFodderInfo.getFodder_id())) {
            one.setFodder_id(suppliesFodderInfo.getFodder_id());
        }
        if (StringUtils.isNotBlank(suppliesFodderInfo.getFodder_name())) {
            one.setFodder_name(suppliesFodderInfo.getFodder_name());
        }
        if (suppliesFodderInfo.getDry_per() != null) {
            one.setDry_per(suppliesFodderInfo.getDry_per());
        }
        if (suppliesFodderInfo.getUnit_price() != null) {
            one.setUnit_price(suppliesFodderInfo.getUnit_price());
        }
        if (suppliesFodderInfo.getStock_count() != null) {
            one.setStock_count(suppliesFodderInfo.getStock_count());
        }
        if (suppliesFodderInfo.getStock_unit() != null) {
            one.setStock_unit(suppliesFodderInfo.getStock_unit());
        }
        one.setStatus(1);
        one.setGmt_modified(new Date());
        SuppliesFodderInfo save = suppliesFodderInfoDao.save(one);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        suppliesFodderInfoDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<SuppliesFodderInfo> createSpecification(Map searchMap) {

        return new Specification<SuppliesFodderInfo>() {

            @Override
            public Predicate toPredicate(Root<SuppliesFodderInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 饲料编号
                if (searchMap.get("fodder_id") != null && !"".equals(searchMap.get("fodder_id"))) {
                    predicateList.add(cb.like(root.get("fodder_id").as(String.class), "%" + (String) searchMap.get("fodder_id") + "%"));
                }
                // 饲料名称
                if (searchMap.get("fodder_name") != null && !"".equals(searchMap.get("fodder_name"))) {
                    predicateList.add(cb.like(root.get("fodder_name").as(String.class), "%" + (String) searchMap.get("fodder_name") + "%"));
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
    public Result searchList(SuppliesFodderReqDto dto) {
        if (dto == null) {
            return new Result(false, StatusCode.ERROR, "无效参数");
        }
        dto.checkParam();
        List<SuppliesFodderInfo> datas = suppliesFodderInfoMapper.selectBySelective(dto);
        int total = suppliesFodderInfoMapper.countSelectBySelective(dto);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<SuppliesFodderInfo>((long) total, datas));

    }

    public Result updateBatch(List<String> list) {
        suppliesFodderInfoMapper.updateBatch(list);
        return new Result(true, StatusCode.OK, "撤销成功");
    }
}
