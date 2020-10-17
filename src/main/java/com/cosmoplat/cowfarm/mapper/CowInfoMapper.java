package com.cosmoplat.cowfarm.mapper;

import com.cosmoplat.cowfarm.dto.CowInfoReqDto;
import com.cosmoplat.cowfarm.pojo.CowInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/11 21:53
 */
@Mapper
public interface CowInfoMapper {

    List<CowInfo> findAllInfo();

    List<CowInfo> selectBySelective(@Param("dto") CowInfoReqDto dto);

    int countSelectBySelective(@Param("dto") CowInfoReqDto dto);

    void updateBatch(List<String> list);

    void updateStatusById(String id);

    void updateAllCowType();

    CowInfo findCowInfoByCowId(String id);

    List<String> selectIds(@Param("cowId") String cowId);

    void updateCowshedIdByCowId(@Param("cowId") String cowId, @Param("cowshedId") String cowshedId);
}
