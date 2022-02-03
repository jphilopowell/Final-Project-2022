package com.example.cricketapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cricketapp.data.entity.Bat;
import com.example.cricketapp.data.repository.BatRepository;

@ExtendWith(MockitoExtension.class)
public class BatServiceUnitTest {

	@Mock
	private BatRepository repo;
	
	@InjectMocks
	private BatService service;
	
	private List<Bat> bats;
	private Bat expectedBat;
	private Bat expectedBatNoId;
	
	@BeforeEach
	public void init() {
		bats = new ArrayList<>();
		bats.addAll(List.of(new Bat("test1 make", "test1 model", 2.7, 2019),
							new Bat("test2 make", "test2 model", 2.8, 2020),
							new Bat("test3 make", "test3 model", 2.9, 2021)));
		expectedBat = new Bat(1,"test make", "test model", 2.8, 2022);
		expectedBatNoId = new Bat("test make", "test model", 2.9, 2021);
	}
	
	// Test get all 
	@Test
	public void getAllBatsTest() {
		when(repo.findAll()).thenReturn(bats);
		assertThat(service.getAll()).isEqualTo(bats);
		verify(repo).findAll();
	}
	
	// Test get by Id
	@Test
	public void getBatbyIdTest() {
		long id = expectedBat.getId();
		when(repo.findById(id)).thenReturn(Optional.of(expectedBat));
		assertThat(service.getById(id)).isEqualTo(expectedBat);
		verify(repo).findById(id);
	}
	
	// Test Create
	@Test
	public void createBatTest() {
		when(repo.save(expectedBatNoId)).thenReturn(expectedBat);
		assertThat(service.create(expectedBatNoId)).isEqualTo(expectedBat);
		verify(repo).save(expectedBatNoId);
	}
	
	// Test Delete
	@Test
	public void deleteBatTest() {
		long id = expectedBat.getId();
		when(repo.existsById(id)).thenReturn(true);
		service.delete(id);
		verify(repo).existsById(id);
		verify(repo).deleteById(id);
	}
}
