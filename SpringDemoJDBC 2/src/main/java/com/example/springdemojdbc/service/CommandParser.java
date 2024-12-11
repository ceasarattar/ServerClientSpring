package com.example.springdemojdbc.service;

import com.example.springdemojdbc.dto.EmployeeRequest;

public interface CommandParser {
    EmployeeRequest parseCommand(String message);
}
