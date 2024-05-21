package com.sebn.backend.security.user.controllers;

import com.sebn.backend.security.user.Role;
import com.sebn.backend.security.user.sercvices.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/security/roles")
@CrossOrigin("*")
//@PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public List<Role> getRoles(){
        return roleService.getRoles();
    }
}
