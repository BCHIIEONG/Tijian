package com.jida.tijian.mapper;

import com.jida.tijian.domain.Orders;
import com.jida.tijian.dto.CalendarResponseDto;
import com.jida.tijian.dto.OrdersMapperDto;

import java.util.List;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<CalendarResponseDto> listHospitalAppointmentNumber(List<OrdersMapperDto> ordersMapperDtos);
}