package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.IEmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	private final IEmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServiceImpl(@Qualifier("employeeDAOJPAImpl") IEmployeeDAO theEmployeeDAO) { // eksplicitno receno koji tacno DAOImpl koristimo jer imamo dve klase u kojima je injectovan
																							   // IEmployeeDAO interfejs, u ovom slucaju koristimo DAO koji koristi JPAQL
		employeeDAO = theEmployeeDAO;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {


		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(Integer theId) {

		return employeeDAO.findById(theId);

	}

	@Override
	@Transactional
	public void save(Employee employee) {
		
		employeeDAO.save(employee);

	}

	@Override
	@Transactional
	public void deleteById(Integer theId) {
		
		employeeDAO.deleteById(theId);

	}

}
