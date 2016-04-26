package com.raaldi.banker.controller;

import com.raaldi.banker.model.Currency;
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
@RequestMapping(value = "currency")
public final class CurrencyRestController {

    static final Logger LOG = LoggerFactory.getLogger(CurrencyRestController.class);

    @Autowired
    ModelService<Currency> service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<Currency>> getAll() {
        List<Currency> currencies = service.findAll();
        if (currencies.isEmpty()) {
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<Currency>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Currency>>(currencies, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Currency> get(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching Currency with id %s", id));
        Currency currency = service.findOne(id);
        if (currency == null) {
            LOG.info(String.format("Currency with id %s not found", id));
            return new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Currency>(currency, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody final Currency currency,
            final UriComponentsBuilder uriBuilder) {
        LOG.info(String.format("Creating Currency %s", currency.toString()));

        if (service.exists(currency)) {
            LOG.info(String.format("A Currency with name %s already exist", currency.toString()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.save(currency);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/get/{id}").buildAndExpand(currency.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Currency> update(@PathVariable("id") final long id,
            @RequestBody final Currency currency) {
        LOG.info(String.format("Updating Currency %s", id));

        Currency currentCurrency = service.findOne(id);

        if (currentCurrency == null) {
            LOG.info(String.format("Currency with id %s not found", id));
            return new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
        }

        currentCurrency.setValue(currency.getValue());

        /**
         * TODO: Update entity model service
         */
        // userService.updateCurrency(currentCurrency);
        return new ResponseEntity<Currency>(currentCurrency, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Currency> delete(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching & Deleting Currency with id %s", id));

        Currency currency = service.findOne(id);
        if (currency == null) {
            LOG.info(String.format("Unable to delete. Currency with id %s not found", id));
            return new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
        }
        /**
         * TODO: Addres delete method to service
         */
        // userService.deleteCurrencyById(id);
        return new ResponseEntity<Currency>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    public ResponseEntity<Currency> deleteAll() {
        LOG.info("Deleting All Currencys");

        /**
         * TODO: Addres delete all method to service
         */
        // userService.deleteAllCurrencys();
        return new ResponseEntity<Currency>(HttpStatus.NO_CONTENT);
    }
}
