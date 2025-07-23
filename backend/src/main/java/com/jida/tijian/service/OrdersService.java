package com.jida.tijian.service;

import com.jida.tijian.domain.Orders;
import com.jida.tijian.dto.ResponseObject;

import java.util.List;

public interface OrdersService {
    ResponseObject saveOrders(Orders orders);
    ResponseObject<List<Orders>> listOrdersByUserIdAndState(String userId, Integer state);
    ResponseObject<List<Orders>> getAppointmentList(String userId);
}
