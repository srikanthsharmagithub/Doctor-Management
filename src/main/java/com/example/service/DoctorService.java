package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Doctor;
import com.example.repository.DoctorRepository;


@Service
public class DoctorService {
	
	public DoctorRepository repository;
	
	public Doctor saveDoctor( Doctor doctor) {
		return repository.save(doctor);
	}
	
	public List<Doctor> getAllDoctors(){
		return repository.findAll();
	}
	
	
}