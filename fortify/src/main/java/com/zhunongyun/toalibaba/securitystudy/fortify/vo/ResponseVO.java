package com.zhunongyun.toalibaba.securitystudy.fortify.vo;

import com.zhunongyun.toalibaba.securitystudy.fortify.enums.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息返回对象
 * @author oscar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO {

    private String code;

    private String msg;

    private Object data;

    public static ResponseVO success(){
        return success(null);
    }

    public static ResponseVO success(Object data){
        return new ResponseVO(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), data);
    }

    public static ResponseVO fail(ResponseCodeEnum responseCodeEnum, Object data){
        return new ResponseVO(responseCodeEnum.getCode(), responseCodeEnum.getMessage(), data);
    }
}