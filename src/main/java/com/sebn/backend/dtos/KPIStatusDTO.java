package com.sebn.backend.dtos;

import lombok.Data;

import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;
@Data
public class KPIStatusDTO {
    private Long kpiId;
    private String kpiName;
    private Map<String, String> monthlyStatus = new LinkedHashMap<>();

    public KPIStatusDTO(String kpiName, Long kpiId) {
        this.kpiName = kpiName;
        this.kpiId = kpiId;
        initializeMonths();
    }

    public KPIStatusDTO(String kpiName) {
        this.kpiName = kpiName;
        initializeMonths();
    }


    public void initializeMonths() {
        for (Month month : Month.values()) {
            monthlyStatus.put(month.toString(), "not_ok");
        }
    }

    public void setMonthStatus(Month month, String status) {
        monthlyStatus.put(month.toString(), status);
    }
}
