package com.sebn.backend.dtos;

import lombok.Data;

@Data
public class KPIDTO {
    private Long kpiId;
    private String name;
    private float target;
    private String description;
    private Long departmentId;

}
