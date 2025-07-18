package com.jida.tijian.service;

import com.jida.tijian.domain.Orders;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersMapper ordersMapper;


    @Override
    public ResponseObject saveOrders(Orders orders){
        try {
            ordersMapper.insert(orders);
            return new ResponseObject(ResponseObject.SUCCESS_STATUS, ResponseObject.SUCCESS_DESC, orders);
        } catch (Exception e) {
            return new ResponseObject(500, "订单保存失败", null);
        }
    }
}
