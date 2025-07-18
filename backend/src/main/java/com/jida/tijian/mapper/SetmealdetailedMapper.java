package com.jida.tijian.mapper;

import com.jida.tijian.domain.Setmealdetailed;

import java.util.List;

public interface SetmealdetailedMapper {
    int deleteByPrimaryKey(Integer sdId);

    int insert(Setmealdetailed record);

    int insertSelective(Setmealdetailed record);

    Setmealdetailed selectByPrimaryKey(Integer sdId);

    List<Setmealdetailed> selectBySetmealId(Integer setmeal);

    int updateByPrimaryKeySelective(Setmealdetailed record);

    int updateByPrimaryKey(Setmealdetailed record);
}