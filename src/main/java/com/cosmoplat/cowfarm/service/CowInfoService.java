package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.common.response.PageResult;
import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dao.CowInfoDao;
import com.cosmoplat.cowfarm.dto.CowInfoReqDto;
import com.cosmoplat.cowfarm.mapper.CowInfoMapper;
import com.cosmoplat.cowfarm.pojo.CowInfo;
import com.cosmoplat.cowfarm.utils.DateUtils;
import com.cosmoplat.cowfarm.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
public class CowInfoService {

    @Autowired
    private CowInfoDao cowInfoDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CowInfoMapper cowInfoMapper;

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public CowInfo findById(String id) {
        return cowInfoMapper.findCowInfoByCowId(id);
    }

    /**
     * 根据cowId模糊匹配牛号列表
     *
     * @param cowId
     * @return
     */
    public List<String> selectIds(String cowId) {
        return cowInfoMapper.selectIds(cowId);
    }

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<CowInfo> findAll() {
        return cowInfoMapper.findAllInfo();
//        return cowInfoDao.findAll();
    }

    /**
     * 分页+条件查询
     *
     * @param dto
     * @return
     */
    public Result searchList(CowInfoReqDto dto) {
        if (dto == null) {
            return new Result(false, StatusCode.ERROR, "无效参数");
        }
        dto.checkParam();
        if (StringUtils.isNotBlank(dto.getEnterDate())) {
            String[] arr = dto.getEnterDate().split(" 至 ");
            dto.setEnterDate1(DateUtils.stringToDate(arr[0], DateUtils.DATE_FORMAT));
            dto.setEnterDate2(DateUtils.stringToDate(arr[1], DateUtils.DATE_FORMAT));
        }
        if (StringUtils.isNotBlank(dto.getLeaveDate())) {
            String[] arr = dto.getLeaveDate().split(" 至 ");
            dto.setLeaveDate1(DateUtils.stringToDate(arr[0], DateUtils.DATE_FORMAT));
            dto.setLeaveDate2(DateUtils.stringToDate(arr[1], DateUtils.DATE_FORMAT));
        }
        List<CowInfo> datas = cowInfoMapper.selectBySelective(dto);
        int total = cowInfoMapper.countSelectBySelective(dto);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<CowInfo>((long) total, datas));
    }

    /**
     * 新增
     *
     * @param cowInfo
     */
    public void add(CowInfo cowInfo) {
        cowInfo.setCow_id(idWorker.nextId() + "");
        cowInfo.setStatus(1);
        cowInfo.setGmt_create(new Date());
        cowInfoDao.save(cowInfo);
    }

    /**
     * 修改
     *
     * @param cowInfo
     */
    public Result updateCowInfo(String id, CowInfo cowInfo) {
        CowInfo one = this.findById(id);
        if (one == null) {
            return new Result(false, StatusCode.ERROR, "牛只不存在");
        }
        // 修改牛只信息
        if (StringUtils.isNotBlank(cowInfo.getCollar_id())) {
            one.setCollar_id(cowInfo.getCollar_id());
        }
        if (StringUtils.isNotBlank(cowInfo.getCowshed_id())) {
            one.setCowshed_id(cowInfo.getCowshed_id());
        }
        if (cowInfo.getVarieties_code() != null) {
            one.setVarieties_code(cowInfo.getVarieties_code());
        }
        if (cowInfo.getSex() != null) {
            one.setSex(cowInfo.getSex());
        }
        if (cowInfo.getBirthday() != null) {
            one.setBirthday(cowInfo.getBirthday());
        }
        if (cowInfo.getAdmission_time() != null) {
            one.setAdmission_time(cowInfo.getAdmission_time());
        }
        if (cowInfo.getBirth_weight() != null) {
            one.setBirth_weight(cowInfo.getBirth_weight());
        }
        if (cowInfo.getAdmission_weight() != null) {
            one.setAdmission_weight(cowInfo.getAdmission_weight());
        }
        if (StringUtils.isNotBlank(cowInfo.getInsurance_id())) {
            one.setInsurance_id(cowInfo.getInsurance_id());
        }
        if (StringUtils.isNotBlank(cowInfo.getLeft_img())) {
            one.setLeft_img(cowInfo.getLeft_img());
        }
        if (StringUtils.isNotBlank(cowInfo.getMid_img())) {
            one.setMid_img(cowInfo.getMid_img());
        }
        if (StringUtils.isNotBlank(cowInfo.getRight_img())) {
            one.setRight_img(cowInfo.getRight_img());
        }
        if (StringUtils.isNotBlank(cowInfo.getSource_address())) {
            one.setSource_address(cowInfo.getSource_address());
        }
        if (cowInfo.getUnit_price() != null) {
            one.setUnit_price(cowInfo.getUnit_price());
        }
        if (StringUtils.isNotBlank(cowInfo.getSource_batch())) {
            one.setSource_batch(cowInfo.getSource_batch());
        }
        if (StringUtils.isNotBlank(cowInfo.getRemark())) {
            one.setRemark(cowInfo.getRemark());
        }
        if (StringUtils.isNotBlank(cowInfo.getSupplier())) {
            one.setSupplier(cowInfo.getSupplier());
        }
        if (StringUtils.isNotBlank(cowInfo.getLoan_id())) {
            one.setLoan_id(cowInfo.getLoan_id());
        }
        if (cowInfo.getLeave_time() != null) {
            one.setLeave_time(cowInfo.getLeave_time());
        }
        one.setStatus(1);
        CowInfo save = cowInfoDao.save(one);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 单个删除
     *
     * @param id
     */
    public void deleteById(String id) {
//        cowInfoDao.deleteById(id);
        cowInfoMapper.updateStatusById(id);
    }

    /**
     * 批量删除（逻辑删除）
     *
     * @param list
     * @return
     */
    public Result updateBatch(List<String> list) {
        cowInfoMapper.updateBatch(list);
        return new Result(true, StatusCode.OK, "批量删除成功");
    }

    /**
     * 删除全部
     *
     * @return
     */
    public Result updateAllCowType() {
        cowInfoMapper.updateAllCowType();
        return new Result(true, StatusCode.OK, "删除全部成功");
    }

}
