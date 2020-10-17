package com.cosmoplat.cowfarm.dto;

import com.cosmoplat.cowfarm.common.request.PageRequestDto;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/15 14:47
 */
@Data
@ToString
public class ImmunityReqDto extends PageRequestDto {

    /**
     * 牛号
     */
    private String cowId;
    /**
     * 免疫方式
     */
    private Integer immuneMode;
    /**
     * 免疫名称
     */
    private Integer immuneName;
    /**
     * 免疫日期
     */
    private String searchDate;

    private Date startDate;
    private Date endDate;
}
