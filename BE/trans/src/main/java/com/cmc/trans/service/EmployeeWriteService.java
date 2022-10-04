package com.cmc.trans.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmc.trans.model.Employee;
import com.cmc.trans.repository.EmployeeRepository;

import javax.persistence.LockModeType;

@Service
public class EmployeeWriteService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean saveEmployee(Employee employee) {
		UUID uuid = UUID.randomUUID();
		employee.setUuid(String.valueOf(uuid));
		return employeeRepo.save(employee) != null;
	}

	@Lock(LockModeType.PESSIMISTIC_READ)
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Employee updateEmployee(String id, Employee emp) {
		Employee empInfo = employeeRepo.findById(id).orElse(null);
		if(empInfo == null) {
			return null;
		} else {
			empInfo.setName(emp.getName());
			empInfo.setDept(emp.getDept());
			empInfo.setPhone(emp.getPhone());
			empInfo.setAddress(emp.getAddress());
			empInfo = employeeRepo.save(empInfo);
		}
		
		return empInfo;
	}
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean deleteEmployee(String id) {
		Employee empInfo = employeeRepo.findById(id).orElse(null);
		if(empInfo == null) {
			return false;
		}
		employeeRepo.delete(empInfo);
		return true;
	}
}
