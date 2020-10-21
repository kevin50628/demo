package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public Object user(@RequestParam("id1") String id1,@RequestParam("id2")  String id2){
        QueryWrapper<User> userWrapper  = new QueryWrapper<User>();
        userWrapper.eq("id1", id1).eq("id2", id2);
        Integer count = userService.count(userWrapper);
        if(count >1 ){
            //got data
            return userService.getOne(userWrapper);
        }else{
            User newUser = new User();
            newUser.setId1(id1);
            newUser.setId2(id2);
            String userId = UUID.randomUUID().toString().replaceAll("-","");
            newUser.setUserId(userId);
            boolean result = userService.save(newUser);
            return  newUser;
        }
    }
}

