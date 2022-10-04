package com.cmc.trans.service;

import com.cmc.trans.model.Employee;
import com.cmc.trans.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeReadNativeService {

//    @Value("${spring.datasource.url}")
//    private String DB_URL;
//    @Value("${spring.datasource.username}")
//    private String USER;
//    @Value("${spring.datasource.password}")
//    private String PASS;
    
    //static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/dkr_dev";
    static final String DB_URL = "jdbc:mariadb://172.16.201.111:3306/trans_db";
    //static final String USER = "root";
    static final String USER = "mallet";
    //static final String PASS = "cmc#drk";
    static final String PASS = "!dkapflzksh#@";

    static final String QUERY = "SELECT uuid, e_name, e_dept, e_phone, e_address FROM Employee";

    public List<Employee> selectEmployees() {
        List<Employee> listEmployee = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(QUERY);
        ) {
            while(rs.next()){
                String uuid = rs.getString("uuid");
                String name = rs.getString("e_name");
                String dept = rs.getString("e_dept");
                String phone = rs.getString("e_phone");
                String address = rs.getString("e_address");
                Employee emp =setEmployee(uuid,name,dept,phone,address);
                listEmployee.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listEmployee;
    }

    private Employee setEmployee(String uuid, String name, String dept, String phone, String address)
    {
        Employee emp = new Employee();
        emp.setUuid(uuid);
        emp.setName(name);
        emp.setDept(dept);
        emp.setPhone(phone);
        emp.setAddress(address);
        return emp;
    }
}
