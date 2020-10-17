package com.cosmoplat.cowfarm.mapper;

import com.cosmoplat.cowfarm.dto.CowshedRespDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/15 10:29
 */
@Mapper
public interface CowshedInfoMapper {

    List<String> findCowshedIds(@Param("cowshedId") String cowshedId);

    List<CowshedRespDto> findAllByStatus();

}
