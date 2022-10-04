package com.cmc.trans.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmc.trans.model.Employee;
import com.cmc.trans.repository.EmployeeRepository;

import javax.persistence.LockModeType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.util.StringUtils;

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

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Employee updateEmployee(String id, Employee emp) {
        Employee empInfo = employeeRepo.findById(id).orElse(null);
        if (empInfo == null) {
            return null;
        } else {
            if (emp.getName() != null && !StringUtils.isEmpty(emp.getName())) {
                employeeRepo.updateEmployeeName(emp.getName(), id);
            }
            if (emp.getPhone() != null && !StringUtils.isEmpty(emp.getPhone())) {
                employeeRepo.updateEmployeePhone(emp.getPhone(), id);
            }
            if (emp.getDept() != null && !StringUtils.isEmpty(emp.getDept())) {
                employeeRepo.updateEmployeeDept(emp.getDept(), id);
            }
            if (emp.getAddress() != null && !StringUtils.isEmpty(emp.getAddress())) {
                employeeRepo.updateEmployeeAddress(emp.getAddress(), id);
            }
        }
        return empInfo;
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean deleteEmployee(String id) {
        Employee empInfo = employeeRepo.findById(id).orElse(null);
        if (empInfo == null) {
            return false;
        }
        employeeRepo.delete(empInfo);
        return true;
    }
}
