package com.jida.tijian.service;

import com.jida.tijian.domain.Cireport;
import com.jida.tijian.domain.Overallresult;
import com.jida.tijian.dto.ResponseObject;

import java.util.List;

public interface ReportService {
    ResponseObject<List<Overallresult>> listOverallResult(Integer orderId);
    ResponseObject<List<Cireport>> listReport(Integer orderId);
}