package com.system.service.impl;/*
@author shkstart
@Date2022-10-28-21:56
*/

import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysUserRole;
import com.atguigu.model.vo.AssginRoleVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.mapper.SysRoleMapper;
import com.system.mapper.SysUserRoleMapper;
import com.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    //条件分页查询
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam,sysRoleQueryVo);
        return pageModel;
    }

    //获取用户的角色数据
    @Override
    public Map<String, Object> getRolesByUserId(String userId) {
        List<SysRole> roles = baseMapper.selectList(null);
        //根据用户id,已经分配角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<SysUserRole> userRolesList = sysUserRoleMapper.selectList(wrapper);
        //获取所有userRoles角色id
        List<String> userRoleIds = new ArrayList<>();
        for(SysUserRole userRole : userRolesList){
            String roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
        }
        //创建返回的Map
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("allRoles",roles);
        returnMap.put("userRoleIds",userRoleIds);
        return returnMap;
    }

    //用户分配角色
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //根据用户id删除之前分配角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",assginRoleVo.getUserId());
        sysUserRoleMapper.delete(wrapper);
        //获取所有角色id，添加角色用户关系表
        //角色id列表
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        for (String roleId:roleIdList) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(assginRoleVo.getUserId());
            userRole.setRoleId(roleId);
            sysUserRoleMapper.insert(userRole);
        }

    }
}
