package com.raaldi.banker.controller;

import com.raaldi.banker.model.User;
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
@RequestMapping(value = "user")
public final class UserRestController {

    static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    ModelService<User> service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        List<User> users = service.findAll();
        if (users.isEmpty()) {
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> get(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching User with id %s", id));
        User user = service.findOne(id);
        if (user == null) {
            LOG.info(String.format("User with id %s not found", id));
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody final User user,
            final UriComponentsBuilder uriBuilder) {
        LOG.info(String.format("Creating User %s", user.getUserName()));

        if (service.exists(user)) {
            LOG.info(String.format("A User with name %s already exist", user.getUserName()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/get/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable("id") final long id,
            @RequestBody final User user) {
        LOG.info(String.format("Updating User %s", id));

        User currentUser = service.findOne(id);

        if (currentUser == null) {
            LOG.info(String.format("User with id %s not found", id));
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currentUser.setFirstName(user.getFirstName());
        currentUser.setAddress(user.getAddress());
        currentUser.setLastName(user.getLastName());
        /**
         * TODO: Update entity model service
         */
        // userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching & Deleting User with id %s", id));

        User user = service.findOne(id);
        if (user == null) {
            LOG.info(String.format("Unable to delete. User with id %s not found", id));
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        /**
         * TODO: Addres delete method to service
         */
        // userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAll() {
        LOG.info("Deleting All Users");

        /**
         * TODO: Addres delete all method to service
         */
        // userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET)
    public String greeting(@PathVariable("name") final String name) {
        return String.format("Name is %s", name);
    }

}
