package com.epic.intern_assessment.service.implementation;

import com.epic.intern_assessment.dto.ApiResponseDTO;
import com.epic.intern_assessment.dto.MovieDTO;
import com.epic.intern_assessment.dto.MovieResponseDTO;
import com.epic.intern_assessment.entity.Movie;
import com.epic.intern_assessment.repo.MovieRepository;
import com.epic.intern_assessment.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieResponseDTO getAllMovies() {
        log.info("Attempting to get all movies");
        try {
            List<Movie> movieResponseDTO =  movieRepository.findAll();

            if(movieResponseDTO.isEmpty()){
                log.info("No any movies found in here");
                return new MovieResponseDTO("02", "No Movies Found", null);
            }
            List<MovieDTO> movieDTOList = movieResponseDTO.stream()
                    .map(movie -> MovieDTO.builder()
                            .imdb(movie.getImdb())
                            .title(movie.getTitle())
                            .description(movie.getDescription())
                            .rating(movie.getRating())
                            .category(movie.getCategory())
                            .year(movie.getYear())
                            .imageUrl(movie.getImageUrl())
                            .build())
                    .collect(Collectors.toList());

            log.info("Fetch all movies : {}", movieDTOList);
            return new MovieResponseDTO("00", "Success",movieDTOList);
        }catch (Exception e){
            log.error("Exception occur in fetching all movies: {}", e.getMessage());
            e.printStackTrace();
            return new MovieResponseDTO("06", "Bad Request",null);
        }
    }

    public ApiResponseDTO addMovie(MovieDTO movieDTO) {
        log.info("Attempting to add one movie : {}", movieDTO);
        try{
            Movie movieResult = movieRepository.findByImdb(movieDTO.getImdb()).orElse(null);
            if (movieResult != null) {
                log.warn("Movie already exists : {}", movieDTO.getImdb());
                return new ApiResponseDTO("04", "Movie Already Exists", null);
            }
            Movie movie = new Movie();
            movie.setImdb(movieDTO.getImdb());
            movie.setTitle(movieDTO.getTitle());
            movie.setDescription(movieDTO.getDescription());
            movie.setRating(movieDTO.getRating());
            movie.setCategory(movieDTO.getCategory());
            movie.setYear(movieDTO.getYear());
            movie.setImageUrl(movieDTO.getImageUrl());
            movieRepository.save(movie);
            log.info("Movie added : {}", movieDTO.getImdb());
            return new ApiResponseDTO("00", "Success", null);
        }catch (Exception e){
            log.error("Exception occur in adding movies: {}", e.getMessage());
            e.printStackTrace();
            return new ApiResponseDTO("06", "Bad Request", null);
        }
    }

    public ApiResponseDTO updateMovie(MovieDTO movieDTO) {
        log.info("Attempting to update one movie : {}", movieDTO);
        try {
            Movie movieResult = movieRepository.findByImdb(movieDTO.getImdb()).orElse(null);
            if (movieResult == null) {
                log.warn("Movie not found : {}", movieDTO.getImdb());
                return new ApiResponseDTO("02", "No Such Movie Exists", null);
            }
            movieResult.setTitle(movieDTO.getTitle());
            movieResult.setDescription(movieDTO.getDescription());
            movieResult.setRating(movieDTO.getRating());
            movieResult.setCategory(movieDTO.getCategory());
            movieResult.setYear(movieDTO.getYear());
            movieResult.setImageUrl(movieDTO.getImageUrl());
            movieRepository.save(movieResult);
            log.info("Movie updated : {}", movieDTO.getImdb());
            return new ApiResponseDTO("00", "Success", null);
        }catch (Exception e){
            log.error("Exception occur in updating movies: {}", e.getMessage());
            e.printStackTrace();
            return new ApiResponseDTO("06", "Bad Request", null);
        }
    }

    public ApiResponseDTO getMovieByImdb(String imdb) {
        log.info("Attempting to get movie by imdb : {}", imdb);
        try{
            Movie movieResult = movieRepository.findByImdb(imdb).orElse(null);
            if (movieResult == null) {
                log.warn("Movie not found : {}", imdb);
                return new ApiResponseDTO("02", "No Such Movie Found", null);
            }
            log.info("Movie found : {}", movieResult.getImdb());
            return new ApiResponseDTO("00", "Success", movieResult);
        }catch (Exception e){
            log.error("Exception occur in retrieving movies: {}", e.getMessage());
            e.printStackTrace();
            return new ApiResponseDTO("06", "Bad Request", null);
        }

    }

    @Transactional
    public ApiResponseDTO deleteMovie(String imdb) {
        log.info("Attempting to delete one movie : {}", imdb);
        try {
            Movie movieResult = movieRepository.findByImdb(imdb).orElse(null);
            if (movieResult == null) {
                log.warn("Movie not found : {}", imdb);
                return new ApiResponseDTO("02", "No Such Movie Found", null);
            }
            movieRepository.deleteByImdb(imdb);
            log.info("Movie deleted : {}", imdb);
            return new ApiResponseDTO("00", "Success", null);
        }catch (Exception e){
            log.error("Exception occur in deleting movies: {}", e.getMessage());
            e.printStackTrace();
            return new ApiResponseDTO("06", "Bad Request", null);
        }

    }
}
