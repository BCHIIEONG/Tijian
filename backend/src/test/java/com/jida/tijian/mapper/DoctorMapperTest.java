package com.jida.tijian.mapper;

import com.jida.tijian.domain.Doctor;
import com.jida.tijian.dto.CalendarResponseDto;
import com.jida.tijian.dto.OrdersMapperDto;
import com.jida.tijian.service.CalendarService;
import com.jida.tijian.service.CalendarServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DoctorMapperTest {

    @Autowired
    CalendarService calendarService;

    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    OrdersMapper ordersMapper;

    DataSourceProperties dataSourceProperties;

    @Test
    void testListCalendar30(){
        //calendarService.getCalendarList30();
        List<CalendarResponseDto> calendarResponseDtos = calendarService.getCalendarList30(2);
        for (CalendarResponseDto cr:calendarResponseDtos){
            System.out.println(cr.getYmd()+","+cr.getTotal()+","+cr.getRemainder()+","+cr.getExisting());
        }
    }

    @Test
    void testListHpAppointmentNumber(){
        List<OrdersMapperDto> ordersMapperDtoList = new ArrayList<>();
        ordersMapperDtoList.add(new OrdersMapperDto(2,"2025-7-23"));
        ordersMapperDtoList.add(new OrdersMapperDto(2,"2025-7-24"));
        List<CalendarResponseDto> calendarResponseDtos = ordersMapper.listHospitalAppointmentNumber(ordersMapperDtoList);

        for (CalendarResponseDto cd:calendarResponseDtos){
            System.out.println(cd.getYmd()+","+cd.getExisting()+"，总号"+cd.getTotal()+"，余号："+cd.getRemainder());
        }
    }

    @Test
    void testselectByPrimaryKey(){

        Doctor doctor=doctorMapper.selectByPrimaryKey(1);

        System.out.println(doctor.getRealName());
    }


}
