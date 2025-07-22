package com.jida.tijian.service;

import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.mapper.DoctordetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorDetailServiceImpl implements DoctorDetailService {

    @Autowired
    DoctordetailMapper doctordetailMapper;

    @Override
    public ResponseObject listDoctordetail(){
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,doctordetailMapper.selectAll());
    }
}
