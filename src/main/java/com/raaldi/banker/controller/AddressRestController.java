package com.raaldi.banker.controller;

import com.raaldi.banker.model.Address;
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
/** Address rest controller provides access to the address repository. */
@NoArgsConstructor
@RestController
@RequestMapping("addresses")
public class AddressRestController {

  @Autowired
  ModelService<Address> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<Address>> getAll() {
    Iterable<Address> addresses = service.findAll();
    if (addresses.iterator().hasNext()) {
      return new ResponseEntity<Iterable<Address>>(addresses, HttpStatus.OK);
    }

    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<Address>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Address> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching Address with id %s", id));
    Address address = service.findOne(id);
    if (address == null) {
      log.info(String.format("Address with id %s not found", id));
      return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Address>(address, HttpStatus.OK);
  }

  @RequestMapping(value = "/address", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final Address address, final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating Address %s", address.getStreet()));

    if (service.exists(address)) {
      log.info(String.format("A Address with name %s already exist", address.getStreet()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(address);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(address.getAddressId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Address> update(@PathVariable("id") final long id, @RequestBody final Address address) {
    log.info(String.format("Updating Address %s", id));

    Address currentAddress = service.findOne(id);

    if (currentAddress == null) {
      log.info(String.format("Address with id %s not found", id));
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

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Address> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting Address with id %s", id));

    Address address = service.findOne(id);
    if (address == null) {
      log.info(String.format("Unable to delete. Address with id %s not found", id));
      return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deleteAddressById(id);
    return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<Address> deleteAll() {
    log.info("Deleting All Addresses");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllAddresses();
    return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
  }
}
