package com.raaldi.banker.controller;

import com.raaldi.banker.model.Address;
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
@RequestMapping("address")
public final class AddressRestController {

    static final Logger LOG = LoggerFactory.getLogger(AddressRestController.class);

    @Autowired
    ModelService<Address> service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<Address>> getAll() {
        List<Address> addresses = service.findAll();
        if (addresses.isEmpty()) {
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> get(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching Address with id %s", id));
        Address address = service.findOne(id);
        if (address == null) {
            LOG.info(String.format("Address with id %s not found", id));
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody final Address address,
            final UriComponentsBuilder uriBuilder) {
        LOG.info(String.format("Creating Address %s", address.getStreet()));

        if (service.exists(address)) {
            LOG.info(String.format("A Address with name %s already exist", address.getStreet()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.save(address);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/get/{id}").buildAndExpand(address.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Address> update(@PathVariable("id") final long id,
            @RequestBody final Address address) {
        LOG.info(String.format("Updating Address %s", id));

        Address currentAddress = service.findOne(id);

        if (currentAddress == null) {
            LOG.info(String.format("Address with id %s not found", id));
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }

        currentAddress.setStreet(address.getStreet());
        currentAddress.setCity(address.getCity());
        currentAddress.setState(address.getState());
        currentAddress.setZipcode(address.getZipcode());
        /**
         * TODO: Update entity model service
         */
        // userService.updateAddress(currentAddress);
        return new ResponseEntity<Address>(currentAddress, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Address> delete(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching & Deleting Address with id %s", id));

        Address address = service.findOne(id);
        if (address == null) {
            LOG.info(String.format("Unable to delete. Address with id %s not found", id));
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        /**
         * TODO: Addres delete method to service
         */
        // userService.deleteAddressById(id);
        return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    public ResponseEntity<Address> deleteAll() {
        LOG.info("Deleting All Addresses");

        /**
         * TODO: Addres delete all method to service
         */
        // userService.deleteAllAddresses();
        return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
    }
}
