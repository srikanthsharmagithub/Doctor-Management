package com.example.exception;

public class DoctorExistsException extends Exception{
	
	public DoctorExistsException() {
		super("doctor already exists in database");
	}
}
