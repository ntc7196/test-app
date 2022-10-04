package com.cmc.trans.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cmc.trans.model.Employee;
import com.cmc.trans.service.EmployeeReadService;
import com.cmc.trans.service.EmployeeWriteService;

@RestController
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	private EmployeeReadService readService;
	@Autowired
	private EmployeeWriteService writeService;

	@GetMapping(value = "/employees")
	public ResponseEntity<?> getEmployees(){
		List<Employee> employees = readService.selectEmployees();
		return ResponseEntity.ok(employees);
	}

	@PostMapping(value = "/employee")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee emp) throws SQLException {
		Boolean saveStatus = writeService.saveEmployee(emp);
		return ResponseEntity.ok(saveStatus);
	}

	@PutMapping(value = "/employee/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") String id, @RequestBody Employee emp) throws SQLException {
		Employee afterUpdateInfo = writeService.updateEmployee(id, emp);
		return ResponseEntity.ok(afterUpdateInfo);
	}

	@DeleteMapping(value = "/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") String id) throws SQLException {
		return ResponseEntity.ok(writeService.deleteEmployee(id));
	}
}
