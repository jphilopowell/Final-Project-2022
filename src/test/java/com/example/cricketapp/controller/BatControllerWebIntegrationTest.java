package com.example.cricketapp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.cricketapp.data.entity.Bat;
import com.example.cricketapp.service.BatService;

//@SpringBootTest
@WebMvcTest(BatController.class)
public class BatControllerWebIntegrationTest {

	@Autowired
	private BatController controller;
	
	@MockBean
	private BatService service;
	
	private List<Bat> bats;
	private Bat validBat;
	private Bat createBat;
	
	@BeforeEach
	public void init() {
		bats = new ArrayList<>();
		bats.addAll(List.of(new Bat("test1 make", "test1 model", 2.7, 2020),
							new Bat("test2 make", "test2 model", 2.8, 2021),
							new Bat("test3 make", "test3 model", 2.9, 2022)));
		validBat = new Bat("test4 make", "test4 model", 2.9, 2022);
	}
	
	// Test get all
	@Test
	public void getAllBatsTest() {
		
		ResponseEntity<List<Bat>> expected = new ResponseEntity<List<Bat>>(bats, HttpStatus.OK);
		
		when(service.getAll()).thenReturn(bats);
		
		ResponseEntity<List<Bat>> actual = controller.getBats();
		
		assertThat(expected).isEqualTo(actual);
		
		verify(service, times(1)).getAll();
		
	}
	
	// Test get by Id
	@Test
	public void getBatByIdTest() {
		
		ResponseEntity<Bat> expected = ResponseEntity.of(Optional.of(validBat));
		
		when(service.getById(1)).thenReturn(validBat);
		
		ResponseEntity<Bat> actual = controller.getBatById(1);
		
		assertEquals(expected, actual);
		
		verify(service, times(1)).getById(1);
		
	}
	
	// Test Create
	@Test
	public void createBatTest() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/bat/" + String.valueOf(validBat.getId()));
		
		ResponseEntity<Bat> expected = new ResponseEntity<Bat>(validBat, headers, HttpStatus.CREATED);
		
		when(service.create(createBat)).thenReturn(validBat);
		
		ResponseEntity<Bat> actual = controller.createBat(createBat);
		
		assertEquals(expected, actual);
		
		verify(service).create(createBat);
		
	}
	
	// Test Delete
	@Test
	public void deleteBatTest() {
		
		ResponseEntity<?> expected = ResponseEntity.accepted().build();
		
		ResponseEntity<?> actual = controller.deleteBat(1);
		
		assertEquals(expected, actual);
		
		verify(service).delete(1);
	}
	
}
