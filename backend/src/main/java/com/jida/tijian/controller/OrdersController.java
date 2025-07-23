package com.jida.tijian.controller;


import com.jida.tijian.domain.Orders;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @RequestMapping("/saveOrders")
    public ResponseObject saveOrders(@RequestBody Orders orders){
        return ordersService.saveOrders(orders);
    }

    @RequestMapping(value = "/listOrdersByUserIdAndState", method = RequestMethod.POST)
    public ResponseObject<List<Orders>> listOrdersByUserIdAndState(@RequestBody Map<String, Object> params){
        String userId = (String) params.get("userId");
        Integer state = (Integer) params.get("state");
        return ordersService.listOrdersByUserIdAndState(userId, state);
    }

    @RequestMapping(value = "/getAppointmentList", method = RequestMethod.POST)
    public ResponseObject<List<Orders>> getAppointmentList(@RequestBody Map<String, Object> params){
        String userId = (String) params.get("userId");
        return ordersService.getAppointmentList(userId);
    }

}
