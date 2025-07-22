package com.jida.tijian.mapper;

import com.jida.tijian.domain.Disease;
import com.jida.tijian.domain.DiseaseWithBLOBs;

import java.util.List;

public interface DiseaseMapper {
    int deleteByPrimaryKey(Integer disease_id);

    int insert(DiseaseWithBLOBs record);

    int insertSelective(DiseaseWithBLOBs record);

    DiseaseWithBLOBs selectByPrimaryKey(Integer disease_id);

    List<DiseaseWithBLOBs> selectByCategoryId(Integer categoryId);

    int updateByPrimaryKeySelective(DiseaseWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DiseaseWithBLOBs record);

    int updateByPrimaryKey(Disease record);
}