package com.example.springdemojdbc.service;

import com.example.springdemojdbc.dto.EmployeeRequest;
import com.example.springdemojdbc.entity.Employee;
import com.example.springdemojdbc.enums.FileType;
import com.example.springdemojdbc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeRequest.getEmployee();
        if (employeeRequest.getFileType() == FileType.JDBC) {
            employeeRepository.save(employee);
        }
    }
}
