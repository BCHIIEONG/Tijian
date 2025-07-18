package com.jida.tijian.service;

import com.jida.tijian.domain.Hospital;
import com.jida.tijian.dto.ResponseObject;
import org.springframework.web.bind.annotation.RequestBody;

public interface HospitalService {
    ResponseObject listHospital();

    Hospital loadHospital(Hospital hospital);
}
