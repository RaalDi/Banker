package com.raaldi.banker.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.raaldi.banker.model.Payment;
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

@Slf4j
@NoArgsConstructor
@RestController
@RequestMapping(value = "payment")
public final class PaymentRestController {

  @Autowired
  ModelService<Payment> service;

  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity<List<Payment>> getAll() {
    List<Payment> payments = service.findAll();
    if (payments.isEmpty()) {
      // You many decide to return HttpStatus.NOT_FOUND
      return new ResponseEntity<List<Payment>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
  }

  @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Payment> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching Payment with id %s", id));
    Payment payment = service.findOne(id);
    if (payment == null) {
      log.info(String.format("Payment with id %s not found", id));
      return new ResponseEntity<Payment>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Payment>(payment, HttpStatus.OK);
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final Payment payment,
      final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating Payment %s", payment.toString()));

    if (service.exists(payment)) {
      log.info(String.format("A Payment with name %s already exist", payment.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(payment);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/get/{id}").buildAndExpand(payment.getId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Payment> update(@PathVariable("id") final long id,
      @RequestBody final Payment payment) {
    log.info(String.format("Updating Payment %s", id));

    Payment currentPayment = service.findOne(id);

    if (currentPayment == null) {
      log.info(String.format("Payment with id %s not found", id));
      return new ResponseEntity<Payment>(HttpStatus.NOT_FOUND);
    }

    currentPayment.setType(payment.getType());
    /**
     * TODO: Update entity model service
     */
    // userService.updatePayment(currentPayment);
    return new ResponseEntity<Payment>(currentPayment, HttpStatus.OK);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Payment> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting Payment with id %s", id));

    Payment payment = service.findOne(id);
    if (payment == null) {
      log.info(String.format("Unable to delete. Payment with id %s not found", id));
      return new ResponseEntity<Payment>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deletePaymentById(id);
    return new ResponseEntity<Payment>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
  public ResponseEntity<Payment> deleteAll() {
    log.info("Deleting All Payments");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllPayments();
    return new ResponseEntity<Payment>(HttpStatus.NO_CONTENT);
  }
}
