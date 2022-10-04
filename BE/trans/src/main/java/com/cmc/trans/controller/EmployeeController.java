package com.cmc.trans.controller;

import java.sql.SQLException;
import java.util.List;

import com.cmc.trans.model.ResponseDataAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    String ERROR_MESSAGE = "failed";
    String SUCCESS_MESSAGE = "succeed";

    @GetMapping(value = "/employees")
    public ResponseEntity<?> getEmployees() throws JsonProcessingException {
        try {
            String a = "123";
            String b = a.substring(0,19);
            List<Employee> employees = readService.selectEmployees();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDataAPI(employees));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDataAPI(ERROR_MESSAGE));
        }
    }

    @PostMapping(value = "/employee")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee emp) throws SQLException {
        try {
            if (writeService.saveEmployee(emp)) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDataAPI(SUCCESS_MESSAGE));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDataAPI(ERROR_MESSAGE));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDataAPI(ERROR_MESSAGE));
        }

    }

    @PutMapping(value = "/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") String id, @RequestBody Employee emp) throws SQLException {
        try {
            Employee employee = writeService.updateEmployee(id, emp);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDataAPI(SUCCESS_MESSAGE, employee));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDataAPI(ERROR_MESSAGE));
        }
    }

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable(value = "id") String id) throws SQLException {
        try {
            Employee afterUpdateInfo = readService.getEmployee(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDataAPI(SUCCESS_MESSAGE, afterUpdateInfo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDataAPI(ERROR_MESSAGE));
        }
    }

    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") String id) throws SQLException {
        try {
            if (writeService.deleteEmployee(id)) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDataAPI(SUCCESS_MESSAGE));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDataAPI(ERROR_MESSAGE));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDataAPI(ERROR_MESSAGE));
        }
    }
}
