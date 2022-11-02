package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJPAImpl implements IEmployeeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJPAImpl(EntityManager theEntityManager) {
		
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {

		// create a query
		Query theQuery = 
				entityManager.createQuery("from Employee", Employee.class);
		
		// execute query and get result list
		List<Employee> employeeList = theQuery.getResultList();
		
		// return results
		return employeeList;
	}

	@Override
	public Employee findById(Integer theId) {

		// get employee
		Employee tempEmployee = 
				entityManager.find(Employee.class, theId);
		
		// return employee
		return tempEmployee;
	}

	@Override
	public void save(Employee employee) {

		// save or update the employee
		Employee dbEmployee = 
				entityManager.merge(employee);
		
		// update with id from db ... so we can get generated id for save/insert
		employee.setId(dbEmployee.getId());

	}

	@Override
	public void deleteById(Integer theId) {

		// delete object with primary key
		Query theQuery = entityManager.createQuery(
				"delete from Employee where id = :employeeId");
		
		// bind parameters
		theQuery.setParameter("employeeId", theId);
		
		// execute query
		theQuery.executeUpdate();

	}

}
