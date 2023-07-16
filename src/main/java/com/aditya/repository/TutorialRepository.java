package com.aditya.repository;

import com.aditya.model.Tutorial;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface TutorialRepository extends R2dbcRepository<Tutorial, Integer> {

    Flux<Tutorial> findByTitleContaining(String title);

    Flux<Tutorial> findByPublished(boolean isPublished);

}
