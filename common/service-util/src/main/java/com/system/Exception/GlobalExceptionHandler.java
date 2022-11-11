package com.system.Exception;/*
@author shkstart
@Date2022-10-29-14:31
*/

import com.common.result.Result;
import com.common.result.ResultCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



//表示通过aop方式把 这个注解加上异常处理方式
@ControllerAdvice
public class GlobalExceptionHandler {

    //1.全局异常处理
    @ResponseBody  //返回json的数据
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail().message("执行了全局异常处理");
    }

    //2.特定异常处理
    @ResponseBody  //返回json的数据
    @ExceptionHandler(ArithmeticException.class)
    public Result error(ArithmeticException e){
        e.printStackTrace();//打印结果
        return Result.fail().message("执行了特定异常处理");
    }

    //2.自定义异常处理
    @ResponseBody  //返回json的数据
    @ExceptionHandler(GuiguException.class)
    public Result error(GuiguException e){
        e.printStackTrace();//打印结果
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }
    /**
     * spring security异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result error(org.springframework.security.access.AccessDeniedException e) throws AccessDeniedException {
        return Result.fail().code(ResultCodeEnum.PERMISSION.getCode()).message("没有当前功能权限");
    }

}
