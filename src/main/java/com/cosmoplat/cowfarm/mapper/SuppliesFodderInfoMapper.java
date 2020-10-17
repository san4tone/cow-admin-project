package com.cosmoplat.cowfarm.mapper;

import com.cosmoplat.cowfarm.dto.SuppliesFodderReqDto;
import com.cosmoplat.cowfarm.pojo.SuppliesFodderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/15 10:44
 */
@Mapper
public interface SuppliesFodderInfoMapper {

    List<SuppliesFodderInfo> selectBySelective(@Param("dto") SuppliesFodderReqDto dto);

    int countSelectBySelective(@Param("dto") SuppliesFodderReqDto dto);

    void updateBatch(List<String> list);

    SuppliesFodderInfo findById(String id);
}
