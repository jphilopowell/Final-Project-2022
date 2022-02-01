package com.example.cricketapp.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.cricketapp.data.entity.Bat;
import com.example.cricketapp.data.repository.BatRepository;

@Profile("dev")
@Configuration
public class ApplicationStartUpListener implements ApplicationListener<ApplicationReadyEvent> {

	private BatRepository batRepo;
	
	@Autowired
	public ApplicationStartUpListener(BatRepository batRepo) {
		this.batRepo = batRepo;
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		batRepo.saveAll(List.of(
				new Bat("Kookaburra", "Kahuna", 2.9, 2021),
				new Bat("Garrard", "Special S", 2.8, 2020)
				));
	}
	
}
