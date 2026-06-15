package com.visionary_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "awards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private Integer year;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;
}
