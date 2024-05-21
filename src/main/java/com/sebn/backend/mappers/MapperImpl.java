package com.sebn.backend.mappers;

import com.sebn.backend.dtos.*;
import com.sebn.backend.entities.Department;
import com.sebn.backend.entities.KPI;
import com.sebn.backend.entities.KPIValue;
import com.sebn.backend.reposotories.DepartmentRepository;
import com.sebn.backend.security.user.Role;
import com.sebn.backend.security.user.User;
import com.sebn.backend.security.user.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MapperImpl {
    private DepartmentRepository departmentRepository;
    private RoleRepository roleRepository;

    public DepartmentDTO fromDepartment(Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(department, departmentDTO);
        return departmentDTO;
    }
    public Department fromDepartmentDTO(DepartmentDTO departmentDTO){
        Department department = new Department();
        BeanUtils.copyProperties(departmentDTO, department);
        return department;
    }

/*
    public RequestManagerDTO toRequestManagerDTO(Manager manager) {
        RequestManagerDTO requestManagerDTO = new RequestManagerDTO();
        BeanUtils.copyProperties(manager, requestManagerDTO);
        return requestManagerDTO;
    }

    public Manager toManager(RequestManagerDTO requestManagerDTO) {
        Manager manager = new Manager();
        BeanUtils.copyProperties(requestManagerDTO, manager);
        if (requestManagerDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(requestManagerDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            manager.setDepartment(department);
        }
        return manager;
    }


    public RespondManagerDTO toRespondManagerDTO(Manager manager) {
        RespondManagerDTO respondManagerDTO = new RespondManagerDTO();
        BeanUtils.copyProperties(manager, respondManagerDTO);
        if (manager.getDepartment() != null) {
            respondManagerDTO.setDepartmentId(manager.getDepartment().getDepartmentId());
        }
        return respondManagerDTO;
    }
*/



    public KPIDTO toKPIDTO(KPI kpi) {
        KPIDTO kpiDTO = new KPIDTO();
        BeanUtils.copyProperties(kpi, kpiDTO);
        if (kpi.getDepartment() != null) {
            kpiDTO.setDepartmentId(kpi.getDepartment().getDepartmentId());
        }
        return kpiDTO;
    }


    public KPI fromKPIDTO(KPIDTO kpiDTO) {
        KPI kpi = new KPI();
        BeanUtils.copyProperties(kpiDTO, kpi);
        if (kpiDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(kpiDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + kpiDTO.getDepartmentId()));
            kpi.setDepartment(department);
        }
        return kpi;
    }

    public KPIValue fromKPIValueDTO(KPIValueDTO kpiValueDTO){
        KPIValue kpiValue = new KPIValue();
        kpiValue.setValueId(kpiValueDTO.getValueId());
        kpiValue.setValue(kpiValueDTO.getValue());
        kpiValue.setMonth(kpiValueDTO.getMonth());
        kpiValue.setYear(kpiValueDTO.getYear());
        //BeanUtils.copyProperties(kpiValueDTO, kpiValue);
        return kpiValue;
    }
    public KPIValueDTO toKPIValueDTO(KPIValue kpiValue) {
        KPIValueDTO kpiValueDTO = new KPIValueDTO();
        kpiValueDTO.setValueId(kpiValue.getValueId());
        kpiValueDTO.setValue(kpiValue.getValue());
        kpiValueDTO.setMonth(kpiValue.getMonth());
        kpiValueDTO.setYear(kpiValue.getYear());
        if (kpiValue.getKpi() != null) { // Ensure KPI is not null before accessing its ID
            kpiValueDTO.setKpiId(kpiValue.getKpi().getKpiId());
            kpiValueDTO.setKpiName(kpiValue.getKpi().getName());
        }
        return kpiValueDTO;
    }

    public Role toRole(RoleDTO roleDTO){
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        return role;
    }

    public Role toRole(String roleName){
        //Role role = new Role();
        //BeanUtils.copyProperties(roleName, role);
        Role role = roleRepository.findByName(roleName);
        return role;

    }

    public User toUser(UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        Role role = roleRepository.findByName(userDTO.getRole());
        user.setRole(role);
        return user;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole() != null ? user.getRole().getName() : null);
        return userDTO;
    }

}
