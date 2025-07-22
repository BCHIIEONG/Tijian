package com.jida.tijian.service;

import com.jida.tijian.dto.ResponseObject;

public interface PhysicalExaminationService {
    ResponseObject listExamineByProject(Integer projectId);

    ResponseObject listPhysicalExamination();
}
