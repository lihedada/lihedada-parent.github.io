package com.system.controller;/*
@author shkstart
@Date2022-10-30-15:58
*/

import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.LoginVo;
import com.common.result.Result;
import com.common.utils.JwtHelper;
import com.common.utils.MD5;
import com.system.Exception.GuiguException;
import com.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户登录的接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    //登录的两个接口
    //lonin
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo){
        //根据username查询数据
        SysUser sysUser = sysUserService.getUserInfoByUserName(loginVo.getUsername());

        //如果查询为空
        if(sysUser == null) {
            throw new GuiguException(20001,"用户不存在");
        }

        //判断密码是否一致
        String password = loginVo.getPassword();
        String md5Password = MD5.encrypt(password);
        if(!sysUser.getPassword().equals(md5Password)) {
            throw new GuiguException(20001,"密码不正确");
        }

        //判断用户是否可用
        if(sysUser.getStatus().intValue()==0) {
            throw new GuiguException(20001,"用户已经被禁用");
        }

        //根据userid和username生成token字符串，通过map返回
        String token = JwtHelper.createToken(sysUser.getId(),sysUser.getUsername());

        HashMap<Object, Object> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }

    @GetMapping("info")
    public Result info(HttpServletRequest request) {
       /* HashMap<Object, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Admin atguigu");
        return Result.ok(map);
    }*/
        //获取请求头token字符串
        String token = request.getHeader("token");

        //从token字符串获取用户名称（id）
        String username = JwtHelper.getUsername(token);

        //根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
        Map<String,Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }
}