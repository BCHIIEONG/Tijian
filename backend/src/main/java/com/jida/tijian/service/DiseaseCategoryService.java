package com.jida.tijian.service;

import com.jida.tijian.domain.DiseaseCategory;
import com.jida.tijian.domain.Doctordetail;
import com.jida.tijian.dto.ResponseObject;

public interface DiseaseCategoryService {
    ResponseObject listDiseaseCategory();

    ResponseObject listDiseasesByCategory(Integer categoryId);

    DiseaseCategory loadDiseaseCategory(DiseaseCategory diseaseCategory);
}
