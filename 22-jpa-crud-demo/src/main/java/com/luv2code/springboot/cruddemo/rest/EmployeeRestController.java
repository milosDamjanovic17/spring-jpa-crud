package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.IEmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private IEmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(IEmployeeService theIEmployeeService) {
		employeeService = theIEmployeeService;
	}
	
	
	// expose "/employess" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll(); // delegate just to interface method to get the list
	}
	
	
	// add mapping for GET / employees/{employeeId} , mapping that will return Employee obj based on passed ID digit 
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable Integer employeeId) {
		
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null) {
			throw new RuntimeException("Employee id not found! - " +employeeId);
		}else {
			return theEmployee;
		}
		
	}
	
	// add mapping for POST /employees - add a new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		// also just in case they add id in JSON .. set id to 0, this is to force a save of new item .. instead of update!
		
		theEmployee.setId(null);
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	
	// add mapping for PUT /employees - update existing employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		
		//pass employee to update
		employeeService.save(employee);
		
		return employee;
	}
	
	// add mapping for DELETE /employees - delete existing employee
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployeebyId(@PathVariable Integer employeeId) {
		
		Employee tempEmployee = employeeService.findById(employeeId);
		
		//throw exception if Employee id doesn't exist
		
		if(tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " +employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return "Deleted Employee id - " +employeeId;
	}
	
	
	
	
}
