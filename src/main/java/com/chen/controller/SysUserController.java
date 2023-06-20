package com.chen.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.annoation.Permission;
import com.chen.common.R;
import com.chen.entity.SysUser;
import com.chen.exception.NoRoleException;
import com.chen.service.SysUserService;
import com.chen.utils.JwtUtils;
import com.chen.utils.MD5;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ccs
 * @since 2023-06-19
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    private SysUserService userService;

    @GetMapping("/getByPage/{currentPage}/{pageSize}")
    public R getByPage(){
        return null;
    }
    @Permission(role = {"admin","editor"})
    @GetMapping("/getOne/{id}")
    public R getOne(HttpServletRequest request,@PathVariable Long id){
        SysUser user = userService.getById(id);
        return user!=null?R.ok().data("user",user):R.error().message("用户不存在");
        
    }
    @PostMapping("/login")
    public R login(@RequestBody SysUser sysUser){
        String password = sysUser.getPassword();
        String username = sysUser.getUsername();
       SysUser user= userService.getByUserName(username);
       if(user==null){
           throw new NoRoleException(20001L,"用户不存在");
       }
        if(!MD5.encrypt(password).equals(user.getPassword())){
            throw new NoRoleException(20001L,"密码错误");
        }
        String token = JwtUtils.getJwtToken(user.getId().intValue(), user.getUsername());
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        return R.ok().data(map);
    }
    @GetMapping("/info")
    @ApiOperation("后台管理员登录获取信息")
    public R info(HttpServletRequest request){
        String username = JwtUtils.getMemberNameJwtToken(request);
        LambdaQueryWrapper<SysUser> adminLambdaQueryWrapper=new LambdaQueryWrapper<>();
        adminLambdaQueryWrapper.eq(SysUser::getUsername,username);
        SysUser one = userService.getOne(adminLambdaQueryWrapper);
        return R.ok().data("roles","["+one.getRole()+"]").data("name",one.getUsername()).data("avatar",one.getAvatarUrl());
    }



}

