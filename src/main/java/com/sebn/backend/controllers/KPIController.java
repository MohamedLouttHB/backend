package com.sebn.backend.controllers;

import com.sebn.backend.dtos.KPIDTO;
import com.sebn.backend.entities.KPI;
import com.sebn.backend.services.KPIService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/kpis")
@CrossOrigin("*")
public class KPIController {
    private KPIService kpiService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<KPIDTO> createKpi(@RequestBody KPIDTO kpi) {
        return ResponseEntity.ok(kpiService.saveKpi(kpi));
    }

    @GetMapping
    public ResponseEntity<List<KPIDTO>> getAllKpis() {
        return ResponseEntity.ok(kpiService.getAllKpis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KPIDTO> getKpiById(@PathVariable Long id) {
        return ResponseEntity.ok(kpiService.getKpiById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<KPIDTO> updateKpi(@RequestBody KPIDTO kpi, @PathVariable Long id) {
        return ResponseEntity.ok(kpiService.updateKpi(kpi, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public void deleteKpi(@PathVariable Long id) {
        kpiService.deleteKpi(id);
        ResponseEntity.ok();
    }

    @GetMapping("/by-department")
    public ResponseEntity<List<KPIDTO>> getKPIsByDepartmentName(@RequestParam String departmentName) {
        List<KPIDTO> kpis = kpiService.findKPIsByDepartmentName(departmentName);
        return ResponseEntity.ok(kpis);
    }

    @GetMapping("/by-department_ID")
    public ResponseEntity<List<KPIDTO>> getKPIsByDepartmentId(@RequestParam Long departmentId) {
        List<KPIDTO> kpis = kpiService.findKPIsByDepartmentId(departmentId);
        return ResponseEntity.ok(kpis);
    }

}
