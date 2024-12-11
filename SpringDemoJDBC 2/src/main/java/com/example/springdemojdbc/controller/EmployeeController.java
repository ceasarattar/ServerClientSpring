package com.example.springdemojdbc.controller;

import com.example.springdemojdbc.dto.EmployeeRequest;
import com.example.springdemojdbc.entity.Employee;
import com.example.springdemojdbc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        employeeService.saveEmployee(employeeRequest);
        return ResponseEntity.ok("Employee added successfully");
    }
}
