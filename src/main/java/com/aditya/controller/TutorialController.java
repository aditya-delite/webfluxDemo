package com.aditya.controller;

import com.aditya.model.Tutorial;
import com.aditya.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @GetMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Tutorial> getTutorialById(@PathVariable("id") int id){
        return this.tutorialService.findById(id);
    }

    @GetMapping("/tutorials")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> getAllTutorials(@RequestParam(required = false) String title) {
        if (title == null)
            return tutorialService.findAll().delayElements(Duration.ofMillis(2000));
        else
            return tutorialService.findByTitleContaining(title);
    }

    @PostMapping("/tutorials")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        return tutorialService.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
    }

    @PutMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Tutorial> updateTutorial(@PathVariable("id") int id, @RequestBody Tutorial tutorial) {
        return tutorialService.update(id, tutorial);
    }

    @DeleteMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTutorial(@PathVariable("id") int id) {
        return tutorialService.deleteById(id);
    }
}
