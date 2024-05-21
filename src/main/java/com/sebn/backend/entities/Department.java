package com.sebn.backend.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    @Column(length = 30, nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "department")
    private List<KPI> KPIS;
    //@OneToMany(mappedBy = "department")
    //private List<Manager> managers;
}
