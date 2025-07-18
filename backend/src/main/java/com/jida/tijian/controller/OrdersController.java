package com.jida.tijian.controller;


import com.jida.tijian.domain.Orders;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @RequestMapping("/saveOrders")
    public ResponseObject saveOrders(@RequestBody Orders orders){
        return ordersService.saveOrders(orders);
    }

}
