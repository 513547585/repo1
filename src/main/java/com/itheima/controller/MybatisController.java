package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j
@RestController
@Api(value = "v1", description = "查询接口1")
@RequestMapping("v1")
public class MybatisController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "查询接口11111", httpMethod = "GET")
    @RequestMapping(value = "/logins", method = RequestMethod.GET)
    public List<User> queryUserList() {
        List<User> users = userMapper.queryUserList();
        return users;
    }
}