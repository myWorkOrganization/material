package com.material.mapper;

import com.material.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public  interface UserMapper
{
    void insertUser(User paramUser);

    User selectUserInfo(User paramUser);

    User selectUser(long paramLong);
}