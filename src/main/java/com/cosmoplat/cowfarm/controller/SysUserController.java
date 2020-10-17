package com.cosmoplat.cowfarm.controller;

import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dto.SysUserReqDto;
import com.cosmoplat.cowfarm.pojo.SysRole;
import com.cosmoplat.cowfarm.pojo.SysUser;
import com.cosmoplat.cowfarm.service.SysRoleService;
import com.cosmoplat.cowfarm.service.SysUserService;
import com.cosmoplat.cowfarm.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/sysUser")
@Api(value = "系统用户", description = "系统用户操作接口")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 用户登录
     *
     * @param loginMap
     * @return
     */
    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, String> loginMap) {
        SysUser user = sysUserService.findByMobileAndPassword(loginMap.get("username"), loginMap.get("password"));
        if (user != null) {
            List<SysRole> roles = roleService.findByUserId(user.getId());
            StringBuilder sb = new StringBuilder();
            if (roles != null && roles.size() > 0) {
                for (SysRole role : roles) {
                    sb.append(role.getRole_name() + ":");
                }
            }
            String role = sb.substring(0, sb.length() - 1);
            String token = jwtUtil.createJWT(user.getId(), user.getNickname(), role);
            Map map = new HashMap();
            map.put("token", token);
            map.put("name", user.getNickname());//昵称
            map.put("avatar", user.getAvatar());//头像
            map.put("role", role);//角色
            return new Result(true, StatusCode.OK, "登录成功", map);
        } else {
            return new Result(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }
    }


    /**
     * 查询全部数据
     *
     * @return
     */
    @ApiOperation("查询全部")
    @GetMapping("/search")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", sysUserService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @ApiOperation("根据id查询用户信息")
    @GetMapping(value = "/search/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", sysUserService.findById(id));
    }


    /**
     * 分页+条件查询
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "条件分页查询")
    @PostMapping("/searchlist")
    public Result searchList(@RequestBody SysUserReqDto dto) {
        return sysUserService.searchList(dto);
    }

    /**
     * 增加
     *
     * @param sysUser
     */
    @ApiOperation("新增用户")
    @PostMapping("/add")
    public Result add(@RequestBody SysUser sysUser) {
        sysUserService.add(sysUser);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param sysUser
     */
    @ApiOperation("修改用户")
    @PutMapping("/update/{id}")
    public Result update(@RequestBody SysUser sysUser, @PathVariable String id) {
        return sysUserService.updateSysUer(id, sysUser);
    }

    /**
     * 删除
     *
     * @param id
     */
    @ApiOperation("删除用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        Claims claims = (Claims) request.getAttribute("cz_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        sysUserService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @ApiOperation("批量删除用户")
    @RequestMapping(value = "/deletebatch", method = RequestMethod.DELETE)
    public Result delete(@RequestBody Map<String, Object> params) {
        Claims claims = (Claims) request.getAttribute("cz_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        String ids = (String) params.get("ids");
        String[] arr = ids.split(",");
        List<String> list = new ArrayList<>();
        for (String s : arr) {
            if (!"-1".equals(s)) {
                list.add(s);
            }
        }
        return sysUserService.updateBatch(list);
    }

}
