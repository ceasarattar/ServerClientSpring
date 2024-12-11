package com.example.springdemojdbc.dto;

import com.example.springdemojdbc.entity.Employee;
import com.example.springdemojdbc.enums.OperationType;
import com.example.springdemojdbc.enums.FileType;

public class EmployeeRequest {
    private OperationType operationType;
    private FileType fileType;
    private Employee employee;

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
