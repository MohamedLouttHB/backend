/*
package com.sebn.backend.services;

import com.sebn.backend.dtos.RequestManagerDTO;
import com.sebn.backend.dtos.RespondManagerDTO;
import com.sebn.backend.entities.Department;
import com.sebn.backend.entities.Manager;
import com.sebn.backend.mappers.MapperImpl;
import com.sebn.backend.reposotories.DepartmentRepository;
import com.sebn.backend.reposotories.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService{
    private ManagerRepository managerRepository;
    private DepartmentRepository departmentRepository;
    private MapperImpl dtoMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RespondManagerDTO saveManager(RequestManagerDTO requestManagerDTO) {
        Manager manager = dtoMapper.toManager(requestManagerDTO);
        // Encode the password before saving
        manager.setPassword(passwordEncoder.encode(requestManagerDTO.getPassword()));
        Manager savedManager = managerRepository.save(manager);
        return dtoMapper.toRespondManagerDTO(savedManager);
    }

    @Override
    public List<RespondManagerDTO> getAllManagers() {
        List<Manager> managers = managerRepository.findAll();
        return managers.stream()
                .map(dtoMapper::toRespondManagerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RespondManagerDTO getManagerById(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Manager not found with id: " + id));
        return dtoMapper.toRespondManagerDTO(manager);
    }

    @Override
    public RespondManagerDTO updateManager(RequestManagerDTO requestManagerDTO, Long id) {
        Manager existingManager = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found with id: " + id));

        // Update fields from DTO
        existingManager.setName(requestManagerDTO.getName());
        existingManager.setEmail(requestManagerDTO.getEmail());
        // Handle password securely

        // Set the department
        if (requestManagerDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(requestManagerDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + requestManagerDTO.getDepartmentId()));
            existingManager.setDepartment(department);
        } else {
            existingManager.setDepartment(null); // Consider what to do if departmentId is null
        }

        Manager savedManager = managerRepository.save(existingManager);
        return dtoMapper.toRespondManagerDTO(savedManager);
    }


    @Override
    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }
}
*/
