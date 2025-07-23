package com.jida.tijian.mapper;

import com.jida.tijian.domain.Overallresult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OverallresultMapper {
    int deleteByPrimaryKey(Integer orId);

    int insert(Overallresult record);

    int insertSelective(Overallresult record);

    Overallresult selectByPrimaryKey(Integer orId);

    int updateByPrimaryKeySelective(Overallresult record);

    int updateByPrimaryKey(Overallresult record);
    
    List<Overallresult> listByOrderId(@Param("orderId") Integer orderId);
}