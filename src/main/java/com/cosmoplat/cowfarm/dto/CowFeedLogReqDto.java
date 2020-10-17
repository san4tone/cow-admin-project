package com.cosmoplat.cowfarm.dto;

import com.cosmoplat.cowfarm.common.request.PageRequestDto;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/15 10:03
 */
@Data
@ToString
public class CowFeedLogReqDto extends PageRequestDto {

    private String cowshedId;

    private String searchDate;

    private Date startDate;

    private Date endDate;

}
