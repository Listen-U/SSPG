package com.listen.sspg.service;

import com.listen.sspg.dao.UserMapper;
import com.listen.sspg.entity.User;
import com.listen.sspg.iservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Listen
 * @date 2019/2/26
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;

    public User getUser(){
        User user = userMapper.selectByPrimaryKey("1");
        return user;
    }

    public User findByOpenid(String id){
        return null;
    }

    public void insert(User user){

    }
}
