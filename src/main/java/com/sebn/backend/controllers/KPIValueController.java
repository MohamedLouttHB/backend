package com.sebn.backend.controllers;

import com.sebn.backend.dtos.KPIValueDTO;
import com.sebn.backend.entities.KPIValue;
import com.sebn.backend.services.KPIValueService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/kpi-values")
@CrossOrigin("*")
//@PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
public class KPIValueController {

    private KPIValueService kpiValueService;

    @PostMapping
    public ResponseEntity<KPIValueDTO> createKpiValue(@RequestBody KPIValueDTO kpiValueDTO) {
        return ResponseEntity.ok(kpiValueService.saveKpiValue(kpiValueDTO));
    }

    @GetMapping
    public ResponseEntity<List<KPIValueDTO>> getAllKpiValues() {
        return ResponseEntity.ok(kpiValueService.getAllKpiValues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KPIValueDTO> getKpiValueById(@PathVariable Long id) {
        return ResponseEntity.ok(kpiValueService.getKpiValueById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<KPIValueDTO> updateKpiValue(@RequestBody KPIValueDTO kpiValueDTO, @PathVariable Long id) {
        return ResponseEntity.ok(kpiValueService.updateKpiValue(kpiValueDTO, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public void deleteKpiValue(@PathVariable Long id) {
        kpiValueService.deleteKpiValue(id);
        ResponseEntity.ok();
    }

    @GetMapping("/by-kpi/{kpiId}")
    public ResponseEntity<List<KPIValueDTO>> getKPIValuesByKPIId(@PathVariable Long kpiId) {
        List<KPIValueDTO> kpiValues = kpiValueService.findKPIValuesByKPIId(kpiId);
        return ResponseEntity.ok(kpiValues);
    }

    // Endpoint to get KPI Values by KPI ID and year
    @GetMapping("/by-year")
    public ResponseEntity<List<KPIValueDTO>> getKPIValuesByYear(
            @RequestParam("kpiId") Long kpiId,
            @RequestParam("year") Year year) {
        List<KPIValueDTO> kpiValues = kpiValueService.getKPIValuesByYear(kpiId, year);
        return ResponseEntity.ok(kpiValues);
    }

    @GetMapping("/kpis/{kpiId}/values/grouped")
    public ResponseEntity<Map<Year, List<KPIValueDTO>>> getKpiValuesGroupedByYear(@PathVariable Long kpiId) {
        Map<Year, List<KPIValueDTO>> groupedKpiValues = kpiValueService.getKpiValuesGroupedByYear(kpiId);
        return ResponseEntity.ok(groupedKpiValues);
    }

    @GetMapping("/years")
    public ResponseEntity<List<Year>> getDistinctYears() {
        return ResponseEntity.ok(kpiValueService.getDistinctYears());
    }

    @GetMapping("/by-department-year")
    public ResponseEntity<List<KPIValueDTO>> getKpiValuesByDepartmentAndYear(
            @RequestParam("departmentId") Long departmentId,
            @RequestParam("year") Year year) {
        List<KPIValueDTO> kpiValues = kpiValueService.getKpiValuesByDepartmentAndYear(departmentId, year);
        if (kpiValues.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(kpiValues);
    }

    @GetMapping("/by-department-year-kpi")
    public ResponseEntity<List<KPIValueDTO>> getKpiValuesByDepartmentAndYearAndKpiName(
            @RequestParam("departmentId") Long departmentId,
            @RequestParam("year") Year year,
            @RequestParam("kpiName") String kpiName) {
        List<KPIValueDTO> kpiValues = kpiValueService.getKpiValuesByDepartmentYearAndKpiName(departmentId, year, kpiName);
        return ResponseEntity.ok(kpiValues);
    }




}
