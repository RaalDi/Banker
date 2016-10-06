package com.raaldi.banker.controller.api;

import com.raaldi.banker.model.Role;
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
@RequestMapping(value = "roles")
public class RoleRestController {

  @Autowired
  ModelService<Role> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<Role>> getAll() {
    Iterable<Role> roles = service.findAll();
    if (roles.iterator().hasNext()) {
      return new ResponseEntity<Iterable<Role>>(roles, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<Role>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Role> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching Role with id %s", id));
    Role role = service.findOne(id);
    if (role == null) {
      log.info(String.format("Role with id %s not found", id));
      return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Role>(role, HttpStatus.OK);
  }

  @RequestMapping(value = "/role", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final Role role, final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating Role %s", role.toString()));

    if (service.exists(role)) {
      log.info(String.format("A Role with name %s already exist", role.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(role);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(role.getId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Role> update(@PathVariable("id") final long id, @RequestBody final Role role) {
    log.info(String.format("Updating Role %s", id));

    Role currentRole = service.findOne(id);

    if (currentRole == null) {
      log.info(String.format("Role with id %s not found", id));
      return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
    }

    currentRole.setName(role.getName());
    /**
     * TODO: Update entity model service
     */
    // userService.updateRole(currentRole);
    return new ResponseEntity<Role>(currentRole, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Role> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting Role with id %s", id));

    Role role = service.findOne(id);
    if (role == null) {
      log.info(String.format("Unable to delete. Role with id %s not found", id));
      return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deleteRoleById(id);
    return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<Role> deleteAll() {
    log.info("Deleting All Roles");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllRoles();
    return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
  }
}
