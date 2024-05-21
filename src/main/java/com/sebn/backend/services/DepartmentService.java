package com.sebn.backend.services;

import com.sebn.backend.dtos.DepartmentDTO;
import com.sebn.backend.dtos.RespondManagerDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO getDepartmentById(Long id);
    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, Long id);
    void deleteDepartment(Long id);

    //List<RespondManagerDTO> findManagersByDepartmentName(String departmentName);

    DepartmentDTO findDepartmentByName(String name);
}
