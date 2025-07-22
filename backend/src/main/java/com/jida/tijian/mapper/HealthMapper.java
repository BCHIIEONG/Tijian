package com.jida.tijian.mapper;

import com.jida.tijian.domain.Health;

import java.util.List;

public interface HealthMapper {
    int deleteByPrimaryKey(Integer healthId);

    int insert(Health record);

    int insertSelective(Health record);

    Health selectByPrimaryKey(Integer healthId);

    List<Health> selectAll();

    int updateByPrimaryKeySelective(Health record);

    int updateByPrimaryKeyWithBLOBs(Health record);

    int updateByPrimaryKey(Health record);


}