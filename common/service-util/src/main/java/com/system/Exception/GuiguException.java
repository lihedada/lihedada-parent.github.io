package com.system.Exception;/*
@author shkstart
@Date2022-10-29-14:51
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //生成get  set方法
@AllArgsConstructor  //有参构造
@NoArgsConstructor  //无参构造
public class GuiguException extends RuntimeException {

    private Integer code;
    private String msg;

}
