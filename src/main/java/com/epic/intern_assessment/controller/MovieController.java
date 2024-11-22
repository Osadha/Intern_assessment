package com.epic.intern_assessment.controller;

import com.epic.intern_assessment.dto.ApiResponseDTO;
import com.epic.intern_assessment.dto.MovieDTO;
import com.epic.intern_assessment.dto.MovieResponseDTO;
import com.epic.intern_assessment.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping()
    public ResponseEntity<MovieResponseDTO> getAllMovies() {
        MovieResponseDTO movieResponseDTO = movieService.getAllMovies();
        HttpStatus status = switch (movieResponseDTO.getResponseCode()) {
            case "00" -> HttpStatus.CREATED;
            case "02" -> HttpStatus.PRECONDITION_FAILED;
            case "03" -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
        return new ResponseEntity<>(movieResponseDTO, status);
    }

    @GetMapping("/{imdb}")
    public ResponseEntity<ApiResponseDTO> getMovieByImdb(@PathVariable String imdb) {
        ApiResponseDTO apiResponseDTO = movieService.getMovieByImdb(imdb);
        HttpStatus status = switch (apiResponseDTO.getResponseCode()) {
            case "00" -> HttpStatus.OK;
            case "02" -> HttpStatus.NOT_FOUND;
            case "06" -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
        return new ResponseEntity<>(apiResponseDTO, status);
    }

    @PostMapping
    public ResponseEntity<?> addMovie(@RequestBody MovieDTO movieDTO) {
        ApiResponseDTO apiResponseDTO = movieService.addMovie(movieDTO);
        HttpStatus status = switch (apiResponseDTO.getResponseCode()) {
            case "00" -> HttpStatus.CREATED;
            case "03" -> HttpStatus.BAD_REQUEST;
            case "04" -> HttpStatus.FOUND;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
        return new ResponseEntity<>(apiResponseDTO, status);
    }

    @PutMapping
    public ResponseEntity<?> updateMovie(@RequestBody MovieDTO movieDTO) {
        ApiResponseDTO apiResponseDTO = movieService.updateMovie(movieDTO);
        HttpStatus status = switch (apiResponseDTO.getResponseCode()) {
            case "00" -> HttpStatus.CREATED;
            case "02" -> HttpStatus.NOT_FOUND;
            case "03" -> HttpStatus.NOT_MODIFIED;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
        return new ResponseEntity<>(apiResponseDTO, status);
    }

    @DeleteMapping("/{imdb}")
    public ResponseEntity<?> deleteMovie(@PathVariable String imdb) {
        ApiResponseDTO apiResponseDTO = movieService.deleteMovie(imdb);
        HttpStatus status = switch (apiResponseDTO.getResponseCode()) {
            case "00" -> HttpStatus.CREATED;
            case "02" -> HttpStatus.NOT_FOUND;
            case "03" -> HttpStatus.NOT_MODIFIED;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
        return new ResponseEntity<>(apiResponseDTO, status);
    }
}