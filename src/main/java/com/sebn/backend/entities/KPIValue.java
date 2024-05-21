package com.sebn.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.time.Year;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "KPIValue", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"kpi_id", "month", "year"}, name = "unique_kpi_value_per_month_year")
})
public class KPIValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long valueId;

    private float value;
    @Enumerated(EnumType.STRING)
    private Month month;
    private Year year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kpi_id")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private KPI kpi;

}
