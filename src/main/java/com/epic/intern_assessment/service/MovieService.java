package com.epic.intern_assessment.service;

import com.epic.intern_assessment.dto.ApiResponseDTO;
import com.epic.intern_assessment.dto.MovieDTO;
import com.epic.intern_assessment.dto.MovieResponseDTO;

public interface MovieService {

    MovieResponseDTO getAllMovies();

    ApiResponseDTO addMovie(MovieDTO movieDTO);

    ApiResponseDTO updateMovie(MovieDTO movieDTO);

    ApiResponseDTO getMovieByImdb(String imdb);

    ApiResponseDTO deleteMovie(String imdb);
}
