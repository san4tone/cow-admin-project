package com.cosmoplat.cowfarm.mapper;

import com.cosmoplat.cowfarm.dto.IllnessReqDto;
import com.cosmoplat.cowfarm.pojo.CowHealthLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/15 13:48
 */
@Mapper
public interface CowHealthLogMapper {

    List<CowHealthLog> findAllByStatus();

    CowHealthLog findByIdAndStatus(String id);

    List<CowHealthLog> selectBySelective(@Param("dto") IllnessReqDto dto);

    int countSelectBySelective(@Param("dto") IllnessReqDto dto);

    void updateStatusById(String id);

    void updateBatch(List<String> list);
}
