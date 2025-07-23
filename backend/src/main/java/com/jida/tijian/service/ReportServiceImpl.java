package com.jida.tijian.service;

import com.jida.tijian.domain.Cireport;
import com.jida.tijian.domain.Overallresult;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.mapper.CireportMapper;
import com.jida.tijian.mapper.OverallresultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OverallresultMapper overallresultMapper;
    
    @Autowired
    private CireportMapper cireportMapper;

    @Override
    public ResponseObject<List<Overallresult>> listOverallResult(Integer orderId) {
        try {
            List<Overallresult> overallResults = overallresultMapper.listByOrderId(orderId);
            return new ResponseObject<>(ResponseObject.SUCCESS_STATUS, ResponseObject.SUCCESS_DESC, overallResults);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<>(500, "查询总检结论失败", null);
        }
    }

    @Override
    public ResponseObject<List<Cireport>> listReport(Integer orderId) {
        try {
            List<Cireport> cireports = cireportMapper.listByOrderId(orderId);
            return new ResponseObject<>(ResponseObject.SUCCESS_STATUS, ResponseObject.SUCCESS_DESC, cireports);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<>(500, "查询体检报告失败", null);
        }
    }
}