package com.raaldi.banker.controller;

import com.raaldi.banker.model.Company;
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
@RequestMapping(value = "companies")
public class CompanyRestController {

  @Autowired
  ModelService<Company> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<Company>> getAll() {
    Iterable<Company> companies = service.findAll();
    if (companies.iterator().hasNext()) {
      return new ResponseEntity<Iterable<Company>>(companies, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<Company>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Company> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching Company with id %s", id));
    Company company = service.findOne(id);
    if (company == null) {
      log.info(String.format("Company with id %s not found", id));
      return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Company>(company, HttpStatus.OK);
  }

  @RequestMapping(value = "/company", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final Company company, final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating Company %s", company.toString()));

    if (service.exists(company)) {
      log.info(String.format("A Company with name %s already exist", company.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(company);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(company.getCompanyId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Company> update(@PathVariable("id") final long id, @RequestBody final Company company) {
    log.info(String.format("Updating Company %s", id));

    Company currentCompany = service.findOne(id);

    if (currentCompany == null) {
      log.info(String.format("Company with id %s not found", id));
      return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
    }

    currentCompany.setName(company.getName());
    currentCompany.setAddress(company.getAddress());
    /**
     * TODO: Update entity model service
     */
    // userService.updateCompany(currentCompany);
    return new ResponseEntity<Company>(currentCompany, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Company> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting Company with id %s", id));

    Company company = service.findOne(id);
    if (company == null) {
      log.info(String.format("Unable to delete. Company with id %s not found", id));
      return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deleteCompanyById(id);
    return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<Company> deleteAll() {
    log.info("Deleting All Companys");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllCompanys();
    return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
  }
}
