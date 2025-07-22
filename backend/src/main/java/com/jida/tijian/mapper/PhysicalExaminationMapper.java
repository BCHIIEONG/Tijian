package com.jida.tijian.mapper;

import com.jida.tijian.domain.PhysicalExamination;

import java.util.List;

public interface PhysicalExaminationMapper {
    int deleteByPrimaryKey(Integer projectId);

    int insert(PhysicalExamination record);

    int insertSelective(PhysicalExamination record);

    PhysicalExamination selectByPrimaryKey(Integer projectId);

    List<PhysicalExamination> selectAll();

    int updateByPrimaryKeySelective(PhysicalExamination record);

    int updateByPrimaryKey(PhysicalExamination record);
}