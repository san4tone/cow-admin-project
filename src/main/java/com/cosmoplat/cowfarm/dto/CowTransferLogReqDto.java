package com.cosmoplat.cowfarm.dto;

import com.cosmoplat.cowfarm.common.request.PageRequestDto;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/14 14:17
 */
@Data
@ToString
public class CowTransferLogReqDto extends PageRequestDto {

    private String cowId;

    private Integer turnCause;

    private String startAndEndDate;

    private Date startDate;
    private Date endDate;
}
