package com.raaldi.banker.controller;

import com.raaldi.banker.model.Lottery;
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
@RequestMapping(value = "lottery")
public final class LotteryRestController {

    static final Logger LOG = LoggerFactory.getLogger(LotteryRestController.class);

    @Autowired
    ModelService<Lottery> service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<Lottery>> getAll() {
        List<Lottery> lotteries = service.findAll();
        if (lotteries.isEmpty()) {
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<Lottery>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Lottery>>(lotteries, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lottery> get(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching Lottery with id %s", id));
        Lottery lottery = service.findOne(id);
        if (lottery == null) {
            LOG.info(String.format("Lottery with id %s not found", id));
            return new ResponseEntity<Lottery>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Lottery>(lottery, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody final Lottery lottery,
            final UriComponentsBuilder uriBuilder) {
        LOG.info(String.format("Creating Lottery %s", lottery.toString()));

        if (service.exists(lottery)) {
            LOG.info(String.format("A Lottery with name %s already exist", lottery.toString()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.save(lottery);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/get/{id}").buildAndExpand(lottery.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Lottery> update(@PathVariable("id") final long id,
            @RequestBody final Lottery lottery) {
        LOG.info(String.format("Updating Lottery %s", id));

        Lottery currentLottery = service.findOne(id);

        if (currentLottery == null) {
            LOG.info(String.format("Lottery with id %s not found", id));
            return new ResponseEntity<Lottery>(HttpStatus.NOT_FOUND);
        }

        currentLottery.setActive(lottery.isActive());
        currentLottery.setName(lottery.getName());
        currentLottery.setShortName(lottery.getShortName());
        /**
         * TODO: Update entity model service
         */
        // userService.updateLottery(currentLottery);
        return new ResponseEntity<Lottery>(currentLottery, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Lottery> delete(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching & Deleting Lottery with id %s", id));

        Lottery lottery = service.findOne(id);
        if (lottery == null) {
            LOG.info(String.format("Unable to delete. Lottery with id %s not found", id));
            return new ResponseEntity<Lottery>(HttpStatus.NOT_FOUND);
        }
        /**
         * TODO: Addres delete method to service
         */
        // userService.deleteLotteryById(id);
        return new ResponseEntity<Lottery>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    public ResponseEntity<Lottery> deleteAll() {
        LOG.info("Deleting All Lotterys");

        /**
         * TODO: Addres delete all method to service
         */
        // userService.deleteAllLotterys();
        return new ResponseEntity<Lottery>(HttpStatus.NO_CONTENT);
    }
}
