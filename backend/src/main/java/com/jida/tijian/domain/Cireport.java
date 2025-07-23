package com.jida.tijian.domain;

import java.util.List;

public class Cireport {
    private Integer cirId;

    private Integer ciId;

    private String ciName;

    private Integer orderId;
    
    // 详细检查项列表
    private List<Cidetailedreport> cidetailedreportList;

    public Integer getCirId() {
        return cirId;
    }

    public void setCirId(Integer cirId) {
        this.cirId = cirId;
    }

    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
    }

    public String getCiName() {
        return ciName;
    }

    public void setCiName(String ciName) {
        this.ciName = ciName == null ? null : ciName.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    public List<Cidetailedreport> getCidetailedreportList() {
        return cidetailedreportList;
    }

    public void setCidetailedreportList(List<Cidetailedreport> cidetailedreportList) {
        this.cidetailedreportList = cidetailedreportList;
    }
}