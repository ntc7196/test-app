package com.cmc.trans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmc.trans.model.Employee;
import com.cmc.trans.repository.EmployeeRepository;
import org.springframework.transaction.annotation.Isolation;

@Service
public class EmployeeReadService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public List<Employee> selectEmployees() {
		return employeeRepo.findAll();
	}

	
}