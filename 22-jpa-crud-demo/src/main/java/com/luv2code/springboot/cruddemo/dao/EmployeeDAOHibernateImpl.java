package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements IEmployeeDAO {

	// define field for entityManager
	private EntityManager entityManager;
	
	// set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl (EntityManager theEntityManager) { // entityManager is automatically defined by Spring Boot (sredili smo konekciju u application.properties)
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {

		// get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> theQuery = 
				session.createQuery("from Employee", Employee.class);
		
		// execute query and get result list
		List<Employee> listOfEmployees = theQuery.getResultList();
		
		// return the results
		return listOfEmployees;
	}

	@Override
	public Employee findById(Integer theId) {

		// get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		// get the employee
		Employee theEmployee = 
				session.get(Employee.class, theId);
		
		// return the employe
		return theEmployee;
	}

	@Override
	public void save(Employee employee) {
		
		// get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		// save Employee
		session.saveOrUpdate(employee); // saveOrUpdate, ako vec postoji id u DB == update, ako nema vec postojeci id u DB == save
		
	}

	@Override
	public void deleteById(Integer theId) {

		// get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		// delete the Employee with query
		Query query =
				session.createQuery("delete from Employee where id=:employeeId"); // equals to DELETE FROM employee_directory WHERE id = ?
		
		query.setParameter("employeeId", theId); // bind values 
		
		query.executeUpdate(); // execute the update
		
	}
	
	

	
	
	
	
	
	
	

}
