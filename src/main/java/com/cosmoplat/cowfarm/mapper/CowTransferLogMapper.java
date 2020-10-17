package com.cosmoplat.cowfarm.mapper;

import com.cosmoplat.cowfarm.dto.CowTransferLogReqDto;
import com.cosmoplat.cowfarm.pojo.CowTransferLog;
import com.cosmoplat.cowfarm.pojo.ext.CowMoveBarExt;
import com.cosmoplat.cowfarm.pojo.ext.CowTransferExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/13 23:18
 */
@Mapper
public interface CowTransferLogMapper {

    int selectCountByCowId(String cowId);

    CowTransferLog findCowTransferLogByCowId(String cowId);

    List<CowTransferLog> findAll();

    List<CowTransferLog> selectBySelective(@Param("dto") CowTransferLogReqDto dto);

    int countSelectBySelective(@Param("dto") CowTransferLogReqDto dto);

    void updateBatch(List<String> list);

    List<CowMoveBarExt> selectAll();

    List<CowMoveBarExt> findBySelective(@Param("dto") CowTransferLogReqDto dto);

    List<CowTransferExt> findCowTransferExts(@Param("dto") CowTransferLogReqDto dto);

    List<CowTransferExt> findAllCowTransferExts();

    void updateOldLogStatusByCowIdAndLogId(@Param("id") String id, @Param("cowId") String cowId);
}
