package com.sebn.backend.services;

import com.sebn.backend.dtos.RespondManagerDTO;
import com.sebn.backend.entities.Department;
import com.sebn.backend.dtos.DepartmentDTO;
import com.sebn.backend.mappers.MapperImpl;
import com.sebn.backend.reposotories.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
    private DepartmentRepository departmentRepository;
    private MapperImpl dtoMapper;
    //private final PasswordEncoder passwordEncoder; // Make sure to inject a password encoder


    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        Department department = dtoMapper.fromDepartmentDTO(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return dtoMapper.fromDepartment(savedDepartment);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS =  departments.stream()
                .map(department -> dtoMapper.fromDepartment(department))
                .collect(Collectors.toList());
        return departmentDTOS;
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department department =  departmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Department not found"));
        return dtoMapper.fromDepartment(department);
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, Long id) {
        DepartmentDTO existingDepartment = getDepartmentById(id);
        existingDepartment.setName(departmentDTO.getName());
        Department department = dtoMapper.fromDepartmentDTO(existingDepartment);
        Department savedDepartment = departmentRepository.save(department);
        return dtoMapper.fromDepartment(savedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

 /*   public List<RespondManagerDTO> findManagersByDepartmentName(String departmentName) {
        Department department = departmentRepository.findByName(departmentName)
                .orElseThrow(() -> new RuntimeException("Department not found with name: " + departmentName));

        return department.getManagers().stream()
                .map(dtoMapper::toRespondManagerDTO)
                .collect(Collectors.toList());
    }*/

    public DepartmentDTO findDepartmentByName(String name) {
        Department department =  departmentRepository.findByName(name).orElse(null);
        return dtoMapper.fromDepartment(department);
    }
}
