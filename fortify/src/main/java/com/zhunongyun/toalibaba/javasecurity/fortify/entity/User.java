package com.zhunongyun.toalibaba.javasecurity.fortify.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybatis.mate.annotation.FieldEncrypt;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @FieldEncrypt
    private String password;
}