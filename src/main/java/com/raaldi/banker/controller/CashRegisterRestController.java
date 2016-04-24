package com.raaldi.banker.controller;

import com.raaldi.banker.model.CashRegister;
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

@RestController
public final class CashRegisterRestController {

    @Autowired
    ModelService<CashRegister> service;

    @RequestMapping(value = "/cashregister/", method = RequestMethod.GET)
    public ResponseEntity<List<CashRegister>> listAllCashRegisters() {
        List<CashRegister> cashRegisteres = service.findAll();
        if (cashRegisteres.isEmpty()) {
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<CashRegister>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CashRegister>>(cashRegisteres, HttpStatus.OK);
    }

    @RequestMapping(value = "/cashregister/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CashRegister> getCashRegister(@PathVariable("id") final long id) {
        System.out.println("Fetching CashRegister with id " + id);
        CashRegister cashRegister = service.findOne(id);
        if (cashRegister == null) {
            System.out.println("CashRegister with id " + id + " not found");
            return new ResponseEntity<CashRegister>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CashRegister>(cashRegister, HttpStatus.OK);
    }

    @RequestMapping(value = "/cashregister/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCashRegister(@RequestBody final CashRegister cashRegister,
            final UriComponentsBuilder uriBuilder) {
        System.out.println("Creating CashRegister " + cashRegister.toString());

        if (service.exists(cashRegister)) {
            System.out.println(
                    "A CashRegister with name " + cashRegister.toString() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.save(cashRegister);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriBuilder.path("/cashregister/{id}").buildAndExpand(cashRegister.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cashregister/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CashRegister> updateCashRegister(@PathVariable("id") final long id,
            @RequestBody final CashRegister cashRegister) {
        System.out.println("Updating CashRegister " + id);

        CashRegister currentCashRegister = service.findOne(id);

        if (currentCashRegister == null) {
            System.out.println("CashRegister with id " + id + " not found");
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

    @RequestMapping(value = "/cashregister/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CashRegister> deleteCashRegister(@PathVariable("id") final long id) {
        System.out.println("Fetching & Deleting CashRegister with id " + id);

        CashRegister cashRegister = service.findOne(id);
        if (cashRegister == null) {
            System.out.println("Unable to delete. CashRegister with id " + id + " not found");
            return new ResponseEntity<CashRegister>(HttpStatus.NOT_FOUND);
        }
        /**
         * TODO: Addres delete method to service
         */
        // userService.deleteCashRegisterById(id);
        return new ResponseEntity<CashRegister>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/cashregister/", method = RequestMethod.DELETE)
    public ResponseEntity<CashRegister> deleteAllCashRegisters() {
        System.out.println("Deleting All CashRegisters");

        /**
         * TODO: Addres delete all method to service
         */
        // userService.deleteAllCashRegisters();
        return new ResponseEntity<CashRegister>(HttpStatus.NO_CONTENT);
    }
}
