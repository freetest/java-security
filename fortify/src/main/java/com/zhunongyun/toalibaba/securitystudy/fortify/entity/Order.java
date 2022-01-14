package com.zhunongyun.toalibaba.securitystudy.fortify.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单表
 * @author oscar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order")
public class Order {
    private Long id;

    private String name;

    private Float price;

    private String address;

    private String phone;

    @TableField("user_name")
    private String userName;
}
