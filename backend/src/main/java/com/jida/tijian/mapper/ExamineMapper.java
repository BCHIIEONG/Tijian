package com.jida.tijian.mapper;

import com.jida.tijian.domain.Examine;

import java.util.List;

public interface ExamineMapper {
    int deleteByPrimaryKey(Integer examineId);

    int insert(Examine record);

    int insertSelective(Examine record);

    Examine selectByPrimaryKey(Integer examineId);

    List<Examine> selectByprojectId(Integer projectId);

    int updateByPrimaryKeySelective(Examine record);

    int updateByPrimaryKey(Examine record);
}