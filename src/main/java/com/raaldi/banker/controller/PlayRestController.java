package com.raaldi.banker.controller;

import com.raaldi.banker.model.Play;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
/** Address service provides access to the address repository. */
@NoArgsConstructor
@RestController
@RequestMapping(value = "plays")
public class PlayRestController {

  @Autowired
  ModelService<Play> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<Play>> getAll() {
    Iterable<Play> plays = service.findAll();
    if (plays.iterator().hasNext()) {
      return new ResponseEntity<Iterable<Play>>(plays, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<Play>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Play> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching Play with id %s", id));
    Play play = service.findOne(id);
    if (play == null) {
      log.info(String.format("Play with id %s not found", id));
      return new ResponseEntity<Play>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Play>(play, HttpStatus.OK);
  }

  @RequestMapping(value = "/play", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final Play play, final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating Play %s", play.toString()));

    if (service.exists(play)) {
      log.info(String.format("A Play with name %s already exist", play.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(play);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(play.getPlayId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Play> update(@PathVariable("id") final long id, @RequestBody final Play play) {
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

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
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

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<Play> deleteAll() {
    log.info("Deleting All Plays");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllPlays();
    return new ResponseEntity<Play>(HttpStatus.NO_CONTENT);
  }
}
