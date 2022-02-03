package com.example.cricketapp.data.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Bats")
public class Bat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String make;
	
	@NotNull
	private String model;
	
	private double weight;
	
	private int age;
	
	public Bat(String make, String model, double weight, int age) {
		this.make = make;
		this.model = model;
		this.weight = weight;
		this.age = age;
	}
	
	public Bat(long nextNewElementsId, String make, String model, double weight, int age) {
		this.id = (long) nextNewElementsId;
		this.make = make;
		this.model = model;
		this.weight = weight;
		this.age = age;
	}
	
	public Bat() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double d) {
		this.weight = d;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Bat [id=" + id + ", make=" + make + ", model=" + model + ", weight=" + weight + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, id, make, model, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bat other = (Bat) obj;
		return age == other.age && Objects.equals(id, other.id) && Objects.equals(make, other.make)
				&& Objects.equals(model, other.model)
				&& Float.floatToIntBits((float) weight) == Float.floatToIntBits((float) other.weight);
	}


	
}
