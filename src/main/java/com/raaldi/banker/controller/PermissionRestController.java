package com.raaldi.banker.controller;

import com.raaldi.banker.model.Permission;
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
@RequestMapping(value = "permission")
public final class PermissionRestController {

    static final Logger LOG = LoggerFactory.getLogger(PermissionRestController.class);

    @Autowired
    ModelService<Permission> service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<Permission>> getAll() {
        List<Permission> permissions = service.findAll();
        if (permissions.isEmpty()) {
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<Permission>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Permission>>(permissions, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Permission> get(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching Permission with id %s", id));
        Permission permission = service.findOne(id);
        if (permission == null) {
            LOG.info(String.format("Permission with id %s not found", id));
            return new ResponseEntity<Permission>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Permission>(permission, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody final Permission permission,
            final UriComponentsBuilder uriBuilder) {
        LOG.info(String.format("Creating Permission %s", permission.toString()));

        if (service.exists(permission)) {
            LOG.info(String.format("A Permission with name %s already exist",
                    permission.toString()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.save(permission);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriBuilder.path("/get/{id}").buildAndExpand(permission.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Permission> update(@PathVariable("id") final long id,
            @RequestBody final Permission permission) {
        LOG.info(String.format("Updating Permission %s", id));

        Permission currentPermission = service.findOne(id);

        if (currentPermission == null) {
            LOG.info(String.format("Permission with id %s not found", id));
            return new ResponseEntity<Permission>(HttpStatus.NOT_FOUND);
        }

        currentPermission.setName(permission.getName());
        /**
         * TODO: Update entity model service
         */
        // userService.updatePermission(currentPermission);
        return new ResponseEntity<Permission>(currentPermission, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Permission> delete(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching & Deleting Permission with id %s", id));

        Permission permission = service.findOne(id);
        if (permission == null) {
            LOG.info(String.format("Unable to delete. Permission with id %s not found", id));
            return new ResponseEntity<Permission>(HttpStatus.NOT_FOUND);
        }
        /**
         * TODO: Addres delete method to service
         */
        // userService.deletePermissionById(id);
        return new ResponseEntity<Permission>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    public ResponseEntity<Permission> deleteAll() {
        LOG.info("Deleting All Permissions");

        /**
         * TODO: Addres delete all method to service
         */
        // userService.deleteAllPermissions();
        return new ResponseEntity<Permission>(HttpStatus.NO_CONTENT);
    }
}
