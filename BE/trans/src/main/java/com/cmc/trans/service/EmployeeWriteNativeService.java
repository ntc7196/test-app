package com.cmc.trans.service;

import com.cmc.trans.model.Employee;
import com.cmc.trans.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.sql.*;
import java.util.UUID;
@Service
public class EmployeeWriteNativeService {
    //static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/dkr_dev";
    static final String DB_URL = "jdbc:mariadb://172.16.201.111:3306/trans_db";
    //static final String USER = "root";
    static final String USER = "mallet";
    //static final String PASS = "cmc#drk";
    static final String PASS = "!dkapflzksh#@";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

    Statement stmt = conn.createStatement();

    public EmployeeWriteNativeService() throws SQLException {
    }

    public String saveEmployee(Employee employee) throws SQLException {
        conn.setAutoCommit(false);
        UUID uuid = UUID.randomUUID();
        String insertQuery = "INSERT INTO employee VALUE (?,?,?,?,?)";
        employee.setUuid(String.valueOf(uuid));
        try (PreparedStatement insert = conn.prepareStatement(insertQuery)){
            insert.setString(1, String.valueOf(uuid));
            insert.setString(2, employee.getName());
            insert.setString(3, employee.getDept());
            insert.setString(4, employee.getPhone());
            insert.setString(5, employee.getAddress());
            insert.executeUpdate();
            conn.commit();
            return "Insert data success";
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            return "Insert data failed";
        }
    }

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public String updateEmployee(Employee employee, String id) throws SQLException {
        conn.setAutoCommit(false);
        String updateQuery = "UPDATE employee SET e_name = ?, e_dept = ?, e_phone = ?, e_address = ? where uuid= ?";
        try (PreparedStatement update = conn.prepareStatement(updateQuery)){
            update.setString(1, employee.getName());
            update.setString(2, employee.getDept());
            update.setString(3, employee.getPhone());
            update.setString(4, employee.getAddress());
            update.setString(5, id);
            update.executeUpdate();
            conn.commit();
            return "Update data success";
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            return "Update data failed";
        }
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public String deleteEmployee(String id) throws SQLException {
        conn.setAutoCommit(false);
        String deleteQuery = "DELETE FROM employee WHERE uuid = ?";
        try (PreparedStatement delete = conn.prepareStatement(deleteQuery)){
            delete.setString(1, id);
            delete.executeUpdate();
            conn.commit();
            return "Delete data success";
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            return "Delete data failed";
        }
    }
}
