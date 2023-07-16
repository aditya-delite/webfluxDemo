package com.aditya.service;

import com.aditya.model.Tutorial;
import com.aditya.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    public Flux<Tutorial> findAll() {
        return tutorialRepository.findAll();
    }

    public Mono<Tutorial> findById(int id) {
        return tutorialRepository.findById(id);
    }

    public Flux<Tutorial> findByTitleContaining(String title) {
        return tutorialRepository.findByTitleContaining(title);
    }

    public Mono<Tutorial> save(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public Mono<Tutorial> update(int id, Tutorial tutorial) {
        return tutorialRepository.findById(id)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(optionalTutorial -> {
                    if (optionalTutorial.isPresent()) {
                        tutorial.setId(id);
                        return tutorialRepository.save(tutorial);
                    }
                    return Mono.empty();
                });
    }

    public Mono<Void> deleteById(int id){
        return tutorialRepository.deleteById(id);
    }

    public Flux<Tutorial> findByPublished(boolean isPublished) {
        return tutorialRepository.findByPublished(isPublished);
    }
}
