package com.jida.tijian.controller;


import com.jida.tijian.dto.CalendarRequestDto;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    @RequestMapping("/listCalendar")
    public ResponseObject listCalender(@RequestBody CalendarRequestDto calendarRequestDto){
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,calendarService.listAppointmentCalendar(calendarRequestDto));

    }




//    @RestController
//    public class CalendarController {
//
//        @Autowired
//        CalendarService calendarService;
//
//        @RequestMapping("/listAppoinmentCalendar")
//        public ApiResponse listAppoinmentCalendar(@RequestBody CalendarRequestDto calendarRequestDto, HttpServletRequest request){
//            return new ApiResponse(ApiResponse.SUCCESS_STATUS,ApiResponse.SUCCESS_DESC,calendarService.listAppointmentCalendar(calendarRequestDto));
//        }
//
//        @RequestMapping("/listAppoinmentCalendar2")
//        public ApiResponse list2(){
//            return new ApiResponse(ApiResponse.SUCCESS_STATUS,ApiResponse.SUCCESS_DESC,calendarService.listAppointmentCalendar(calendarRequestDto));
//        }

    //}
}
