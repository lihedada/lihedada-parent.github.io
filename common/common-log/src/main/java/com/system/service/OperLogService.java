package com.system.service;/*
@author shkstart
@Date2022-11-10-18:47
*/

import com.atguigu.model.system.SysLoginLog;
import com.atguigu.model.system.SysOperLog;
import com.atguigu.model.vo.SysOperLogQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface OperLogService {

    public void saveSysLog(SysOperLog sysOperLog);

    //操作日志分页查询
    IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo);
}
