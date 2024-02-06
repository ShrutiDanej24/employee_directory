package com.springboot.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.backend.exception.ResourceNotFoundException;
import com.springboot.backend.model.Employee;
import com.springboot.backend.repository.EmployeeRepository;
import com.springboot.backend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	//Constructor based dependency injection
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}



	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}



	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}



	@Override
	public Employee getEmployeeById(long id) {
		
//		Optional<Employee> employee = new employeeRepository.findById(id);
//		if(employee.isPresent())
//		{
//			return employee.get();
//		}
//		else {
//			throw new ResourceNotFoundException("Employee","ID",id);
//		}
		
//OR 
		
		return employeeRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Employee","ID",id));
		
	}



	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		//Check whether employee with given id is exist in db or not ; if it is not present then it throw ResourceNotFoundException
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Employee","ID",id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		//Save existingEmployee to db
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}



	@Override
	public void deleteEmployee(long id) {
		
		//Check whether employee with given id is exist in db or not ; if it is not present then it throw ResourceNotFoundException
		employeeRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Employee","ID",id));
		
		//Delete employee by id
		employeeRepository.deleteById(id);
	}

}
