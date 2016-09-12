package com.raaldi.banker.controller.api;

import com.raaldi.banker.model.RolePermission;
import com.raaldi.banker.service.ModelService;

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

import java.util.List;

@Slf4j
/** Address service provides access to the address repository. */
@NoArgsConstructor
@RestController
@RequestMapping(value = "role-permissions")
public final class RolePermissionRestController {

  @Autowired
  ModelService<RolePermission> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<RolePermission>> getAll() {
    List<RolePermission> rolePermissions = service.findAll();
    if (rolePermissions.isEmpty()) {
      // You many decide to return HttpStatus.NOT_FOUND
      return new ResponseEntity<List<RolePermission>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<RolePermission>>(rolePermissions, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RolePermission> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching RolePermission with id %s", id));
    RolePermission rolePermission = service.findOne(id);
    if (rolePermission == null) {
      log.info(String.format("RolePermission with id %s not found", id));
      return new ResponseEntity<RolePermission>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<RolePermission>(rolePermission, HttpStatus.OK);
  }

  @RequestMapping(value = "/role-permission", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final RolePermission rolePermission,
      final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating RolePermission %s", rolePermission.toString()));

    if (service.exists(rolePermission)) {
      log.info(
          String.format("A RolePermission with name %s already exist", rolePermission.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(rolePermission);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(rolePermission.getId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<RolePermission> update(@PathVariable("id") final long id,
      @RequestBody final RolePermission rolePermission) {
    log.info(String.format("Updating RolePermission %s", id));

    RolePermission currentRolePermission = service.findOne(id);

    if (currentRolePermission == null) {
      log.info(String.format("RolePermission with id %s not found", id));
      return new ResponseEntity<RolePermission>(HttpStatus.NOT_FOUND);
    }

    currentRolePermission.setPermission(rolePermission.getPermission());
    currentRolePermission.setRole(rolePermission.getRole());
    currentRolePermission.setUser(rolePermission.getUser());
    /**
     * TODO: Update entity model service
     */
    // userService.updateRolePermission(currentRolePermission);
    return new ResponseEntity<RolePermission>(currentRolePermission, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<RolePermission> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting RolePermission with id %s", id));

    RolePermission rolePermission = service.findOne(id);
    if (rolePermission == null) {
      log.info(String.format("Unable to delete. RolePermission with id %s not found", id));
      return new ResponseEntity<RolePermission>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deleteRolePermissionById(id);
    return new ResponseEntity<RolePermission>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<RolePermission> deleteAll() {
    log.info("Deleting All RolePermissions");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllRolePermissions();
    return new ResponseEntity<RolePermission>(HttpStatus.NO_CONTENT);
  }
}
