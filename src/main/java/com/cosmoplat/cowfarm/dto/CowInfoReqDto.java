package com.cosmoplat.cowfarm.dto;

import com.cosmoplat.cowfarm.common.request.PageRequestDto;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/13 13:47
 */
@Data
@ToString
public class CowInfoReqDto extends PageRequestDto {

    /**
     * 牛号
     */
    private String cowNumStr;
    /**
     * 牛舍
     */
    private String barGroup;
    /**
     * 入场日期
     */
    private String enterDate;
    /**
     * 核心种群
     */
//    private Integer core;
    /**
     * 牛群状态
     */
    private Integer herdState;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 在群状态
     */
    private Integer isActived;
    /**
     * 品种
     */
    private Integer breedCode;
    /**
     * 离场日期
     */
    private String leaveDate;

    private Date enterDate1;

    private Date enterDate2;

    private Date leaveDate1;

    private Date leaveDate2;
}
