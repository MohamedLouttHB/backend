package com.sebn.backend.services;

import com.sebn.backend.dtos.KPIDTO;
import com.sebn.backend.entities.Department;
import com.sebn.backend.entities.KPI;
import com.sebn.backend.mappers.MapperImpl;
import com.sebn.backend.reposotories.DepartmentRepository;
import com.sebn.backend.reposotories.KPIRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KPIServiceImpl implements KPIService{
    private KPIRepository kpiRepository;
    private DepartmentRepository departmentRepository;
    private final MapperImpl dtoMapper;


    @Override
    public KPIDTO saveKpi(KPIDTO kpiDTO) {
        KPI kpi = dtoMapper.fromKPIDTO(kpiDTO); // Converts and sets the department
        KPI savedKPI = kpiRepository.save(kpi);
        return dtoMapper.toKPIDTO(savedKPI);
    }

    @Override
    public List<KPIDTO> getAllKpis() {
        List<KPI> kpis = kpiRepository.findAll();
        return kpis.stream()
                .map(dtoMapper::toKPIDTO)
                .collect(Collectors.toList());    }

    @Override
    public KPIDTO getKpiById(Long id) {
        KPI kpi = kpiRepository.findById(id).orElseThrow(
                () -> new RuntimeException("KPI not found with id: " + id));
        return dtoMapper.toKPIDTO(kpi);   }

    @Override
    public KPIDTO updateKpi(KPIDTO kpiDTO, Long id) {
        KPI existingKPI = kpiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KPI not found with id: " + id));

        // Update fields
        existingKPI.setName(kpiDTO.getName());
        existingKPI.setTarget(kpiDTO.getTarget());
        existingKPI.setDescription(kpiDTO.getDescription());

        // Update Department
        if (kpiDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(kpiDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + kpiDTO.getDepartmentId()));
            existingKPI.setDepartment(department);
        } else {
            existingKPI.setDepartment(null); // Handle null departmentId appropriately
        }

        KPI updatedKPI = kpiRepository.save(existingKPI);
        return dtoMapper.toKPIDTO(updatedKPI);
    }

    @Override
    public void deleteKpi(Long id) {
        kpiRepository.deleteById(id);
    }

    public List<KPIDTO> findKPIsByDepartmentName(String departmentName) {
        Department department = departmentRepository.findByName(departmentName)
                .orElseThrow(() -> new RuntimeException("Department not found with name: " + departmentName));
        List<KPI> kpis = kpiRepository.findByDepartment(department);
        return kpis.stream().map(dtoMapper::toKPIDTO).collect(Collectors.toList());
    }

    @Override
    public List<KPIDTO> findKPIsByDepartmentId(Long departmentId) {
        List<KPI> kpis = kpiRepository.findByDepartmentId(departmentId);
        return kpis.stream().map(dtoMapper::toKPIDTO).collect(Collectors.toList());    }

}
