/*
package com.sebn.backend.controllers;

import com.sebn.backend.dtos.RequestManagerDTO;
import com.sebn.backend.dtos.RespondManagerDTO;
import com.sebn.backend.entities.Manager;
import com.sebn.backend.services.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/managers")
@CrossOrigin("*")
public class ManagerController {
    private ManagerService managerService;


    @PostMapping
    public ResponseEntity<RespondManagerDTO> createManager(@RequestBody RequestManagerDTO managerDTO) {
        RespondManagerDTO savedManagerDTO = managerService.saveManager(managerDTO);
        return ResponseEntity.ok(savedManagerDTO);
    }

    @GetMapping
    public ResponseEntity<List<RespondManagerDTO>> getAllManagers() {
        List<RespondManagerDTO> managerDTOS = managerService.getAllManagers();
        return ResponseEntity.ok(managerDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespondManagerDTO> getManagerById(@PathVariable Long id) {
        RespondManagerDTO managerDTO = managerService.getManagerById(id);
        return ResponseEntity.ok(managerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespondManagerDTO> updateManager(@RequestBody RequestManagerDTO managerDTO, @PathVariable Long id) {
        RespondManagerDTO updatedManagerDTO = managerService.updateManager(managerDTO, id);
        return ResponseEntity.ok(updatedManagerDTO);
    }

    // Add a delete endpoint if you need one
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return ResponseEntity.noContent().build(); // Use no content as there's no response body needed
    }
}
*/
