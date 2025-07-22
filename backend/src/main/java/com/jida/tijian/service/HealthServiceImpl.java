package com.jida.tijian.service;

import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.mapper.HealthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl implements HealthService {

    @Autowired
    HealthMapper healthMapper;

    @Override
    public ResponseObject listHealth(){
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,healthMapper.selectAll());
    }
}
