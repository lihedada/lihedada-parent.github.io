package com.system.mapper;


import com.atguigu.model.system.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author atguigulihe
 * @since 2022-11-07
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    //根据userid  查询这个用户权限
    List<SysMenu> findMenuListUserId(@Param("userId") String userId);

}
