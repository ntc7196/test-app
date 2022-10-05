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
    String ERROR_MESSAGE = "Update failed";
    String SUCCESS_MESSAGE = "Update succeed";
    String CRETE_SUCCEED="Create succeed";
    String SUCCEED="Succeed";

    @GetMapping(value = "/employees")
    public ResponseEntity<?> getEmployees() throws JsonProcessingException {
            List<Employee> employees = readService.selectEmployees();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDataAPI(employees));
    }

    @PostMapping(value = "/employee")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee emp) throws SQLException {
            if (writeService.saveEmployee(emp)) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDataAPI(CRETE_SUCCEED));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDataAPI(ERROR_MESSAGE));
            }
    }

    @PutMapping(value = "/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") String id, @RequestBody Employee emp) throws SQLException, InterruptedException {
            Employee employee = writeService.updateEmployee(id, emp);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDataAPI(SUCCESS_MESSAGE, employee));

    }

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable(value = "id") String id) throws SQLException {
            Employee emp = readService.getEmployee(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDataAPI(SUCCEED, emp));
    }

    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") String id) throws SQLException {
        if (writeService.deleteEmployee(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDataAPI(SUCCEED));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDataAPI(ERROR_MESSAGE));
        }
    }
}
