package com.zhunongyun.toalibaba.javasecurity.fortify.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybatis.mate.annotation.FieldEncrypt;

/**
 * @author oscar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user_money")
public class UserMoney {
    private Long id;
    private String name;

    @TableField("money_total")
    private Float moneyTotal;
}