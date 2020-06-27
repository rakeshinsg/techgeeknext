package com.techgeeknext.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techgeeknext.model.Employee;
import com.techgeeknext.repository.EmployeeRepository;

@CrossOrigin()
@RestController
@RequestMapping({ "/employees" })
public class EmployeeCrudController {
	@Autowired
	EmployeeRepository employeeRepository;

	//private List<Employee> employees = createList();
	private List<Employee> employees = null;

	@GetMapping(produces = "application/json")
	public List<Employee> firstPage() {
		List<Employee> employees = employeeRepository.findAll() ;
		if(employees != null)
		   return employees ;
		return employees = new ArrayList<>();
	}

	@DeleteMapping(path = { "/{id}" })
	public Employee delete(@PathVariable("id") int id) {
		Optional<Employee> deletedEmp = null;
		deletedEmp = employeeRepository.findById((long) id);
		employeeRepository.deleteById((long) id);
		/*
		 * for (Employee emp : employees) { if (emp.getEmpId().equals(id)) {
		 * employees.remove(emp); deletedEmp = emp; break; } }
		 */
		return deletedEmp.get();
	}

	@PostMapping
	public Employee create(@RequestBody Employee user) {
		//employees.add(user);
		employeeRepository.save(user);
		return user;
	}

	private static List<Employee> createList() {
		List<Employee> tempEmployees = new ArrayList<>();
		Employee emp1 = new Employee();
		emp1.setName("emp1");
		emp1.setDesignation("manager");
		emp1.setEmpId(1L);
		emp1.setSalary(3000);

		Employee emp2 = new Employee();
		emp2.setName("emp2");
		emp2.setDesignation("developer");
		emp2.setEmpId(2L);
		emp2.setSalary(3000);
		tempEmployees.add(emp1);
		tempEmployees.add(emp2);
		return tempEmployees;
	}

}