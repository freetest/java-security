package com.zhunongyun.toalibaba.javasecurity.fortify.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhunongyun.toalibaba.javasecurity.fortify.entity.User;
import com.zhunongyun.toalibaba.javasecurity.fortify.entity.UserMoney;
import com.zhunongyun.toalibaba.javasecurity.fortify.service.UserMoneyService;
import com.zhunongyun.toalibaba.javasecurity.fortify.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;

/**
 * CSRF 跨站点请求伪造(Cross—Site Request Forgery)
 *
 * @author oscar
 * @date 2021/12/20 18:10
 */
@Slf4j
@Controller
@RequestMapping("csrf")
public class CsrfController {

    /**
     * 存储用户对应的 token 信息
     */
    private static final Map<String, String> TOKEN_MAP = new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    private UserMoneyService userMoneyService;

    @GetMapping("login")
    public String login() {
        return "csrf/login.html";
    }

    @PostMapping("login")
    public String login(@RequestBody String requestBody, HttpServletResponse response) {
        log.info("csrf 登录,入参:{}", requestBody);



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
            response.addCookie(cookie);
            return "csrf/index.html";
        }

        return "csrf/login";
    }

    @GetMapping("changeMoney")
    public String changeMoney(@RequestParam("name") String name,
                                  @RequestParam("money") Float money, HttpServletRequest request) {
        // 检查 token 是否正确
        String fromName = this.checkToken(request);

        if (StringUtils.isBlank(fromName)) {
            return "csrf/login";
        }

        // 转账逻辑
        // 查询转账用户账户金额
        QueryWrapper<UserMoney> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserMoney::getName, fromName);
        UserMoney formUserMoney = userMoneyService.getOne(queryWrapper);

        if (formUserMoney.getMoneyTotal() < money) {
            return "csrf/index";
        }

        queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserMoney::getName, name);
        UserMoney toUserMoney = userMoneyService.getOne(queryWrapper);

        formUserMoney.setMoneyTotal(formUserMoney.getMoneyTotal() - money);
        toUserMoney.setMoneyTotal(toUserMoney.getMoneyTotal() + money);

        List<UserMoney> dataList = new ArrayList<>(2);
        dataList.add(formUserMoney);
        dataList.add(toUserMoney);

        userMoneyService.updateBatchById(dataList);

        return "csrf/index";
    }

    private String checkToken(HttpServletRequest request) {
        // 获取 cookie 校验是否登录
        Cookie[] cookie = request.getCookies();

        Set<String> tokenSet = new HashSet<>();

        if (null == cookie || cookie.length == 0) {
            return null;
        }

        boolean flag = false;
        for (Cookie c : cookie) {
            if ("token".equals(c.getName())) {
                tokenSet.add(c.getValue());
                flag = true;
            }
        }

        if (!flag) {
            return null;
        }

        String fromName = null;
        flag = false;
        for (Map.Entry<String, String> map : TOKEN_MAP.entrySet()) {
            if (tokenSet.contains(map.getValue())) {
                fromName = map.getKey();
                flag = true;
                break;
            }
        }

        if (!flag) {
            return null;
        }

        return fromName;
    }
}