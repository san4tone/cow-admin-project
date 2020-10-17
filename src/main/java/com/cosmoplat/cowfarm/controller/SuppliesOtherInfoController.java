package com.cosmoplat.cowfarm.controller;
import com.cosmoplat.cowfarm.common.response.PageResult;
import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.pojo.SuppliesOtherInfo;
import com.cosmoplat.cowfarm.service.SuppliesOtherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/suppliesOtherInfo")
public class SuppliesOtherInfoController {

	@Autowired
	private SuppliesOtherInfoService suppliesOtherInfoService;


	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",suppliesOtherInfoService.findAll());
	}

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",suppliesOtherInfoService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<SuppliesOtherInfo> pageList = suppliesOtherInfoService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<SuppliesOtherInfo>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",suppliesOtherInfoService.findSearch(searchMap));
    }

	/**
	 * 增加
	 * @param suppliesOtherInfo
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody SuppliesOtherInfo suppliesOtherInfo  ){
		suppliesOtherInfoService.add(suppliesOtherInfo);
		return new Result(true,StatusCode.OK,"增加成功");
	}

	/**
	 * 修改
	 * @param suppliesOtherInfo
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody SuppliesOtherInfo suppliesOtherInfo, @PathVariable String id ){
		suppliesOtherInfo.setId(id);
		suppliesOtherInfoService.update(suppliesOtherInfo);
		return new Result(true,StatusCode.OK,"修改成功");
	}

	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		suppliesOtherInfoService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

}
