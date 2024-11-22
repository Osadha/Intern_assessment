package com.epic.intern_assessment.repo;

import com.epic.intern_assessment.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByImdb(String imdb);
    void deleteByImdb(String imdb);
}
