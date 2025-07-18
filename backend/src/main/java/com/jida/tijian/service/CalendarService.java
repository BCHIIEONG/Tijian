package com.jida.tijian.service;

import com.jida.tijian.dto.CalendarRequestDto;
import com.jida.tijian.dto.CalendarResponseDto;

import java.util.List;

public interface CalendarService {
    //生成前端界面需要的预约日历数据
    List<CalendarResponseDto> listAppointmentCalendar(CalendarRequestDto calendarRequestDto);

    public List<CalendarResponseDto> getCalendarList30(Integer hpId);
}
