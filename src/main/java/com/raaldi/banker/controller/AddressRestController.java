package com.raaldi.banker.controller;

import java.util.List;

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

import com.raaldi.banker.model.Address;
import com.raaldi.banker.service.ModelService;

@RestController
public class AddressRestController {

	@Autowired
	ModelService<Address> service;

	@RequestMapping(value = "/address/", method = RequestMethod.GET)
	public ResponseEntity<List<Address>> listAllAddresses() {
		List<Address> addresses = service.findAll();
		if (addresses.isEmpty()) {
			// You many decide to return HttpStatus.NOT_FOUND
			return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> getAddress(@PathVariable("id") long id) {
		System.out.println("Fetching Address with id " + id);
		Address address = service.findOne(id);
		if (address == null) {
			System.out.println("Address with id " + id + " not found");
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}

	@RequestMapping(value = "/address/", method = RequestMethod.POST)
	public ResponseEntity<Void> createAddress(@RequestBody Address address, UriComponentsBuilder uriBuilder) {
		System.out.println("Creating Address " + address.getStreet());

		if (service.exists(address)) {
			System.out.println("A Address with name " + address.getStreet() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		service.save(address);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/address/{id}").buildAndExpand(address.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Address> updateAddress(@PathVariable("id") long id, @RequestBody Address address) {
		System.out.println("Updating Address " + id);

		Address currentAddress = service.findOne(id);

		if (currentAddress == null) {
			System.out.println("Address with id " + id + " not found");
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}

		currentAddress.setStreet(address.getStreet());
		currentAddress.setCity(address.getCity());
		currentAddress.setState(address.getState());
		currentAddress.setZipcode(address.getZipcode());
		/**
		 * TODO: Update entity model service
		 */
		// userService.updateAddress(currentAddress);
		return new ResponseEntity<Address>(currentAddress, HttpStatus.OK);
	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Address> deleteAddress(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Address with id " + id);

		Address address = service.findOne(id);
		if (address == null) {
			System.out.println("Unable to delete. Address with id " + id + " not found");
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}
		/**
		 * TODO: Addres delete method to service
		 */
		// userService.deleteAddressById(id);
		return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/address/", method = RequestMethod.DELETE)
	public ResponseEntity<Address> deleteAllAddresses() {
		System.out.println("Deleting All Addresses");

		/**
		 * TODO: Addres delete all method to service
		 */
		// userService.deleteAllAddresses();
		return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
	}
}
