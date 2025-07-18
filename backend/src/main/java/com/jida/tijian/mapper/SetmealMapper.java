package com.jida.tijian.mapper;

import com.jida.tijian.domain.Setmeal;

import java.util.List;

public interface SetmealMapper {
    int deleteByPrimaryKey(Integer smId);

    int insert(Setmeal record);

    int insertSelective(Setmeal record);

    Setmeal selectByPrimaryKey(Integer smId);

    List<Setmeal> selectBySex(Integer sex);

    int updateByPrimaryKeySelective(Setmeal record);

    int updateByPrimaryKey(Setmeal record);
}