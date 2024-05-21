package com.sebn.backend.services;

import com.sebn.backend.dtos.KPIValueDTO;
import com.sebn.backend.entities.KPI;
import com.sebn.backend.entities.KPIValue;
import com.sebn.backend.mappers.MapperImpl;
import com.sebn.backend.reposotories.KPIRepository;
import com.sebn.backend.reposotories.KPIValueRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KPIValueServiceImpl implements KPIValueService{

    private final KPIValueRepository kpiValueRepository;
    private final KPIRepository kpiRepository;
    private final MapperImpl mapper;

    @Override
    public KPIValueDTO saveKpiValue(KPIValueDTO kpiValueDTO) {
        // Convert DTO to entity
        KPIValue kpiValue = mapper.fromKPIValueDTO(kpiValueDTO);

        // Find the KPI entity by ID
        KPI kpi = kpiRepository.findById(kpiValueDTO.getKpiId()).orElseThrow(
                () -> new RuntimeException("KPI not found with id: " + kpiValueDTO.getKpiId()));
        kpiValue.setKpi(kpi);

        // Check if a KPIValue for the specified kpi_id, month, and year already exists
        Optional<KPIValue> existingKpiValue = kpiValueRepository.findByKpiAndMonthAndYear(kpi, kpiValue.getMonth(), kpiValue.getYear());

        if (existingKpiValue.isPresent())
            throw new RuntimeException("A KPI value for the specified month and year already exists.");

        // Save the new KPIValue if it doesn't exist already
        KPIValue savedKPIValue = kpiValueRepository.save(kpiValue);
        return mapper.toKPIValueDTO(savedKPIValue);
    }



    @Override
    public List<KPIValueDTO> getAllKpiValues() {
        List<KPIValue> kpiValues = kpiValueRepository.findAll();
        return kpiValues.stream().map(mapper::toKPIValueDTO).collect(Collectors.toList());    }

    @Override
    public KPIValueDTO getKpiValueById(Long id) {
        KPIValue kpiValue = kpiValueRepository.findById(id).orElseThrow(
                () -> new RuntimeException("KPI Value not found with id: " + id));
        return mapper.toKPIValueDTO(kpiValue);
    }

    @Override
    public KPIValueDTO updateKpiValue(KPIValueDTO kpiValueDTO, Long id) {
        KPIValue kpiValue = kpiValueRepository.findById(id).orElseThrow(
                () -> new RuntimeException("KPI Value not found with id: " + id));

        kpiValue.setValue(kpiValueDTO.getValue());
        kpiValue.setMonth(kpiValueDTO.getMonth());
        kpiValueDTO.setYear(kpiValue.getYear());
        // Re-set the KPI using the kpiId from the DTO
        kpiValue.setKpi(kpiRepository.findById(kpiValueDTO.getKpiId()).orElseThrow(
                () -> new RuntimeException("KPI not found with id: " + kpiValueDTO.getKpiId())));
        KPIValue updatedKPIValue = kpiValueRepository.save(kpiValue);
        return mapper.toKPIValueDTO(updatedKPIValue);
    }

    @Override
    public void deleteKpiValue(Long id) {
        kpiValueRepository.deleteById(id);
    }

    public List<KPIValueDTO> findKPIValuesByKPIId(Long kpiId) {
        List<KPIValue> kpiValues = kpiValueRepository.findByKpi_KpiId(kpiId);
        return kpiValues.stream().map(mapper::toKPIValueDTO).collect(Collectors.toList());
    }

    public List<KPIValueDTO> getKPIValuesByYear(Long kpiId, Year year) {
        List<KPIValue> kpiValues = kpiValueRepository.findByKpi_KpiIdAndYear(kpiId, year);
        return kpiValues.stream().map(mapper::toKPIValueDTO).collect(Collectors.toList());
    }

    public Map<Year, List<KPIValueDTO>> getKpiValuesGroupedByYear(Long kpiId) {
        List<KPIValue> kpiValues = kpiValueRepository.findAllByKpiIdOrderByYearDescMonthAsc(kpiId);
        return kpiValues.stream()
                .map(mapper::toKPIValueDTO)
                .collect(Collectors.groupingBy(KPIValueDTO::getYear, LinkedHashMap::new, Collectors.toList()));
    }

    @Override
    public List<Year> getDistinctYears() {
        return kpiValueRepository.findDistinctYears();
    }

    @Override
    public List<KPIValueDTO> getKpiValuesByDepartmentAndYear(Long departmentId, Year year) {
        List<KPIValue> kpiValues = kpiValueRepository.findByKpi_Department_DepartmentIdAndYear(departmentId, year);
        return kpiValues.stream().map(mapper::toKPIValueDTO).collect(Collectors.toList());
    }

    @Override
    public List<KPIValueDTO> getKpiValuesByDepartmentYearAndKpiName(Long departmentId, Year year, String kpiName) {
        List<KPIValue> kpiValues = kpiValueRepository.findByKpi_Department_DepartmentIdAndYearAndKpi_Name(departmentId, year, kpiName);
        return kpiValues.stream().map(mapper::toKPIValueDTO).collect(Collectors.toList());
    }


}
