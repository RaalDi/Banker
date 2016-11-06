package com.raaldi.banker.controller;

import com.raaldi.banker.model.Shop;
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
@RequestMapping(value = "shops")
public class ShopRestController {

  @Autowired
  ModelService<Shop> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<Shop>> getAll() {
    Iterable<Shop> shops = service.findAll();
    if (shops.iterator().hasNext()) {
      return new ResponseEntity<Iterable<Shop>>(shops, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<Shop>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Shop> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching Shop with id %s", id));
    Shop shop = service.findOne(id);
    if (shop == null) {
      log.info(String.format("Shop with id %s not found", id));
      return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Shop>(shop, HttpStatus.OK);
  }

  @RequestMapping(value = "/shop", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final Shop shop, final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating Shop %s", shop.toString()));

    if (service.exists(shop)) {
      log.info(String.format("A Shop with name %s already exist", shop.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(shop);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(shop.getShopId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Shop> update(@PathVariable("id") final long id, @RequestBody final Shop shop) {
    log.info(String.format("Updating Shop %s", id));

    Shop currentShop = service.findOne(id);

    if (currentShop == null) {
      log.info(String.format("Shop with id %s not found", id));
      return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
    }

    currentShop.setActive(shop.isActive());
    currentShop.setAddress(shop.getAddress());
    currentShop.setCompany(shop.getCompany());
    currentShop.setName(shop.getName());
    // currentShop.setPlayOrders(shop.getPlayOrders());
    currentShop.setUsers(shop.getUsers());
    /**
     * TODO: Update entity model service
     */
    // userService.updateShop(currentShop);
    return new ResponseEntity<Shop>(currentShop, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Shop> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting Shop with id %s", id));

    Shop shop = service.findOne(id);
    if (shop == null) {
      log.info(String.format("Unable to delete. Shop with id %s not found", id));
      return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deleteShopById(id);
    return new ResponseEntity<Shop>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<Shop> deleteAll() {
    log.info("Deleting All Shops");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllShops();
    return new ResponseEntity<Shop>(HttpStatus.NO_CONTENT);
  }
}
