package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Employee;

public interface IEmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findById(Integer theId);
	
	public void save(Employee employee);
	
	public void deleteById(Integer theId);

}
