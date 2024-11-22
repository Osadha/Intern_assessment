package com.epic.intern_assessment.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MovieDTO {
    private String imdb;
    private String title;
    private String description;
    private double rating;
    private String category;
    private int year;
    private String imageUrl;
}
