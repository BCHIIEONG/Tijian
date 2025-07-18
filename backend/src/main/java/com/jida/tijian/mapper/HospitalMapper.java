package com.jida.tijian.mapper;

import com.jida.tijian.domain.Hospital;

import java.util.List;

public interface HospitalMapper {
    int deleteByPrimaryKey(Integer hpId);

    int insert(Hospital record);

    int insertSelective(Hospital record);

    Hospital selectByPrimaryKey(Integer hpId);

    List<Hospital> selectAll();

    int updateByPrimaryKeySelective(Hospital record);

    int updateByPrimaryKeyWithBLOBs(Hospital record);

    int updateByPrimaryKey(Hospital record);
}