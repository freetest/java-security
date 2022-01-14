package com.zhunongyun.toalibaba.javasecurity.fortify.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhunongyun.toalibaba.javasecurity.fortify.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 用户 mapper
 * @author oscar
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户信息
     * @param paramMap
     * @return
     */
    List<User> queryUserByName(Map<String, String> paramMap);

    /**
     * 更新用户密码
     * @param user
     */
    @Update("update user set password=#{u.password} where id=#{u.id}")
    void updatePasswordById(@Param("u") User user);
}