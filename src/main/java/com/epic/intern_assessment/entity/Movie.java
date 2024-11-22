package com.epic.intern_assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Movies")
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imdb;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    private double rating;
    private String category;
    private int year;
    private String imageUrl;

}
