package com.sebn.backend.dtos;

import lombok.Data;

import java.time.Month;
import java.time.Year;
import java.util.Date;

@Data
public class KPIValueDTO {
    private Long valueId;
    private float value;
    private Month month;
    private Year year;
    private Long kpiId;

    private String kpiName;
}
