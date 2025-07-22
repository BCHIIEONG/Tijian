package com.jida.tijian.mapper;

import com.jida.tijian.domain.Doctordetail;

import java.util.List;

public interface DoctordetailMapper {
    int deleteByPrimaryKey(Integer doctorId);

    int insert(Doctordetail record);

    int insertSelective(Doctordetail record);

    Doctordetail selectByPrimaryKey(Integer doctorId);

    List<Doctordetail> selectAll();

    int updateByPrimaryKeySelective(Doctordetail record);

    int updateByPrimaryKeyWithBLOBs(Doctordetail record);

    int updateByPrimaryKey(Doctordetail record);
}