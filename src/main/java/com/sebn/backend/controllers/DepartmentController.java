package com.sebn.backend.controllers;

import com.sebn.backend.dtos.DepartmentDTO;
import com.sebn.backend.dtos.RespondManagerDTO;
import com.sebn.backend.entities.Department;
import com.sebn.backend.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/departments")
@CrossOrigin("*")
//@PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentService.saveDepartment(departmentDTO));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO department, @PathVariable Long id) {
        return ResponseEntity.ok(departmentService.updateDepartment(department, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        ResponseEntity.ok();
    }

/*    @GetMapping("/managers")
    public ResponseEntity<List<RespondManagerDTO>> getManagersByDepartmentName(@RequestParam String departmentName) {
        List<RespondManagerDTO> managers = departmentService.findManagersByDepartmentName(departmentName);
        return ResponseEntity.ok(managers);
    }*/

    @GetMapping("/findByName")
    public ResponseEntity<Long> getDepartmentIdByName(@RequestParam String name) {
        DepartmentDTO department = departmentService.findDepartmentByName(name);
        return ResponseEntity.ok(department.getDepartmentId());
    }

}
