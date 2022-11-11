package com.system.controller;/*
@author shkstart
@Date2022-10-28-22:55
*/

import com.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.AssginRoleVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.system.Exception.GuiguException;
import com.system.annotation.Log;
import com.system.enums.BusinessType;
import com.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("获取用户的角色数据")
    @GetMapping("toAssign/{userId}")
        public Result toAssign(@PathVariable String userId) {
        Map<String,Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }

    @ApiOperation("用户分配角色")
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }

    //7.批量删除  多个id[1,2,3]
    //json的数组格式对应着java的list集合
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids) {

        sysRoleService.removeByIds(ids);
        return Result.ok();

    }

    //6.修改-最终
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation("最终修改")
    @PostMapping("update")
    public Result updateRole(@RequestBody SysRole sysRole) {
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if(isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //5.修改根据id查询
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("根据id查询")
    @PostMapping("findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id ) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }
    //4.添加
    //@RequestBody  不能使用get方式提交    传递json价格数据,把json格式 数据封装对象里面
    @Log(title = "角色管理",businessType = BusinessType.INSERT)
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result saveRole(@RequestBody SysRole sysRole){
        boolean issuccess = sysRoleService.save(sysRole);
        if(issuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //3.条件分页查询
    //page当前页  limit每页 记录数
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result findPageQueryRole(@PathVariable long page,
                                    @PathVariable long limit,
                                    SysRoleQueryVo sysRoleQueryVo){
        //创建page对象
        Page<SysRole> pageParam = new Page<>(page,limit);
        //调用service 方法
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam,sysRoleQueryVo);
        //返回
        return Result.ok(pageModel);
    }

    //2.逻辑删除接口
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("remove/{id}")
    public Result removeRole(@PathVariable Long id) {
        //调用方法删除
        boolean isSuccess = sysRoleService.removeById(id);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //1.查询所有记录
    @ApiOperation("查询所有记录接口")
    @GetMapping("findAll")
    public Result findAllRole(){
        //额外模拟异常ArithmeticException
        try {
            int i = 9 / 0;
        }catch (Exception e){
            throw new GuiguException(20001,"执行异常处理");
        }
        //调用service
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }
}
