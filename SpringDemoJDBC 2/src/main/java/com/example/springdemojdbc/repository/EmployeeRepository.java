package com.example.springdemojdbc.repository;

import com.example.springdemojdbc.entity.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Employee employee) {
        String sql = "INSERT INTO Employees (unique_id, name, age, salary) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, employee.getUniqueID(), employee.getName(), employee.getAge(), employee.getSalary());
    }

    private static class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setUniqueID(rs.getInt("unique_id"));
            employee.setName(rs.getString("name"));
            employee.setAge(rs.getString("age"));
            employee.setSalary(rs.getInt("salary"));
            return employee;
        }
    }
}
