package com.raaldi.banker.controller;

import com.raaldi.banker.model.Member;
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
@RequestMapping(value = "member")
public final class MemberRestController {

    static final Logger LOG = LoggerFactory.getLogger(MemberRestController.class);

    @Autowired
    ModelService<Member> service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<Member>> getAll() {
        List<Member> members = service.findAll();
        if (members.isEmpty()) {
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> get(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching Member with id %s", id));
        Member member = service.findOne(id);
        if (member == null) {
            LOG.info(String.format("Member with id %s not found", id));
            return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody final Member member,
            final UriComponentsBuilder uriBuilder) {
        LOG.info(String.format("Creating Member %s", member.toString()));

        if (service.exists(member)) {
            LOG.info(String.format("A Member with name %s already exist", member.toString()));
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.save(member);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/get/{id}").buildAndExpand(member.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Member> update(@PathVariable("id") final long id,
            @RequestBody final Member member) {
        LOG.info(String.format("Updating Member %s", id));

        Member currentMember = service.findOne(id);

        if (currentMember == null) {
            LOG.info(String.format("Member with id %s not found", id));
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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Member> delete(@PathVariable("id") final long id) {
        LOG.info(String.format("Fetching & Deleting Member with id %s", id));

        Member member = service.findOne(id);
        if (member == null) {
            LOG.info(String.format("Unable to delete. Member with id %s not found", id));
            return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
        }
        /**
         * TODO: Addres delete method to service
         */
        // userService.deleteMemberById(id);
        return new ResponseEntity<Member>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delete-all", method = RequestMethod.DELETE)
    public ResponseEntity<Member> deleteAll() {
        LOG.info("Deleting All Members");

        /**
         * TODO: Addres delete all method to service
         */
        // userService.deleteAllMembers();
        return new ResponseEntity<Member>(HttpStatus.NO_CONTENT);
    }
}
