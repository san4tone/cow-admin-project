package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.dao.SysDictDao;
import com.cosmoplat.cowfarm.dto.SysDictDto;
import com.cosmoplat.cowfarm.mapper.SysDictMapper;
import com.cosmoplat.cowfarm.pojo.SysDict;
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
public class SysDictService {

    @Autowired
    private SysDictDao sysDictDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<SysDict> findAll() {
        return sysDictDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<SysDict> findSearch(Map whereMap, int page, int size) {
        Specification<SysDict> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return sysDictDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<SysDict> findSearch(Map whereMap) {
        Specification<SysDict> specification = createSpecification(whereMap);
        return sysDictDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public SysDict findById(String id) {
        return sysDictDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param sysDict
     */
    public void add(SysDict sysDict) {
        sysDict.setId(idWorker.nextId() + "");
        sysDictDao.save(sysDict);
    }

    /**
     * 修改
     *
     * @param sysDict
     */
    public void update(SysDict sysDict) {
        sysDictDao.save(sysDict);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        sysDictDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<SysDict> createSpecification(Map searchMap) {

        return new Specification<SysDict>() {

            @Override
            public Predicate toPredicate(Root<SysDict> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    @Autowired
    SysDictMapper sysDictMapper;

    public List<SysDictDto> findSysDictByKey(String column) {
        return sysDictMapper.findSysDictByKey(column);
    }
}
