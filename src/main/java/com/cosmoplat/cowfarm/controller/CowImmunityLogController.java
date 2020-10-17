package com.cosmoplat.cowfarm.controller;

import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dto.ImmunityReqDto;
import com.cosmoplat.cowfarm.pojo.CowImmunityLog;
import com.cosmoplat.cowfarm.service.CowImmunityLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/cowImmunityLog")
@Api(value = "免疫管理", description = "免疫管理接口")
public class CowImmunityLogController {

	@Autowired
	private CowImmunityLogService cowImmunityLogService;


	/**
	 * 查询全部数据
	 * @return
	 */
	@ApiOperation(value = "查询全部")
	@GetMapping("search")
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",cowImmunityLogService.findAll());
	}

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@ApiOperation("根据ID查询")
	@GetMapping("/search/{id}")
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",cowImmunityLogService.findById(id));
	}

	/**
	 * 分页+条件查询
	 *
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "条件分页查询")
	@PostMapping("/searchlist")
	public Result searchList(@RequestBody ImmunityReqDto dto) {
		return cowImmunityLogService.searchList(dto);
	}


	/**
	 * 增加
	 * @param cowImmunityLog
	 */
	@ApiOperation(value = "新增")
	@PostMapping("/add")
	public Result add(@RequestBody CowImmunityLog cowImmunityLog  ){
		cowImmunityLogService.add(cowImmunityLog);
		return new Result(true,StatusCode.OK,"增加成功");
	}

	/**
	 * 修改
	 * @param cowImmunityLog
	 */
	@ApiOperation(value = "根据ID修改")
	@PutMapping("/update/{id}")
	public Result update(@RequestBody CowImmunityLog cowImmunityLog, @PathVariable String id ){
		return cowImmunityLogService.update(id,cowImmunityLog);
	}

	/**
	 * 单个删除
	 *
	 * @param id
	 */
	@ApiOperation(value = "根据ID删除")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		cowImmunityLogService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
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
		return cowImmunityLogService.updateBatch(list);
	}

}
