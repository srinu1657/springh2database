package org.example.dao;

import org.example.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE employee1 (id INT PRIMARY KEY, name VARCHAR(50))");
    }

    public void insertEmployee(int id, String name) {
        jdbcTemplate.update("INSERT INTO employee1 (id, name) VALUES (?, ?)", id, name);
    }

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT * FROM employee1", (rs, rowNum) ->
                new Employee(rs.getInt("id"), rs.getString("name")));
    }
}
