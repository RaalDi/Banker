package com.raaldi.banker.controller;

import com.raaldi.banker.model.Address;
import com.raaldi.banker.model.User;
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
import java.util.concurrent.atomic.AtomicLong;

@RestController
public final class UserRestController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    ModelService<User> service;

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = service.findAll();
        if (users.isEmpty()) {
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") final long id) {
        System.out.println("Fetching User with id " + id);
        User user = service.findOne(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody final User user,
            final UriComponentsBuilder uriBuilder) {
        System.out.println("Creating User " + user.getUserName());

        if (service.exists(user)) {
            System.out.println("A User with name " + user.getUserName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") final long id,
            @RequestBody final User user) {
        System.out.println("Updating User " + id);

        User currentUser = service.findOne(id);

        if (currentUser == null) {
            System.out.println("User with id " + id + " not found");
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

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") final long id) {
        System.out.println("Fetching & Deleting User with id " + id);

        User user = service.findOne(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        /**
         * TODO: Addres delete method to service
         */
        // userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("Deleting All Users");

        /**
         * TODO: Addres delete all method to service
         */
        // userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET)
    public String greeting(@PathVariable("name") final String name) {
        User user = new User();
        user.setFirstName("Rafael");
        user.setLastName("Diaz");
        user.setPassword("hello");
        user.setPhoneNumber("7275155317");
        user.setUserName("rafael.diaz");
        user.setCreatedUid(1L);

        Address address = new Address();
        address.setStreet("15350 Amberly Dr 1014");
        address.setCity("Tampa");
        address.setState("Florida");
        address.setZipcode("33647");
        address.setCreatedUid(1L);

        user.setAddress(address);
        service.save(user);

        return String.format(TEMPLATE, name);
    }

}
