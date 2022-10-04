package com.cmc.trans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmc.trans.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
