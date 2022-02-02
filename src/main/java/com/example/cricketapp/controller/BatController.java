package com.example.cricketapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cricketapp.data.entity.Bat;
import com.example.cricketapp.service.BatService;

@RestController
@RequestMapping(path = "/bat")
public class BatController {

	@Autowired
	private BatService service;
	
	public BatController(BatService service) {
		this.service = service;
	}
	
	// Create
	@PostMapping("/create")
	public ResponseEntity<Bat> createBat(@RequestBody Bat bat) {
		Bat savedBat = service.create(bat);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/bat/" + String.valueOf(savedBat.getId()));
		
		ResponseEntity<Bat> response = new ResponseEntity<Bat>(savedBat, headers, HttpStatus.CREATED);
		return response;
	}
	
	// Update
	@PutMapping("/update/{id}")
	public ResponseEntity<Bat> updateBat(@PathVariable("id") long id, @Valid @RequestBody Bat bat) {
		Bat updatedBat = service.update(id, bat);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/bat/" + String.valueOf(updatedBat.getId()));
		return new ResponseEntity<Bat>(updatedBat, headers, HttpStatus.ACCEPTED);
	}
	
	// Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		service.delete(id);
		return ResponseEntity.accepted().build();
	}
	
	// Read All
	@GetMapping("/readAll")
	public ResponseEntity<List<Bat>> getBats() {
		ResponseEntity<List<Bat>> bats = ResponseEntity.ok(service.getAll());
		return bats;
	}
	
	//Read By ID
	@RequestMapping(path = "/{id}", method = { RequestMethod.GET })
	public ResponseEntity<Bat> getBatById(@PathVariable("id")long id) {
		Bat savedBat = service.getById(id);
		ResponseEntity<Bat> response = ResponseEntity.status(HttpStatus.OK).body(savedBat);
		return response;
	}
	
}
