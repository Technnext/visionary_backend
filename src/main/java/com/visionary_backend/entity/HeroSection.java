package com.visionary_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hero_sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "page_key", nullable = false, unique = true)
    private String pageKey;

    @Column
    private String eyebrow;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String subtitle;

    @Column(name = "primary_button_text")
    private String primaryButtonText;

    @Column(name = "primary_button_url")
    private String primaryButtonUrl;

    @Column(name = "secondary_button_text")
    private String secondaryButtonText;

    @Column(name = "secondary_button_url")
    private String secondaryButtonUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "background_image_url")
    private String backgroundImageUrl;

    @Column(name = "badge_text")
    private String badgeText;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
