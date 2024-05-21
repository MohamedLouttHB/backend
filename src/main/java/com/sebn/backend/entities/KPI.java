package com.sebn.backend.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class KPI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kpiId;

    private String name;
    private float target;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Department department;

    @OneToMany(mappedBy = "kpi", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<KPIValue> kpiValues;

}
