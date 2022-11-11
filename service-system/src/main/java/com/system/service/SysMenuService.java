package com.system.service;


import com.atguigu.model.system.SysMenu;
import com.atguigu.model.vo.AssginMenuVo;
import com.atguigu.model.vo.RouterVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author atguigulihe
 * @since 2022-11-07
 */

@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public interface SysMenuService extends IService<SysMenu> {


    ////获取 菜单列表
    List<SysMenu> findNodes();

    //删除菜单
    void removeMenuById(String id);

    //更改用户状态
    void updateStatus(String id, Integer status);

    // 根据角色分配菜单
    List<SysMenu> findMenuByRoleId(String roleId);

    //给角色分配菜单权限
    void doAssign(AssginMenuVo assginMenuVo);

    //根据userid 查询菜单权限值
    List<RouterVo> getUserMenuList(String id);

    //根据userid 查询菜单权限值
    List<String> getUserButtonList(String id);
}
