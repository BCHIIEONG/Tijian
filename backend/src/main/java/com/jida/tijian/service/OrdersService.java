package com.jida.tijian.service;

import com.jida.tijian.domain.Orders;
import com.jida.tijian.dto.ResponseObject;

public interface OrdersService {
    ResponseObject saveOrders(Orders orders);
}
