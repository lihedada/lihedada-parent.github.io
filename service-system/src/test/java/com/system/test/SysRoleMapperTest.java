package com.system.test;/*
@author shkstart
@Date2022-10-28-20:28
*/

import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@MapperScan("com.system.mapper")
public class SysRoleMapperTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    //6..条件删除
    @Test
    public void testDelete(){
        //创建条件构造器
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        //设置条件
        wrapper.eq("role_name","用户管理员");
       sysRoleMapper.delete(wrapper);
    }

    //5.条件查询
    @Test
    public void testSelect(){
        //创建条件构造器
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        //设置条件
        wrapper.eq("role_name","用户管理员");
        //调用方法查询
        List<SysRole> list = sysRoleMapper.selectList(wrapper);
        System.out.println(list);
    }

    //4.批量删除
    @Test
    public void testBatchDelete(){
        sysRoleMapper.deleteBatchIds(Arrays.asList(9,10));
    }

    //4.删除
    @Test
    public void deleteId(){
        int rows = sysRoleMapper.deleteById(10);
    }

    //3.修改操作
    @Test
    public void update(){
        //根据id查询
        SysRole sysRole = sysRoleMapper.selectById(1);
        //设置修改值
        sysRole.setDescription("超级管理员");
        //调用方法实现修改
        sysRoleMapper.updateById(sysRole);
    }

    //2.添加操作
    @Test
    public void add(){
        SysRole sysRole =new SysRole();
        sysRole.setRoleName("测试角色");
        sysRole.setDescription("testmanger");
        sysRole.setRoleCode("这是添加");
        int rows = sysRoleMapper.insert(sysRole);//返回行数rows
        System.out.println(rows );
    }

    //1.查询表的所有记录
    @Test
    public void findAll(){
        List<SysRole> list = sysRoleMapper.selectList(null);
        for (SysRole sysRole:list){
            System.out.println(sysRole);
        }
    }
}
