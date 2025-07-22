package com.jida.tijian.controller;

import com.jida.tijian.domain.DiseaseCategory;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.service.DiseaseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiseaseCategoryController {

    @Autowired
    DiseaseCategoryService diseaseCategoryService;

    @RequestMapping("/listDiseaseCategory")
    public ResponseObject listDiseaseCategory(){
        return diseaseCategoryService.listDiseaseCategory();
    }

    @RequestMapping("/loadDiseaseCategory")
    public ResponseObject loadDiseaseCategory(@RequestBody DiseaseCategory diseaseCategory) {
        DiseaseCategory result = diseaseCategoryService.loadDiseaseCategory(diseaseCategory);
        return new ResponseObject(ResponseObject.SUCCESS_STATUS, ResponseObject.SUCCESS_DESC, result);
    }

    @RequestMapping("/listDiseasesByCategory")
    public ResponseObject listDiseasesByCategory(@RequestParam Integer categoryId) {
        return diseaseCategoryService.listDiseasesByCategory(categoryId);
    }
}
