package com.zhunongyun.toalibaba.javasecurity.fortify.controller;


import com.zhunongyun.toalibaba.javasecurity.fortify.entity.User;
import com.zhunongyun.toalibaba.javasecurity.fortify.vo.ResponseVO;
import com.zhunongyun.toalibaba.javasecurity.fortify.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * SQL注入
 * @author oscar
 */
@Slf4j
@RestController
@RequestMapping("sql")
public class SqlInjectionController {

    private final UserService userService;

    public SqlInjectionController(UserService userService) {
        this.userService = userService;
    }

    /**
     * localhost:8090/sql/likeAndOrderBy?name=j' or 1=1 --+&orderByName=name
     * SQL注入 like 和 order by
     *
     * select * from t_user where name like '%J%'
     * 盲注: ’ and sleep(5) --+
     *
     * 报错注入 ' and (select extractvalue(1, concat('~', (select database()))))
     *
     * @param name
     * @param orderByName
     */
    @GetMapping(value = "likeAndOrderBy")
    public ResponseVO downloadFile(@RequestParam("name") String name, @RequestParam("orderByName") String orderByName) {
        List<User> userList = userService.queryUserByName(name, orderByName);
        return ResponseVO.success(userList);
    }
}