package com.system.mapper;/*
@author shkstart
@Date2022-11-10-17:41
*/

import com.atguigu.model.system.SysLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginLogMapper extends BaseMapper<SysLoginLog> {

}
