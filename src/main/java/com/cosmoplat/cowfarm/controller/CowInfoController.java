package com.cosmoplat.cowfarm.controller;

import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dto.CowInfoReqDto;
import com.cosmoplat.cowfarm.pojo.CowInfo;
import com.cosmoplat.cowfarm.service.CowInfoService;
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
@RequestMapping("/cowInfo")
@Api(value = "牛只管控", description = "牛只信息管理")
public class CowInfoController {

    @Autowired
    private CowInfoService cowInfoService;

    /**
     * 根据cowId模糊匹配牛号列表
     *
     * @param cowId
     * @return
     */
    @ApiOperation(value = "根据cowId模糊匹配牛号列表")
    @GetMapping("/selectids/{cowId}")
    public Result selectIds(@PathVariable String cowId) {
        return new Result(true, StatusCode.OK, "查询成功", cowInfoService.selectIds(cowId));
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @ApiOperation("根据ID查询")
    @GetMapping("/search/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",cowInfoService.findById(id));
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @ApiOperation(value = "查询全部")
    @GetMapping("/search")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", cowInfoService.findAll());
    }

    /**
     * 分页+条件查询
     *
     * @param cowInfoReqDto
     * @return
     */
    @ApiOperation(value = "条件分页查询")
    @PostMapping("/searchlist")
    public Result searchList(@RequestBody CowInfoReqDto cowInfoReqDto) {
        return cowInfoService.searchList(cowInfoReqDto);
    }

    /**
     * 增加
     *
     * @param cowInfo
     */
    @ApiOperation(value = "新增牛只")
    @PostMapping("/add")
    public Result add(@RequestBody CowInfo cowInfo) {
        cowInfoService.add(cowInfo);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param cowInfo
     */
    @ApiOperation(value = "根据ID修改牛只信息")
    @PutMapping("/update/{id}")
    public Result update(@RequestBody CowInfo cowInfo, @PathVariable String id) {
        return cowInfoService.updateCowInfo(id, cowInfo);
    }

    /**
     * 单个删除
     *
     * @param id
     */
    @ApiOperation(value = "根据ID删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        cowInfoService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param params
     * @return
     */
    @ApiOperation(value = "批量删除")
    @PostMapping(value = "/deletebatch")
    public Result delete(@RequestBody String[] params) {
        List<String> list = Arrays.asList(params);
        return cowInfoService.updateBatch(list);
    }

    @ApiOperation(value = "删除全部")
    @PostMapping(value = "/deleteAll")
    public Result deleteAll() {
        return cowInfoService.updateAllCowType();
    }

}
