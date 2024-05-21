package com.sebn.backend.security.user.repository;

import com.sebn.backend.security.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
