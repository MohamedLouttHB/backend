package com.sebn.backend.controllers;

import com.sebn.backend.dtos.KPIStatusDTO;
import com.sebn.backend.services.KPIStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/kpi_status")
@CrossOrigin("*")
public class KPIStatusController {
    private KPIStatusService kpiStatusService;


    @GetMapping("/by-dep-year")
    public ResponseEntity<?> getKPIStatus(@RequestParam String departmentName, @RequestParam int year) {
        try {
            List<KPIStatusDTO> statuses = kpiStatusService.getKPIStatusByDepartmentAndYear(departmentName, year);
            return new ResponseEntity<>(statuses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-year")
    public ResponseEntity<?> getKPIStatusForAllDepartments(@RequestParam int year) {
        try {
            Map<String, List<KPIStatusDTO>> statuses = kpiStatusService.getKPIStatusByYear(year);
            return new ResponseEntity<>(statuses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
