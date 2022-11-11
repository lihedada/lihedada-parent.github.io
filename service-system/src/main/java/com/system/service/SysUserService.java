package com.system.service;


import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author atguigulihe
 * @since 2022-11-06
 */
public interface SysUserService extends IService<SysUser> {


    //用户列表
    IPage<SysUser> selectpage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

    //更改用户状态
    void updateStatus(String id, Integer status);

    //username  查询
    SysUser getUserInfoByUserName(String username);

    //根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
    Map<String, Object> getUserInfo(String username);
}
