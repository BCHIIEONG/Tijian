package com.jida.tijian.controller;


import com.jida.tijian.domain.Orders;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @RequestMapping("/saveOrders")
    public ResponseObject saveOrders(@RequestBody Orders orders){
        return ordersService.saveOrders(orders);
    }

}
