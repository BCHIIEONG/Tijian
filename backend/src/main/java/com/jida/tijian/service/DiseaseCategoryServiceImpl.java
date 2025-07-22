package com.jida.tijian.service;

import com.jida.tijian.domain.Disease;
import com.jida.tijian.domain.DiseaseCategory;
import com.jida.tijian.domain.DiseaseWithBLOBs;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.mapper.DiseaseCategoryMapper;
import com.jida.tijian.mapper.DiseaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseCategoryServiceImpl implements DiseaseCategoryService {

    @Autowired
    DiseaseCategoryMapper diseaseCategoryMapper;


    @Autowired
    DiseaseMapper diseaseMapper;

    @Override
    public ResponseObject listDiseasesByCategory(Integer categoryId) {
        List<DiseaseWithBLOBs> diseases = diseaseMapper.selectByCategoryId(categoryId);
        return new ResponseObject(ResponseObject.SUCCESS_STATUS, ResponseObject.SUCCESS_DESC, diseases);
    }

    @Override
    public ResponseObject listDiseaseCategory(){

        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,diseaseCategoryMapper.selectAll());

    }

    @Override
    public DiseaseCategory loadDiseaseCategory(DiseaseCategory diseaseCategory) {
        return diseaseCategoryMapper.selectByPrimaryKey(diseaseCategory.getCategory_id());
    }

}
