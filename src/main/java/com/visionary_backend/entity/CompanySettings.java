package com.visionary_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanySettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(name = "headquarters_address", columnDefinition = "TEXT", nullable = false)
    private String headquartersAddress;

    @Column(name = "business_days", nullable = false)
    private String businessDays;

    @Column(name = "business_hours", nullable = false)
    private String businessHours;

    @Column(name = "response_time", nullable = false)
    private String responseTime;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String tagline;
}
