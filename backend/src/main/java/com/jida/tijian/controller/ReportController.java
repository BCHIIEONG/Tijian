package com.jida.tijian.controller;

import com.jida.tijian.domain.Cireport;
import com.jida.tijian.domain.Overallresult;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/listOverallResult", method = RequestMethod.POST)
    public ResponseObject<List<Overallresult>> listOverallResult(@RequestBody Map<String, Object> params) {
        try {
            Integer orderId = (Integer) params.get("orderId");
            if (orderId == null) {
                return new ResponseObject<>(400, "订单ID不能为空", null);
            }
            return reportService.listOverallResult(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<>(500, "查询总检结论失败", null);
        }
    }

    @RequestMapping(value = "/listReport", method = RequestMethod.POST)
    public ResponseObject<List<Cireport>> listReport(@RequestBody Map<String, Object> params) {
        try {
            Integer orderId = (Integer) params.get("orderId");
            if (orderId == null) {
                return new ResponseObject<>(400, "订单ID不能为空", null);
            }
            return reportService.listReport(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<>(500, "查询体检报告失败", null);
        }
    }
}