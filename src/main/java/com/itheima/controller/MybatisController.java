package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Log4j
@RestController
@Api(value = "v1", description = "获取信息登录相关接口")
@RequestMapping("v1")
public class MybatisController {

    @Autowired
    private UserMapper userMapper;
    //获取sql语句对象
    @Autowired
    private SqlSessionTemplate template;


    @ApiOperation(value = "查询所有用户信息", httpMethod = "GET")
    @RequestMapping(value = "/querys", method = RequestMethod.GET)
    public List<User> queryUserList(){
            List<User> users = template.selectList("getUserInfo");
            log.info("getUserInfo获取到的用户数量是" + "\n" +users.size()+users.toString());
            log.info("getUserInfo获取到的用户" + "\n"+users.toString());
            return users;
    }



    // 查询指定的用户信息信息
    @ApiOperation(value = "查询指定用户信息", httpMethod = "GET")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String queryUser(String user_name){
        try {

            User user = template.selectOne("user_name",user_name);
            String users=user.toString();
            log.info(users);
            return users;
        }catch (Exception e){
            String username="user_name：【"+user_name+"无该用户信息";
            log.info("没有查询到该数据");
            return username;
        }
    }




    @ApiOperation(value = "添加用户", httpMethod = "POST")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request, @RequestBody User user){
        log.info(user);

        String user_name=user.getName();
        if(user_name.length()>30){
            log.info("名称过长不能大于30");
        }
        List<User> users = template.selectList("user_name");
        log.info("查询用户信息"+users.toString());

        Boolean x = verifyCookies(request);

        log.info("获取的"+x);
        int result = 0;
        if (x != null) {

            result = template.insert("addUser", user);
        }
        if (result > 0) {
            log.info("添加用户的数量是:" + result);
            return true;
        }
        return false;
    }

    @ApiOperation(value = "删除用户", httpMethod = "POST")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete_username(HttpServletRequest request,@RequestBody  String username){
        //先查询
        String s=queryUser(username);

        //查询是否存在
        if(s.equals(username)) {
            try {
                //删除用户
                int a = template.delete("username", username);
                log.info("删除的数据" + a);
                return a + "用户：删除成功";
            } catch (Exception e) {

                return "未知原因，删除失败";
            }
        }else {
            return "该用户不存在，不可删除";
        }

    }


    //判断cookie
    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            log.info("cookies为空");
            return false;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")) {
                log.info("cookies验证通过");
                return true;
            }
        }
        return false;
    }
}