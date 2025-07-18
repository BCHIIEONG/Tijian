package com.jida.tijian.controller;

import com.jida.tijian.domain.Setmeal;
import com.jida.tijian.domain.Users;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetmealController {

    @Autowired
    SetmealService setmealService;

    @RequestMapping("/listSetmeal")
    public ResponseObject listSetmeal(@RequestBody Users users){
        return setmealService.listSetmeal(users.getSex());
    }

    @RequestMapping("/loadSetmeal")
    public ResponseObject loadSetmeal(@RequestBody Setmeal setmeal){
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,setmealService.loadSetmeal(setmeal));
    }

}
