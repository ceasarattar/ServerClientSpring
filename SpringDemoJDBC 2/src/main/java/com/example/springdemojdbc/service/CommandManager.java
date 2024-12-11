package com.example.springdemojdbc.service;

import com.example.springdemojdbc.dto.EmployeeRequest;
import com.example.springdemojdbc.entity.Employee;
import com.example.springdemojdbc.enums.FileType;
import com.example.springdemojdbc.enums.OperationType;
import com.example.springdemojdbc.exception.CommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Component
public class CommandManager {
    private static final Logger logger = LoggerFactory.getLogger(CommandManager.class);

    private static final int INIT_ID = 2024001; // Initial unique ID value
    private static final AtomicInteger UNIQUE_ID = new AtomicInteger(INIT_ID);

    private final CommandParser commandParser;
    private final EmployeeService employeeService;
    private final HashMap<OperationType, Consumer<EmployeeRequest>> commandMap = new HashMap<>();

    @Autowired
    public CommandManager(@Lazy CommandParser commandParser, EmployeeService employeeService) {
        this.commandParser = commandParser;
        this.employeeService = employeeService;

        commandMap.put(OperationType.ADD, this::handleAdd);
        commandMap.put(OperationType.EXIT, this::handleExit);
    }

    public void handleCommand(String command) {
        logger.info("Handling command: {}", command);
        EmployeeRequest employeeRequest = commandParser.parseCommand(command);
        Consumer<EmployeeRequest> commandAction = commandMap.get(employeeRequest.getOperationType());

        if (commandAction != null) {
            commandAction.accept(employeeRequest);
        } else {
            logger.error("Unknown command: {}", command);
        }
    }

    private void handleAdd(EmployeeRequest employeeRequest) {
        Employee employee = employeeRequest.getEmployee();
        employee.setUniqueID(generateUniqueId());

        logger.info("Handling Add Command");
        logger.info("Storage Type: {}", employeeRequest.getFileType());

        if (employeeRequest.getFileType() == FileType.JDBC) {
            employeeService.saveEmployee(employeeRequest);
        } else {
            logger.error("Unknown storage type: {}", employeeRequest.getFileType());
        }
    }

    private void handleExit(EmployeeRequest employeeRequest) {
        throw new CommandException("EXIT");
    }

    private int generateUniqueId() {
        return UNIQUE_ID.getAndIncrement();
    }
}
