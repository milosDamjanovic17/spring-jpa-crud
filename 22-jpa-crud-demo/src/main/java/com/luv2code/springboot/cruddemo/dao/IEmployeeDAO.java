package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Employee;

public interface IEmployeeDAO {
	
	public List<Employee> findAll(); // method will return list of all employees -> SELECT * FROM employee_directory
	
	public Employee findById(Integer theId); // return Employee object from ID -> SELECT * FROM employee_directory WHERE id = ?
	
	public void save(Employee employee); // save a new Employee
	
	public void deleteById(Integer theId); // delete Employee object from ID

}
