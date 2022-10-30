package com.itheima.mapper;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<User> queryUserList();


    @Delete("delete from user where username= #{username}")
    public String delelte_username(User username);



}
