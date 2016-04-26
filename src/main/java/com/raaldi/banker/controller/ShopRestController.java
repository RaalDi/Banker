package com.raaldi.banker.controller;

import com.raaldi.banker.model.Shop;
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
@RequestMapping(value = "shop")
public final class ShopRestController {

    static final Logger LOG = LoggerFactory.getLogger(ShopRestController.class);

    @Autowired
    ModelService<Shop> service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<Shop>> getAll() {
        List<Shop> shops = service.findAll();
        if (shops.isEmpty()) {
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<Shop>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Shop>>(shops, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shop> get(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching Shop with id %s", id));
        Shop shop = service.findOne(id);
        if (shop == null) {
            LOG.info(String.format("Shop with id %s not found", id));
            return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Shop>(shop, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody final Shop shop,
            final UriComponentsBuilder uriBuilder) {
        LOG.info(String.format("Creating Shop %s", shop.toString()));

        if (service.exists(shop)) {
            LOG.info(String.format("A Shop with name %s already exist", shop.toString()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.save(shop);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/get/{id}").buildAndExpand(shop.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Shop> update(@PathVariable("id") final long id,
            @RequestBody final Shop shop) {
        LOG.info(String.format("Updating Shop %s", id));

        Shop currentShop = service.findOne(id);

        if (currentShop == null) {
            LOG.info(String.format("Shop with id %s not found", id));
            return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
        }

        currentShop.setActive(shop.isActive());
        currentShop.setAddress(shop.getAddress());
        currentShop.setCompany(shop.getCompany());
        currentShop.setName(shop.getName());
        currentShop.setPlayOrders(shop.getPlayOrders());
        currentShop.setUsers(shop.getUsers());
        /**
         * TODO: Update entity model service
         */
        // userService.updateShop(currentShop);
        return new ResponseEntity<Shop>(currentShop, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Shop> delete(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching & Deleting Shop with id %s", id));

        Shop shop = service.findOne(id);
        if (shop == null) {
            LOG.info(String.format("Unable to delete. Shop with id %s not found", id));
            return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
        }
        /**
         * TODO: Addres delete method to service
         */
        // userService.deleteShopById(id);
        return new ResponseEntity<Shop>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    public ResponseEntity<Shop> deleteAll() {
        LOG.info("Deleting All Shops");

        /**
         * TODO: Addres delete all method to service
         */
        // userService.deleteAllShops();
        return new ResponseEntity<Shop>(HttpStatus.NO_CONTENT);
    }
}
