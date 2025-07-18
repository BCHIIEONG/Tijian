package com.jida.tijian.controller;


import com.jida.tijian.domain.Hospital;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HospitalController {

    @Autowired
    HospitalService hospitalService;

    @RequestMapping("/listHospital")
    public ResponseObject listHospital(){
        return hospitalService.listHospital();
    }

    @RequestMapping("/loadHospital")
    public ResponseObject loadHospital(@RequestBody Hospital hospital){
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,hospitalService.loadHospital(hospital));
    }




}
