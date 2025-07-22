package com.jida.tijian.controller;

import com.jida.tijian.domain.Doctordetail;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.service.DoctorDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorDetailController {

    @Autowired
    DoctorDetailService doctorDetailService;

    @RequestMapping("/listDoctordetail")
    public ResponseObject listDoctordetail() {
        return doctorDetailService.listDoctordetail();
    }
}