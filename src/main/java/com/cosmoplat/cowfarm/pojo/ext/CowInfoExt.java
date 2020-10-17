package com.cosmoplat.cowfarm.pojo.ext;

import com.cosmoplat.cowfarm.pojo.CowInfo;
import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/10 16:04
 */
@Data
@ToString
public class CowInfoExt extends CowInfo {

    private String sexStr;

    private String varieties;

    private String cowshedName;

    private String groupStateStr;

    private String herdStateStr;

}
