package com.cosmoplat.cowfarm.controller;

import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dto.CowFeedLogReqDto;
import com.cosmoplat.cowfarm.pojo.CowFeedLog;
import com.cosmoplat.cowfarm.service.CowFeedLogService;
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
@RequestMapping("/cowFeedLog")
@Api(value = "牛只饲喂", description = "牛只饲喂接口")
public class CowFeedLogController {

    @Autowired
    private CowFeedLogService cowFeedLogService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @ApiOperation(value = "查询全部")
    @GetMapping("/search")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", cowFeedLogService.findAll());
    }

    /**
     * 分页+多条件查询
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "条件分页查询")
    @PostMapping("/searchlist")
    public Result findSearch(@RequestBody CowFeedLogReqDto dto) {
        if (dto == null) {
            return new Result(false, StatusCode.ERROR, "无效参数");
        }
        return cowFeedLogService.searchList(dto);
    }

    /**
     * 增加
     *
     * @param cowFeedLog
     */
    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public Result add(@RequestBody CowFeedLog cowFeedLog) {
        cowFeedLogService.add(cowFeedLog);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 删除
     *
     * @param params
     */
    @ApiOperation(value = "撤销")
    @PostMapping(value = "/deletebatch")
    public Result delete(@RequestBody String[] params) {
        List<String> list = Arrays.asList(params);
        return cowFeedLogService.updateBatch(list);
    }

}
