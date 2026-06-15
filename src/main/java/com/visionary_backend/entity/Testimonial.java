package com.visionary_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "testimonials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String quote;

    @Column(name = "author_name", nullable = false)
    private String authorName;

    @Column(name = "author_title")
    private String authorTitle;

    @Column
    private String company;

    @Column(name = "photo_url")
    private String photoUrl;
}
