package com.example.cricketapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cricketapp.data.entity.Bat;

@Repository
public interface BatRepository extends JpaRepository<Bat, Long> {

}
