package com.example.cricketapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cricketapp.data.entity.Bat;
import com.example.cricketapp.data.repository.BatRepository;

@SpringBootTest
@Transactional
public class BatServiceIntegrationTest {

	@Autowired
	private BatService service;
	
	@Autowired
	private BatRepository repo;
	
	private List<Bat> batsInDB;
	
	private long nextNewElementsId;
	
	@BeforeEach
	public void init() {
		List<Bat> bats = List.of(new Bat("test1 make", "test1 model", 2.7, 2019),
								 new Bat("test2 make", "test2 model", 2.8, 2020),
								 new Bat("test3 make", "test3 model", 2.9, 2021));
		batsInDB = new ArrayList<>();
		batsInDB.addAll(repo.saveAll(bats));
		int size = batsInDB.size();
		nextNewElementsId = batsInDB.get(size - 1).getId() + 1;
		
	}
	
	// Test get all Bats
	@Test
	public void getAllBatsTest() {
		
		assertThat(batsInDB).isEqualTo(service.getAll());
	}
	
	// Test get Bat by Id
	@Test
	public void getBatById() {
		
		Bat batInDB = batsInDB.get(0);
		
		assertThat(service.getById(batInDB.getId())).isEqualTo(batInDB);
	}
	
	// Test Create Bat
	@Test
	public void createBatTest() {
		
		Bat batSave = new Bat("test make", "test model", 2.7, 2022);
		
		Bat expectedBat = new Bat(nextNewElementsId, batSave.getMake(), batSave.getModel(), batSave.getWeight(), batSave.getAge());
		
		assertThat(expectedBat).isEqualTo(service.create(batSave));
	}
	
	// Test Delete Bat
	@Test
	public void deleteBatTest() {
		
		Bat batInDB = batsInDB.get(0);
		
		long id = batInDB.getId();
		
		service.delete(id);
		
		assertThat(repo.findById(id)).isEqualTo(Optional.empty());
	}
	
	// Test Update Bat
	@Test
	public void updateBatTest() {
		
		Bat batInDB = batsInDB.get(0);
		
		long id = batInDB.getId();
		
		Bat batUpdate = new Bat(batInDB.getId(), batInDB.getMake(), batInDB.getModel(), batInDB.getWeight(), batInDB.getAge() + 1);
		
		Bat actual = service.update(id, batUpdate);
		
		assertThat(actual).isEqualTo(batUpdate);	
	}
	
}
