package com.jida.tijian.controller;

import com.jida.tijian.domain.Users;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public ResponseObject<Users> login(@RequestBody Users users){//json对象-->java对象

        System.out.println("Users:"+users.getUserId()+","+users.getPassword());
        //Users u = users;

        //ResponseObject<Users> responseObject = new ResponseObject<>(200,"成功",u);
        ResponseObject<Users> responseObject = usersService.login(users);

        return responseObject;

    }

}
