package com.system.test;/*
@author shkstart
@Date2022-10-28-20:28
*/

import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com.system.mapper")
public class SysRoleServiceTest {

    @Autowired
    private SysRoleService sysRoleService;

    //1.查询表的所有记录
    @Test
    public void findAll() {
        List<SysRole> list = sysRoleService.list();
        System.out.println(list);
    }

    //2.添加操作
    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色");
        sysRole.setDescription("role");
        sysRole.setRoleCode("角色管理员");
        sysRoleService.save(sysRole);
    }

    //3.修改操作
    @Test
    public void update(){
        //根据id查询
        SysRole sysRole = sysRoleService.getById(1);
        //设置修改值
        sysRole.setDescription("超级管理员");
        //调用方法实现修改
        sysRoleService.updateById(sysRole);
    }


    //4.删除
    @Test
    public void remove(){
        sysRoleService.removeById(9);
    }

    //5.条件查询
    @Test
    public void select(){
        //创建条件构造器
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        //设置条件
        wrapper.eq("role_name", "用户管理员");
        //调用方法查询
        List<SysRole> list = sysRoleService.list(wrapper);
        System.out.println(list);
    }

}
