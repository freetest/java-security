package com.zhunongyun.toalibaba.javasecurity.fortify.enums;

/**
 * 返回响应码枚举类
 * @author lsiahqniu
 */
public enum ResponseCodeEnum {
    /**
     * 枚举实例
     */
    SUCCESS("000000", "操作成功"),
    FAIL("100000", "操作失败"),
    FAIL_JOB_CHECK_ERROR("100001", "请求参数校验异常"),
    SYSTEM_ERROR("200001", "系统异常");

    private final String code;

    /**
     * 消息
     */
    private final String message;

    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessageByCode(String code) {
        if (code == null) {
            return null;
        }

        ResponseCodeEnum responseCodeEnum = getByCode(code);

        if (responseCodeEnum == null) {
            return null;
        }
        return responseCodeEnum.getMessage();
    }

    private static ResponseCodeEnum getByCode(String code) {
        for (ResponseCodeEnum responseCodeEnum : values()) {
            if (responseCodeEnum.getCode().equals(code)) {
                return responseCodeEnum;
            }
        }
        return null;
    }
}