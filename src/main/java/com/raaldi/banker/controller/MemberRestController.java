package com.raaldi.banker.controller;

import com.raaldi.banker.model.Member;
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
@RequestMapping(value = "members")
public class MemberRestController {

  @Autowired
  ModelService<Member> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<Member>> getAll() {
    Iterable<Member> members = service.findAll();
    if (members.iterator().hasNext()) {
      return new ResponseEntity<Iterable<Member>>(members, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<Member>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Member> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching Member with id %s", id));
    Member member = service.findOne(id);
    if (member == null) {
      log.info(String.format("Member with id %s not found", id));
      return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Member>(member, HttpStatus.OK);
  }

  @RequestMapping(value = "/member", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final Member member, final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating Member %s", member.toString()));

    if (service.exists(member)) {
      log.info(String.format("A Member with name %s already exist", member.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(member);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(member.getId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Member> update(@PathVariable("id") final long id, @RequestBody final Member member) {
    log.info(String.format("Updating Member %s", id));

    Member currentMember = service.findOne(id);

    if (currentMember == null) {
      log.info(String.format("Member with id %s not found", id));
      return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
    }

    currentMember.setFullName(member.getFullName());
    currentMember.setPhoneNumber(member.getPhoneNumber());
    /**
     * TODO: Update entity model service
     */
    // userService.updateMember(currentMember);
    return new ResponseEntity<Member>(currentMember, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Member> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting Member with id %s", id));

    Member member = service.findOne(id);
    if (member == null) {
      log.info(String.format("Unable to delete. Member with id %s not found", id));
      return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deleteMemberById(id);
    return new ResponseEntity<Member>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<Member> deleteAll() {
    log.info("Deleting All Members");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllMembers();
    return new ResponseEntity<Member>(HttpStatus.NO_CONTENT);
  }
}
