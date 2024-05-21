package com.sebn.backend.services;

import com.sebn.backend.dtos.KPIDTO;
import com.sebn.backend.entities.KPI;

import java.util.List;

public interface KPIService {
    KPIDTO saveKpi(KPIDTO kpiDTO);
    List<KPIDTO> getAllKpis();
    KPIDTO getKpiById(Long id);
    KPIDTO updateKpi(KPIDTO kpiDTO, Long id);
    void deleteKpi(Long id);

    List<KPIDTO> findKPIsByDepartmentName(String departmentName);
    List<KPIDTO> findKPIsByDepartmentId(Long departmentName);

}
