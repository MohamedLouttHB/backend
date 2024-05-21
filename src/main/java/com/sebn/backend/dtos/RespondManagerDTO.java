package com.sebn.backend.dtos;

import lombok.Data;

@Data
public class RespondManagerDTO {
    private Long managerId;
    private String name;
    private String email;
    private Long departmentId;
}
