package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.common.response.PageResult;
import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dao.SysUserDao;
import com.cosmoplat.cowfarm.dto.SysUserReqDto;
import com.cosmoplat.cowfarm.mapper.SysUserMapper;
import com.cosmoplat.cowfarm.pojo.SysUser;
import com.cosmoplat.cowfarm.utils.DateUtils;
import com.cosmoplat.cowfarm.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private SysUserMapper sysUserMapper;

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("shouyi");
        // $2a$10$9WUv.lC7hNJAyQ4o5bQts.fIy3JMy.nR4HRJ6od4qhlb6eOgHXVhK admin
        // $2a$10$d8U3VAJJjFKJ0pdRbN59IeHZLL43ZcONY57htJMen5TBEIyFT/jXy siyangyuan
        // $2a$10$IXxFwkXeuyMRnk6zJvKi8OjMu9svSHJtp99TpQ6pEJCJ5hCff.am2 qingjieyuan
        // $2a$10$zhlDxLamSK3IwnjBRc69sOnAYfdwmj2XPKFO0VZHPFKsx3hUHsc2q shouyi
        System.out.println(encode);
    }

    /**
     * 根据手机号和密码查询用户
     *
     * @param mobile
     * @param password
     * @return
     */
    public SysUser findByMobileAndPassword(String mobile, String password) {
        SysUser user = sysUserDao.findByMobile(mobile);
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<SysUser> findAll() {
//        return sysUserMapper.findAllByStatus();
        return sysUserDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<SysUser> findSearch(Map whereMap, int page, int size) {
        Specification<SysUser> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return sysUserDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<SysUser> findSearch(Map whereMap) {
        Specification<SysUser> specification = createSpecification(whereMap);
        return sysUserDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public SysUser findById(String id) {
        return sysUserMapper.findById(id);
    }

    /**
     * 增加
     *
     * @param sysUser
     */
    public void add(SysUser sysUser) {
        sysUser.setId(idWorker.nextId() + "");
        sysUser.setPassword(encoder.encode(sysUser.getPassword()));
        sysUser.setRegdate(new Date());
        sysUser.setStatus(1);
        sysUser.setGmt_create(new Date());
        sysUserDao.save(sysUser);
    }

    /**
     * 修改
     *
     * @param sysUser
     */
    public void update(SysUser sysUser) {
        sysUserDao.save(sysUser);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
//        sysUserDao.deleteById(id);
        sysUserMapper.updateStatusById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<SysUser> createSpecification(Map searchMap) {

        return new Specification<SysUser>() {

            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 用户名
                if (searchMap.get("username") != null && !"".equals(searchMap.get("username"))) {
                    predicateList.add(cb.like(root.get("username").as(String.class), "%" + (String) searchMap.get("username") + "%"));
                }
                // 手机号码
                if (searchMap.get("mobile") != null && !"".equals(searchMap.get("mobile"))) {
                    predicateList.add(cb.like(root.get("mobile").as(String.class), "%" + (String) searchMap.get("mobile") + "%"));
                }
                // 密码
                if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                    predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
                }
                // 昵称
                if (searchMap.get("nickname") != null && !"".equals(searchMap.get("nickname"))) {
                    predicateList.add(cb.like(root.get("nickname").as(String.class), "%" + (String) searchMap.get("nickname") + "%"));
                }
                // 性别
                if (searchMap.get("sex") != null && !"".equals(searchMap.get("sex"))) {
                    predicateList.add(cb.like(root.get("sex").as(String.class), "%" + (String) searchMap.get("sex") + "%"));
                }
                // 头像
                if (searchMap.get("avatar") != null && !"".equals(searchMap.get("avatar"))) {
                    predicateList.add(cb.like(root.get("avatar").as(String.class), "%" + (String) searchMap.get("avatar") + "%"));
                }
                // E-Mail
                if (searchMap.get("email") != null && !"".equals(searchMap.get("email"))) {
                    predicateList.add(cb.like(root.get("email").as(String.class), "%" + (String) searchMap.get("email") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    /**
     * 根据id修改用户信息
     *
     * @param id
     * @param sysUser
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result updateSysUer(String id, SysUser sysUser) {
        SysUser one = this.findById(id);
        if (one == null) {
            return new Result(false, StatusCode.ERROR, "该用户不存在");
        }
        // 修改用户信息
        if (StringUtils.isNotBlank(sysUser.getNickname())) {
            one.setNickname(sysUser.getNickname());
        }
        if (StringUtils.isNotBlank(sysUser.getMobile())) {
            one.setMobile(sysUser.getMobile());
        }
        if (StringUtils.isNotBlank(sysUser.getEmail())) {
            one.setEmail(sysUser.getEmail());
        }
        one.setStatus(sysUser.getStatus());
        SysUser save = sysUserDao.save(one);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result updateBatch(List<String> list) {
        sysUserMapper.updateBatch(list);
        return new Result(true, StatusCode.OK, "批量删除成功");
    }

    /**
     * 分页+条件查询
     *
     * @param dto
     * @return
     */
    public Result searchList(SysUserReqDto dto) {
        if (dto == null) {
            return new Result(false, StatusCode.ERROR, "无效参数");
        }
        dto.checkParam();
        if (StringUtils.isNotBlank(dto.getRegDate())) {
            String[] arr = dto.getRegDate().split(" 至 ");
            dto.setRegStart(DateUtils.stringToDate(arr[0], DateUtils.DATE_FORMAT));
            dto.setRegEnd(DateUtils.stringToDate(arr[1], DateUtils.DATE_FORMAT));
        }
        if (StringUtils.isNotBlank(dto.getLastDate())) {
            String[] arr = dto.getLastDate().split(" 至 ");
            dto.setStartDate(DateUtils.stringToDate(arr[0], DateUtils.DATE_FORMAT));
            dto.setEndDate(DateUtils.stringToDate(arr[1], DateUtils.DATE_FORMAT));
        }
        List<SysUser> datas = sysUserMapper.selectBySelective(dto);
        int total = sysUserMapper.countSelectBySelective(dto);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<SysUser>((long) total, datas));
    }
}
