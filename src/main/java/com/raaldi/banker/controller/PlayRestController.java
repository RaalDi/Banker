package com.raaldi.banker.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.raaldi.banker.model.Play;
import com.raaldi.banker.service.ModelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@NoArgsConstructor
@RestController
@RequestMapping(value = "play")
public final class PlayRestController {

    @Autowired
    ModelService<Play> service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<Play>> getAll() {
        List<Play> plaies = service.findAll();
        if (plaies.isEmpty()) {
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<Play>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Play>>(plaies, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Play> get(@PathVariable("id") final long id) {
        log.info(String.format("Fetching Play with id %s", id));
        Play play = service.findOne(id);
        if (play == null) {
            log.info(String.format("Play with id %s not found", id));
            return new ResponseEntity<Play>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Play>(play, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody final Play play,
            final UriComponentsBuilder uriBuilder) {
        log.info(String.format("Creating Play %s", play.toString()));

        if (service.exists(play)) {
            log.info(String.format("A Play with name %s already exist", play.toString()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.save(play);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/get/{id}").buildAndExpand(play.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Play> update(@PathVariable("id") final long id,
            @RequestBody final Play play) {
        log.info(String.format("Updating Play %s", id));

        Play currentPlay = service.findOne(id);

        if (currentPlay == null) {
            log.info(String.format("Play with id %s not found", id));
            return new ResponseEntity<Play>(HttpStatus.NOT_FOUND);
        }

        currentPlay.setActive(play.isActive());
        currentPlay.setName(play.getName());
        currentPlay.setShortName(play.getShortName());
        /**
         * TODO: Update entity model service
         */
        // userService.updatePlay(currentPlay);
        return new ResponseEntity<Play>(currentPlay, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Play> delete(@PathVariable("id") final long id) {
        log.info(String.format("Fetching & Deleting Play with id %s", id));

        Play play = service.findOne(id);
        if (play == null) {
            log.info(String.format("Unable to delete. Play with id %s not found", id));
            return new ResponseEntity<Play>(HttpStatus.NOT_FOUND);
        }
        /**
         * TODO: Addres delete method to service
         */
        // userService.deletePlayById(id);
        return new ResponseEntity<Play>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    public ResponseEntity<Play> deleteAll() {
        log.info("Deleting All Plays");

        /**
         * TODO: Addres delete all method to service
         */
        // userService.deleteAllPlays();
        return new ResponseEntity<Play>(HttpStatus.NO_CONTENT);
    }
}
