package com.sebn.backend.services;

import com.sebn.backend.dtos.KPIStatusDTO;
import com.sebn.backend.entities.KPIValue;
import com.sebn.backend.reposotories.KPIRepository;
import com.sebn.backend.reposotories.KPIValueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KPIStatusService {
    private KPIRepository kpiRepository;
    private KPIValueRepository kpiValueRepository;


    public List<KPIStatusDTO> getKPIStatusByDepartmentAndYear(String departmentName, int year) {
        Year yearObj = Year.of(year);
        List<KPIValue> values = kpiValueRepository.findByDepartmentAndYear(departmentName, yearObj);
        Map<Long, KPIStatusDTO> kpiStatusMap = new HashMap<>();

        for (KPIValue value : values) {
            KPIStatusDTO dto = kpiStatusMap.computeIfAbsent(value.getKpi().getKpiId(), k -> new KPIStatusDTO(value.getKpi().getName()));
            dto.setMonthStatus(value.getMonth(), "OK");
        }

        return new ArrayList<>(kpiStatusMap.values());
    }


    public Map<String, List<KPIStatusDTO>> getKPIStatusByYear(int year) {
        Year yearObj = Year.of(year);
        List<KPIValue> values = kpiValueRepository.findByYear(yearObj);
        Map<String, List<KPIStatusDTO>> departmentStatusMap = new HashMap<>();

        for (KPIValue value : values) {
            String departmentName = value.getKpi().getDepartment().getName();
            departmentStatusMap.putIfAbsent(departmentName, new ArrayList<>());

            List<KPIStatusDTO> kpiList = departmentStatusMap.get(departmentName);
            Optional<KPIStatusDTO> dtoOpt = kpiList.stream()
                    .filter(dto -> dto.getKpiId().equals(value.getKpi().getKpiId()))
                    .findFirst();

            KPIStatusDTO dto;
            if (dtoOpt.isPresent()) {
                dto = dtoOpt.get();
            } else {
                dto = new KPIStatusDTO(value.getKpi().getName(), value.getKpi().getKpiId());
                kpiList.add(dto);
                dto.initializeMonths();  // Initialize months only when creating a new DTO
            }
            dto.setMonthStatus(value.getMonth(), "OK");
        }

        // Debug output
/*
        departmentStatusMap.forEach((dept, kpis) -> {
            System.out.println("Department: " + dept);
            kpis.forEach(kpi -> {
                System.out.println("KPI: " + kpi.getKpiName() + " Status: " + kpi.getMonthlyStatus());
            });
        });
*/

        return departmentStatusMap;
    }

}
