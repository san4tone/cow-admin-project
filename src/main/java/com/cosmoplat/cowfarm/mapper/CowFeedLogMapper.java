package com.cosmoplat.cowfarm.mapper;

import com.cosmoplat.cowfarm.dto.CowFeedLogReqDto;
import com.cosmoplat.cowfarm.dto.CowFeedLogRespDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/15 10:11
 */
@Mapper
public interface CowFeedLogMapper {

    List<CowFeedLogRespDto> findAllByStatus();

    CowFeedLogRespDto findByCowshedId(String cowshedId);

    List<CowFeedLogRespDto> selectBySelective(@Param("dto") CowFeedLogReqDto dto);

    int countSelectBySelective(@Param("dto") CowFeedLogReqDto dto);

    void updateBatch(List<String> list);
}
