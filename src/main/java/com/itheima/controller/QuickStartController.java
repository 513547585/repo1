package com.itheima.controller;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@Log4j
@RestController
@Api(value = "v1", description = "练习接口")
@RequestMapping(value = "/login", method = RequestMethod.GET)
public class QuickStartController {
    //http://localhost:8080/quick
    @RequestMapping("/quick")
    @ResponseBody
    public String quick() {
        return "springboot 访问成功!!!!!!!!!!!";
    }
}