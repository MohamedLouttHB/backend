package com.sebn.backend.reposotories;

import com.sebn.backend.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //Department findByName(String name);
    Optional<Department> findByName(String name);


}
