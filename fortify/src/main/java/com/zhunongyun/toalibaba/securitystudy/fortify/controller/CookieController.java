package com.zhunongyun.toalibaba.securitystudy.fortify.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhunongyun.toalibaba.securitystudy.fortify.entity.User;
import com.zhunongyun.toalibaba.securitystudy.fortify.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Cookie Security
 *
 * @author oscar
 * @date 2021/12/20 18:10
 */
@Slf4j
@RestController
@RequestMapping("cookie")
public class CookieController {

    /**
     * 存储用户对应的 token 信息
     */
    private static final Map<String, String> TOKEN_MAP = new HashMap<>();

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public String login(@RequestBody String requestBody, HttpServletResponse response) {
        log.info("Cookie Security 登录,入参:{}", requestBody);

        String name = null;
        String password = null;

        String[] requestData = requestBody.split("&");
        for (String requestDatum : requestData) {
            if (requestDatum.contains("name=")) {
                name = requestDatum.split("=")[1];
            } else if (requestDatum.contains("password=")) {
                password = requestDatum.split("=")[1];
            }
        }

        // 查询用户信息
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.lambda().eq(User::getName, name);
        User user = userService.getOne(userWrapper);
        if (null != user && StringUtils.isNotBlank(user.getPassword()) && user.getPassword().equals(password)) {
            String token = Md5Crypt.md5Crypt(("FortifyCsrfLoginUser"
                    + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8), new SecureRandom());
            TOKEN_MAP.put(name, token);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(7200);
            cookie.setSecure(true);
            cookie.setPath("/demo");
            response.addCookie(cookie);
            return "csrf/index.html";
        }

        return "csrf/login";
    }
}