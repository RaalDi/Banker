package com.raaldi.banker.controller;

import com.raaldi.banker.model.CashRegister;
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
@RequestMapping(value = "cashregister")
public final class CashRegisterRestController {

    static final Logger LOG = LoggerFactory.getLogger(CashRegisterRestController.class);

    @Autowired
    ModelService<CashRegister> service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<CashRegister>> getAll() {
        List<CashRegister> cashRegisteres = service.findAll();
        if (cashRegisteres.isEmpty()) {
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<CashRegister>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CashRegister>>(cashRegisteres, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CashRegister> get(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching CashRegister with id %s", id));
        CashRegister cashRegister = service.findOne(id);
        if (cashRegister == null) {
            LOG.info(String.format("CashRegister with id %s not found", id));
            return new ResponseEntity<CashRegister>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CashRegister>(cashRegister, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody final CashRegister cashRegister,
            final UriComponentsBuilder uriBuilder) {
        LOG.info(String.format("Creating CashRegister %s", cashRegister.toString()));

        if (service.exists(cashRegister)) {
            LOG.info(String.format("A CashRegister with name %s already exist",
                    cashRegister.toString()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.save(cashRegister);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriBuilder.path("/get/{id}").buildAndExpand(cashRegister.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CashRegister> update(@PathVariable("id") final long id,
            @RequestBody final CashRegister cashRegister) {
        LOG.info(String.format("Updating CashRegister %s", id));

        CashRegister currentCashRegister = service.findOne(id);

        if (currentCashRegister == null) {
            LOG.info(String.format("CashRegister with id %s not found", id));
            return new ResponseEntity<CashRegister>(HttpStatus.NOT_FOUND);
        }

        currentCashRegister.setClosed(cashRegister.getClosed());
        currentCashRegister.setClosedAmount(cashRegister.getClosedAmount());
        currentCashRegister.setOpened(cashRegister.getOpened());
        currentCashRegister.setOpenedAmount(cashRegister.getOpenedAmount());
        currentCashRegister.setSession(cashRegister.getSession());
        currentCashRegister.setState(cashRegister.getState());
        /**
         * TODO: Update entity model service
         */
        // userService.updateCashRegister(currentCashRegister);
        return new ResponseEntity<CashRegister>(currentCashRegister, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CashRegister> delete(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching & Deleting CashRegister with id %s", id));

        CashRegister cashRegister = service.findOne(id);
        if (cashRegister == null) {
            LOG.info(String.format("Unable to delete. CashRegister with id %s not found", id));
            return new ResponseEntity<CashRegister>(HttpStatus.NOT_FOUND);
        }
        /**
         * TODO: Addres delete method to service
         */
        // userService.deleteCashRegisterById(id);
        return new ResponseEntity<CashRegister>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    public ResponseEntity<CashRegister> deleteAll() {
        LOG.info("Deleting All CashRegisters");

        /**
         * TODO: Addres delete all method to service
         */
        // userService.deleteAllCashRegisters();
        return new ResponseEntity<CashRegister>(HttpStatus.NO_CONTENT);
    }
}
