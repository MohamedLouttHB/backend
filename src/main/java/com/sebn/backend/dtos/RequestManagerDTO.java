package com.sebn.backend.dtos;

import lombok.Data;

@Data
public class RequestManagerDTO {
    private Long managerId;
    private String name;
    private String email;
    private String password;
    private Long departmentId;
}
