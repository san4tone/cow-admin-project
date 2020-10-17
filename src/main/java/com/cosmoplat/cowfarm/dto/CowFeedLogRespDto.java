package com.cosmoplat.cowfarm.dto;

import com.cosmoplat.cowfarm.pojo.CowFeedLog;
import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/17 13:37
 */
@Data
@ToString
public class CowFeedLogRespDto extends CowFeedLog {

    private String cowshedName;
}
