package com.cosmoplat.cowfarm.mapper;

import com.cosmoplat.cowfarm.dto.ImmunityReqDto;
import com.cosmoplat.cowfarm.pojo.CowImmunityLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/15 14:42
 */
@Mapper
public interface CowImmunityLogMapper {

    List<CowImmunityLog> findAllByStatus();

    CowImmunityLog findByIdAndStatus(String id);

    List<CowImmunityLog> selectBySelective(@Param("dto") ImmunityReqDto dto);

    int countSelectBySelective(@Param("dto") ImmunityReqDto dto);

    void updateStatusById(String id);

    void updateBatch(List<String> list);
}
