package com.cmc.trans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmc.trans.model.Employee;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

    @Modifying
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(nativeQuery = true, value = "UPDATE Employee SET e_name = :name WHERE uuid = :id")
    void updateEmployeeName(@Param("name") String name, @Param("id") String id);
    
    @Modifying
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(nativeQuery = true, value = "UPDATE Employee SET e_dept = :dept WHERE uuid = :id")
    void updateEmployeeDept(@Param("dept") String name, @Param("id") String id);
    
    @Modifying
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(nativeQuery = true, value = "UPDATE Employee SET e_phone = :phone WHERE uuid = :id")
    void updateEmployeePhone(@Param("phone") String name, @Param("id") String id);
    
    @Modifying
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(nativeQuery = true, value = "UPDATE Employee SET e_address = :address WHERE uuid = :id")
    void updateEmployeeAddress(@Param("address") String name, @Param("id") String id);
    
}
