package com.example.springdemojdbc.controller;

import com.example.springdemojdbc.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Controller
public class ServerController {
    private final ReceiverService receiverService;

    @Autowired
    public ServerController(ReceiverService receiverService) {
        this.receiverService = receiverService;
    }

    @PostConstruct
    public void startServer() {
        new Thread(this::acceptClients).start();
    }

    private void acceptClients() {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                receiverService.handleClient(clientSocket);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error accepting client connection", e);
        }
    }
}
