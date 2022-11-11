package com.system.service;/*
@author shkstart
@Date2022-11-10-17:36
*/

import com.atguigu.model.system.SysLoginLog;
import com.atguigu.model.vo.SysLoginLogQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

//定义接口记录日志信息
public interface LoginLogService {
    public void recordLoginLog(String username,Integer status,String ipaddr,String message);


    //条件分页登录日志
    IPage<SysLoginLog> selectPage(long page, long limit, SysLoginLogQueryVo sysLoginLogQueryVo);
}
