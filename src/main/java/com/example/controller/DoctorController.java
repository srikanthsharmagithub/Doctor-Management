package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

import com.example.entity.Doctor;
import com.example.exception.DoctorException;
import com.example.exception.DoctorExistsException;
import com.example.exception.DoctorNotFoundException;
import com.example.repository.DoctorRepository;



@RestController
public class DoctorController {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	//@GetMapping("/doctor/all")
	@RequestMapping(value = "/doctors/all", method = RequestMethod.GET)
	public List<Doctor> getAllDoctors(){
		return doctorRepository.findAll();
	}
	
	@GetMapping("/doctor/{id}")
	Doctor getDoctor(@PathVariable Integer id) throws DoctorNotFoundException {
		return doctorRepository.findById(id).orElseThrow(()-> new DoctorNotFoundException(id));
	}
	
	
	@PostMapping("/doctor/save")
	public Doctor saveDoctor(@RequestBody Doctor doctor) throws DoctorExistsException {
		
		if(doctorRepository.existsById(doctor.getId())){
			throw new  DoctorExistsException();
		}
		return doctorRepository.save(doctor);
	}
		
	@PutMapping("/doctors/salary/{id}/{salary}")
	ResponseEntity<Doctor> updateSalary(@PathVariable Integer id, @PathVariable int salary) throws  DoctorNotFoundException, DoctorExistsException, DoctorException{
		Doctor d=getDoctor(id);
		d.setSalary(salary);
		doctorRepository.save(d);
		return ResponseEntity.ok(d);
	}
	
	@DeleteMapping("/delete/doctor/{id}")
	void deleteDoctor(@PathVariable Integer id) {
		doctorRepository.deleteById(id);
	}

	@GetMapping("/desc")
	public List<Doctor>getAllDoctorsDescendingBySalary1(){
		List<Doctor> s=doctorRepository.findAll();
		return doctorRepository.findAll(Sort.by(Direction.DESC,"salary").and(Sort.by(Sort.Direction.ASC, "name")));
				}
	}

	
