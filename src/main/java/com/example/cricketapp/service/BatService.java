package com.example.cricketapp.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cricketapp.data.entity.Bat;
import com.example.cricketapp.data.repository.BatRepository;

@Service
public class BatService {

	private BatRepository batRepo;
	
	@Autowired
	public BatService(BatRepository batRepo) {
		this.batRepo = batRepo;
	}
	
	// View all bats
	public List<Bat> getAll() {
		return batRepo.findAll();
	}

	// View bat by Id
	public Bat getById(long id) {
		return batRepo.findById(id).orElseThrow(() -> {
			return new EntityNotFoundException("Bat with id" + id + "does not exist");});
		
		}
	
	// Create bat
	public Bat create(Bat bat) {
		Bat savedBat = batRepo.save(bat);
		return savedBat;
	}
	
	// Update bat
	public Bat update(long id, Bat bat) {
		if (batRepo.existsById(id)) {
			Bat batExists = batRepo.getById(id);
			batExists.setMake(bat.getMake());
			batExists.setModel(bat.getModel());
			batExists.setWeight(bat.getWeight());
			batExists.setAge(bat.getAge());
			return batRepo.save(batExists);
		} else {
			throw new EntityNotFoundException("Bat with id" + id + "does not exist");
		}
	}
	
	// Delete bat
	public void delete(long id) {
		if (batRepo.existsById(id)) {
			batRepo.deleteById(id);
		} else {
			throw new EntityNotFoundException("Bat with id" + id + "does not exist");
		}
	}
	
	
	}

