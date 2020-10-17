package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.CowshedInfoDao;
import com.cosmoplat.cowfarm.dto.CowshedRespDto;
import com.cosmoplat.cowfarm.mapper.CowshedInfoMapper;
import com.cosmoplat.cowfarm.pojo.CowshedInfo;
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
 */
@Service
public class CowshedInfoService {

    @Autowired
    private CowshedInfoDao cowshedInfoDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<CowshedInfo> findAll() {
        return cowshedInfoDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<CowshedInfo> findSearch(Map whereMap, int page, int size) {
        Specification<CowshedInfo> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return cowshedInfoDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<CowshedInfo> findSearch(Map whereMap) {
        Specification<CowshedInfo> specification = createSpecification(whereMap);
        return cowshedInfoDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public CowshedInfo findById(String id) {
        return cowshedInfoDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param cowshedInfo
     */
    public void add(CowshedInfo cowshedInfo) {
        cowshedInfo.setCowshed_id(idWorker.nextId() + "");
        cowshedInfoDao.save(cowshedInfo);
    }

    /**
     * 修改
     *
     * @param cowshedInfo
     */
    public void update(CowshedInfo cowshedInfo) {
        cowshedInfoDao.save(cowshedInfo);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        cowshedInfoDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<CowshedInfo> createSpecification(Map searchMap) {

        return new Specification<CowshedInfo>() {

            @Override
            public Predicate toPredicate(Root<CowshedInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 牛舍编号
                if (searchMap.get("cowshed_id") != null && !"".equals(searchMap.get("cowshed_id"))) {
                    predicateList.add(cb.like(root.get("cowshed_id").as(String.class), "%" + (String) searchMap.get("cowshed_id") + "%"));
                }
                // 牛舍名称
                if (searchMap.get("cowshed_name") != null && !"".equals(searchMap.get("cowshed_name"))) {
                    predicateList.add(cb.like(root.get("cowshed_name").as(String.class), "%" + (String) searchMap.get("cowshed_name") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    @Autowired
    CowshedInfoMapper cowshedInfoMapper;

    /**
     * 模糊查询IDs
     *
     * @param cowshedId
     * @return
     */
    public List<String> findCowshedIds(String cowshedId) {
        return cowshedInfoMapper.findCowshedIds(cowshedId);
    }

    public List<CowshedRespDto> findAllByStatus() {
        return cowshedInfoMapper.findAllByStatus();
    }
}
