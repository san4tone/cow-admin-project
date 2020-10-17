package com.cosmoplat.cowfarm.controller;

import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dto.SuppliesFodderReqDto;
import com.cosmoplat.cowfarm.pojo.SuppliesFodderInfo;
import com.cosmoplat.cowfarm.service.SuppliesFodderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/suppliesFodderInfo")
@Api(value = "饲料信息", description = "饲料信息接口")
public class SuppliesFodderInfoController {

    @Autowired
    private SuppliesFodderInfoService suppliesFodderInfoService;

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @ApiOperation("根据ID查询")
    @GetMapping("/search/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", suppliesFodderInfoService.findById(id));
    }


    /**
     * 查询全部数据
     *
     * @return
     */
    @ApiOperation(value = "查询全部")
    @GetMapping("/search")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", suppliesFodderInfoService.findAll());
    }

    /**
     * 分页+多条件查询
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "条件分页查询")
    @PostMapping("/searchlist")
    public Result findSearch(@RequestBody SuppliesFodderReqDto dto) {
        if (dto == null) {
            return new Result(false, StatusCode.ERROR, "无效参数");
        }
        return suppliesFodderInfoService.searchList(dto);
    }

    /**
     * 增加
     *
     * @param suppliesFodderInfo
     */
    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public Result add(@RequestBody SuppliesFodderInfo suppliesFodderInfo) {
        suppliesFodderInfoService.add(suppliesFodderInfo);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param suppliesFodderInfo
     */
    @ApiOperation(value = "根据ID修改饲料信息")
    @PutMapping("/update/{id}")
    public Result update(@RequestBody SuppliesFodderInfo suppliesFodderInfo, @PathVariable String id) {
        return suppliesFodderInfoService.update(id, suppliesFodderInfo);
    }

    /**
     * 批量删除
     *
     * @param params
     */
    @ApiOperation(value = "批量删除")
    @PostMapping(value = "/deletebatch")
    public Result delete(@RequestBody String[] params) {
        List<String> list = Arrays.asList(params);
        return suppliesFodderInfoService.updateBatch(list);
    }

}
