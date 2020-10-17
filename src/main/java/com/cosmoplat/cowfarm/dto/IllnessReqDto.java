package com.cosmoplat.cowfarm.dto;

import com.cosmoplat.cowfarm.common.request.PageRequestDto;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/15 13:38
 */
@Data
@ToString
public class IllnessReqDto extends PageRequestDto {

    /**
     * 牛号
     */
    private String cowId;
    /**
     * 疾病类别（疾病名称）
     */
    private Integer illnessCode;
    /**
     * 发病日期
     */
    private String searchDate;

    private Date startDate;

    private Date endDate;
}
