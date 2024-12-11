package com.example.springdemojdbc.service;

import com.example.springdemojdbc.exception.CommandException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;

@Service
public class ReceiverService implements Runnable {
    private final CommandManager commandManager;
    private Socket socket;

    public ReceiverService(@Lazy CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void handleClient(Socket socket) {
        this.socket = socket;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                BufferedReader reader = new BufferedReader(inputStreamReader);
                BufferedWriter writer = new BufferedWriter(outputStreamWriter)
        ) {
            String command;
            while ((command = reader.readLine()) != null) {
                try {
                    System.out.println("Received from client: " + command);
                    System.out.println("TEST1");
                    commandManager.handleCommand(command);

                    writer.write("Command processed");
                    writer.newLine();
                    writer.flush();

                    if (command.equalsIgnoreCase("exit")) {
                        break;
                    }

                } catch (CommandException e) {
                    writer.write(e.getMessage());
                    writer.newLine();
                    writer.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error in ReceiverService", e);
        }
    }
}
