package com.cosmoplat.cowfarm.dto;

import com.cosmoplat.cowfarm.common.request.PageRequestDto;
import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/15 10:41
 */
@Data
@ToString
public class SuppliesFodderReqDto extends PageRequestDto {

    private String fodderId;

    private String fodderName;
}
