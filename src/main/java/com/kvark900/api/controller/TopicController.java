package com.kvark900.api.controller;

import com.kvark900.api.exceptions.BindingErrorsResponse;
import com.kvark900.api.model.Topic;
import com.kvark900.api.service.TopicService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping(value = "/topics")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }


    @GetMapping("")
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> allTopics = topicService.findAll();
        if (allTopics == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (allTopics.isEmpty())
            return new ResponseEntity<>(allTopics, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(allTopics, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable Long id) {
        return topicService
                .findById(id)
                .map(topic -> new ResponseEntity<>(topic, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Topic> addTopic(@RequestBody @Valid Topic topic, BindingResult bindingResult,
                                          UriComponentsBuilder uriComponentsBuilder) {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (topic == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        topicService.save(topic);
        headers.setLocation(uriComponentsBuilder.path("/books/{id}").buildAndExpand(topic.getId()).toUri());
        return new ResponseEntity<>(topic, headers, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable Long id) {
        Optional<Topic> topicToDelete = topicService.findById(id);
        if (!topicToDelete.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        topicService.delete(id);
        return new ResponseEntity<>(topicToDelete.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody @Valid Topic topic,
                                             BindingResult bindingResult) {
        Optional<Topic> currentTopic = topicService.findById(id);
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (topic == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        if (!currentTopic.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        topicService.update(topic);
        return new ResponseEntity<>(topic, HttpStatus.NO_CONTENT);
    }
}
