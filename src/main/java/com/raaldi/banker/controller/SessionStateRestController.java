package com.raaldi.banker.controller;

import com.raaldi.banker.model.SessionState;
import com.raaldi.banker.service.ModelService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestController
@RequestMapping(value = "session-state")
public final class SessionStateRestController {

    static final Logger LOG = LoggerFactory.getLogger(SessionStateRestController.class);

    @Autowired
    ModelService<SessionState> service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<SessionState>> getAll() {
        List<SessionState> sessionStates = service.findAll();
        if (sessionStates.isEmpty()) {
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<SessionState>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<SessionState>>(sessionStates, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionState> get(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching SessionState with id %s", id));
        SessionState sessionState = service.findOne(id);
        if (sessionState == null) {
            LOG.info(String.format("SessionState with id %s not found", id));
            return new ResponseEntity<SessionState>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SessionState>(sessionState, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody final SessionState sessionState,
            final UriComponentsBuilder uriBuilder) {
        LOG.info(String.format("Creating SessionState %s", sessionState.toString()));

        if (service.exists(sessionState)) {
            LOG.info(String.format("A SessionState with name %s already exist",
                    sessionState.toString()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.save(sessionState);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriBuilder.path("/get/{id}").buildAndExpand(sessionState.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SessionState> update(@PathVariable("id") final long id,
            @RequestBody final SessionState sessionState) {
        LOG.info(String.format("Updating SessionState %s", id));

        SessionState currentSessionState = service.findOne(id);

        if (currentSessionState == null) {
            LOG.info(String.format("SessionState with id %s not found", id));
            return new ResponseEntity<SessionState>(HttpStatus.NOT_FOUND);
        }

        currentSessionState.setName(sessionState.getName());

        /**
         * TODO: Update entity model service
         */
        // userService.updateSessionState(currentSessionState);
        return new ResponseEntity<SessionState>(currentSessionState, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SessionState> delete(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching & Deleting SessionState with id %s", id));

        SessionState sessionState = service.findOne(id);
        if (sessionState == null) {
            LOG.info(String.format("Unable to delete. SessionState with id %s not found", id));
            return new ResponseEntity<SessionState>(HttpStatus.NOT_FOUND);
        }
        /**
         * TODO: Addres delete method to service
         */
        // userService.deleteSessionStateById(id);
        return new ResponseEntity<SessionState>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    public ResponseEntity<SessionState> deleteAll() {
        LOG.info("Deleting All SessionStates");

        /**
         * TODO: Addres delete all method to service
         */
        // userService.deleteAllSessionStates();
        return new ResponseEntity<SessionState>(HttpStatus.NO_CONTENT);
    }
}
