package com.cosmoplat.cowfarm.dto;

import com.cosmoplat.cowfarm.common.request.PageRequestDto;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/15 16:20
 */
@Data
@ToString
public class SysUserReqDto extends PageRequestDto {

    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 性别
     */
    private String sex;
    /**
     * 注册日期
     */
    private String regDate;
    /**
     * 最后登录日期
     */
    private String lastDate;
    /**
     * 状态
     */
    private String status;

    private Date regStart;
    private Date regEnd;

    private Date startDate;
    private Date endDate;
}
