package com.sebn.backend.services;

import com.sebn.backend.dtos.KPIValueDTO;
import com.sebn.backend.entities.KPIValue;

import java.time.Year;
import java.util.List;
import java.util.Map;

public interface KPIValueService {
    KPIValueDTO saveKpiValue(KPIValueDTO kpiValueDTO);
    List<KPIValueDTO> getAllKpiValues();
    KPIValueDTO getKpiValueById(Long id);
    KPIValueDTO updateKpiValue(KPIValueDTO kpiValueDTO, Long id);
    void deleteKpiValue(Long id);

    List<KPIValueDTO> findKPIValuesByKPIId(Long kpiId);

    List<KPIValueDTO> getKPIValuesByYear(Long kpiId, Year year);

    Map<Year, List<KPIValueDTO>> getKpiValuesGroupedByYear(Long kpiId);

    List<Year> getDistinctYears();

    List<KPIValueDTO> getKpiValuesByDepartmentAndYear(Long departmentId, Year year);

    List<KPIValueDTO> getKpiValuesByDepartmentYearAndKpiName(Long departmentId, Year year, String kpiName);
}
