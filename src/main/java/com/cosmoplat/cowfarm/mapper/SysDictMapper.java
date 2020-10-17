package com.cosmoplat.cowfarm.mapper;

import com.cosmoplat.cowfarm.dto.SysDictDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/13 11:19
 */
@Mapper
public interface SysDictMapper {

    List<SysDictDto> findSysDictByKey(String column);
}
