package com.jida.tijian.service;

import com.jida.tijian.domain.Examine;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.mapper.ExamineMapper;
import com.jida.tijian.mapper.PhysicalExaminationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhysicalExaminationServiceServiceImpl implements PhysicalExaminationService {

    @Autowired
    PhysicalExaminationMapper physicalExaminationMapper;

    @Autowired
    ExamineMapper examineMapper;

    @Override
    public ResponseObject listExamineByProject(Integer projectId){
        List<Examine> examines = examineMapper.selectByprojectId(projectId);
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,examines);
    }

    @Override
    public ResponseObject listPhysicalExamination(){
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,physicalExaminationMapper.selectAll());
    }
}
