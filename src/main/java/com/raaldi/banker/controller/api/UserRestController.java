package com.raaldi.banker.controller.api;

import com.raaldi.banker.model.User;
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
@RequestMapping(value = "users")
public class UserRestController {

  @Autowired
  ModelService<User> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<User>> getAll() {
    Iterable<User> users = service.findAll();
    if (users.iterator().hasNext()) {
      return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<User>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching User with id %s", id));
    User user = service.findOne(id);
    if (user == null) {
      log.info(String.format("User with id %s not found", id));
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final User user, final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating User %s", user.getUsername()));

    if (service.exists(user)) {
      log.info(String.format("A User with name %s already exist", user.getUsername()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(user);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(user.getId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<User> update(@PathVariable("id") final long id, @RequestBody final User user) {
    log.info(String.format("Updating User %s", id));

    User currentUser = service.findOne(id);

    if (currentUser == null) {
      log.info(String.format("User with id %s not found", id));
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

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<User> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting User with id %s", id));

    User user = service.findOne(id);
    if (user == null) {
      log.info(String.format("Unable to delete. User with id %s not found", id));
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deleteUserById(id);
    return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<User> deleteAll() {
    log.info("Deleting All Users");

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
