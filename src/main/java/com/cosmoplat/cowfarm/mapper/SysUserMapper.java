package com.cosmoplat.cowfarm.mapper;

import com.cosmoplat.cowfarm.dto.SysUserReqDto;
import com.cosmoplat.cowfarm.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/12 18:15
 */
@Mapper
public interface SysUserMapper {

    void updateStatusById(String id);

    void updateBatch(List<String> list);

    List<SysUser> findAllByStatus();

    SysUser findById(String id);

    List<SysUser> selectBySelective(@Param("dto") SysUserReqDto dto);

    int countSelectBySelective(@Param("dto") SysUserReqDto dto);
}
