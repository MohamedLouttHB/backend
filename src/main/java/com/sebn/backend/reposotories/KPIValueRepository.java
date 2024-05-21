package com.sebn.backend.reposotories;

import com.sebn.backend.dtos.KPIValueDTO;
import com.sebn.backend.entities.KPI;
import com.sebn.backend.entities.KPIValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Month;
import java.time.Year;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface KPIValueRepository extends JpaRepository<KPIValue, Long> {
    List<KPIValue> findByKpi_KpiId(Long kpiId);
    List<KPIValue> findByKpi_KpiIdAndYear(Long kpiId, Year year);

    @Query("SELECT kv FROM KPIValue kv WHERE kv.kpi.id = :kpiId ORDER BY kv.year DESC, kv.month ASC")
    List<KPIValue> findAllByKpiIdOrderByYearDescMonthAsc(@Param("kpiId") Long kpiId);
    Optional<KPIValue> findByKpiAndMonthAndYear(KPI kpi, Month month, Year year);
    @Query("SELECT kv FROM KPIValue kv WHERE kv.kpi.department.name = :deptName AND kv.year = :year")
    List<KPIValue> findByDepartmentAndYear(@Param("deptName") String deptName, @Param("year") Year year);

    @Query("SELECT kv FROM KPIValue kv WHERE kv.year = :year")
    List<KPIValue> findByYear(@Param("year") Year year);

    @Query("SELECT DISTINCT kv.year FROM KPIValue kv ORDER BY kv.year")
    List<Year> findDistinctYears();


    List<KPIValue> findByKpi_Department_DepartmentIdAndYear(Long departmentId, Year year);

    List<KPIValue> findByKpi_Department_DepartmentIdAndYearAndKpi_Name(Long departmentId, Year year, String kpiName);


}
