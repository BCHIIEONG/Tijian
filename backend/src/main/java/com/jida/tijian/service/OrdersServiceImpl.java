package com.jida.tijian.service;

import com.jida.tijian.domain.Orders;
import com.jida.tijian.domain.Users;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.mapper.OrdersMapper;
import com.jida.tijian.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersMapper ordersMapper;
    
    @Autowired
    UsersMapper usersMapper;


    @Override
    public ResponseObject saveOrders(Orders orders){
        try {
            ordersMapper.insert(orders);
            return new ResponseObject(ResponseObject.SUCCESS_STATUS, ResponseObject.SUCCESS_DESC, orders);
        } catch (Exception e) {
            return new ResponseObject(500, "订单保存失败", null);
        }
    }

    @Override
    public ResponseObject<List<Orders>> listOrdersByUserIdAndState(String userId, Integer state) {
        try {
            List<Orders> ordersList = ordersMapper.listOrdersByUserIdAndState(userId, state);
            
            // 获取用户姓名
            if (!ordersList.isEmpty()) {
                Users user = usersMapper.selectByPrimaryKey(userId);
                String userName = user != null ? user.getRealName() : "未知用户";
                
                // 为每个订单添加用户姓名
                for (Orders order : ordersList) {
                    order.setName(userName);
                }
            }
            
            return new ResponseObject<>(ResponseObject.SUCCESS_STATUS, ResponseObject.SUCCESS_DESC, ordersList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<>(500, "查询失败", null);
        }
    }

    @Override
    public ResponseObject<List<Orders>> getAppointmentList(String userId) {
        try {
            List<Orders> ordersList = ordersMapper.listOrdersByUserId(userId);
            
            // 获取用户姓名
            if (!ordersList.isEmpty()) {
                Users user = usersMapper.selectByPrimaryKey(userId);
                String userName = user != null ? user.getRealName() : "未知用户";
                
                // 为每个订单添加用户姓名
                for (Orders order : ordersList) {
                    order.setName(userName);
                }
            }
            
            return new ResponseObject<>(ResponseObject.SUCCESS_STATUS, ResponseObject.SUCCESS_DESC, ordersList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<>(500, "查询失败", null);
        }
    }
}
