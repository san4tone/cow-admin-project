package com.cosmoplat.cowfarm.pojo.ext;

import com.cosmoplat.cowfarm.pojo.CowTransferLog;
import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/14 15:20
 */
@Data
@ToString
public class CowMoveBarExt extends CowTransferLog {

    private String reason;
}
