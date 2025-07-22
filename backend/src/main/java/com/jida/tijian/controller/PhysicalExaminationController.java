package com.jida.tijian.controller;


import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.service.PhysicalExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhysicalExaminationController {
    @Autowired
    PhysicalExaminationService  physicalExaminationService;

    @RequestMapping("/listPhysicalExamination")
    public ResponseObject listPhysicalExamination() {
        return physicalExaminationService.listPhysicalExamination();
    }

    @RequestMapping("/listExamineByProject")
    public ResponseObject listExamineByProject(@RequestParam Integer projectId) {
        return physicalExaminationService.listExamineByProject(projectId);
    }

}
