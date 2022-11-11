package com.system.annotation;/*
@author shkstart
@Date2022-11-10-18:18
*/

import com.system.enums.BusinessType;
import com.system.enums.OperatorType;

import java.lang.annotation.*;

//自定义注解可以用在参数和方法上
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) //作用范围 运行时
@Documented
public @interface Log {
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    public boolean isSaveResponseData() default true;
}
