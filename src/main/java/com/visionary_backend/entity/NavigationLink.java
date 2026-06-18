package com.visionary_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "navigation_links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NavigationLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String section;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String url;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
