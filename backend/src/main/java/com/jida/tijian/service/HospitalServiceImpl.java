package com.jida.tijian.service;

import com.jida.tijian.domain.Hospital;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.mapper.HospitalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    HospitalMapper hospitalMapper;


    @Override
    public ResponseObject listHospital(){
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,hospitalMapper.selectAll());
    }

    @Override
    public Hospital loadHospital(Hospital hospital) {
        return hospitalMapper.selectByPrimaryKey(hospital.getHpId());
    }
}
