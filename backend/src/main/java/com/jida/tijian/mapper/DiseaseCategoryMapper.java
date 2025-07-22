package com.jida.tijian.mapper;

import com.jida.tijian.domain.DiseaseCategory;

import java.util.List;

public interface DiseaseCategoryMapper {
    int deleteByPrimaryKey(Integer category_id);

    int insert(DiseaseCategory record);

    int insertSelective(DiseaseCategory record);

    DiseaseCategory selectByPrimaryKey(Integer category_id);

    List<DiseaseCategory> selectAll();

    int updateByPrimaryKeySelective(DiseaseCategory record);

    int updateByPrimaryKeyWithBLOBs(DiseaseCategory record);

    int updateByPrimaryKey(DiseaseCategory record);
}