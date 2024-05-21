package com.sebn.backend.reposotories;

import com.sebn.backend.entities.Department;
import com.sebn.backend.entities.KPI;
import com.sebn.backend.entities.KPIValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.List;

public interface KPIRepository extends JpaRepository<KPI, Long> {
    List<KPI> findByDepartment(Department department);

    @Query("SELECT kv FROM KPIValue kv WHERE kv.kpi.department.name = :deptName AND kv.year = :year")
    List<KPIValue> findByDepartmentAndYear(@Param("deptName") String deptName, @Param("year") Year year);

    @Query("SELECT k FROM KPI k WHERE k.department.id = :deptId")
    List<KPI> findByDepartmentId(@Param("deptId") Long deptId);



}
