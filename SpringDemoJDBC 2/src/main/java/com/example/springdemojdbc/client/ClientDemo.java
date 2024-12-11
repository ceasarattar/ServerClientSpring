package com.example.springdemojdbc.client;

import com.example.springdemojdbc.service.EncryptorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@Component
public class ClientDemo implements CommandLineRunner {

    private final EncryptorService encryptorService;

    @Autowired
    public ClientDemo(EncryptorService encryptorService) {
        this.encryptorService = encryptorService;
    }

    @Override
    public void run(String... args) {
        new Thread(this::startClient).start();
    }

    private void startClient() {
        try (Socket socket = new Socket("localhost", 1234);
             InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
             BufferedReader bufferStreamReader = new BufferedReader(inputStreamReader);
             BufferedWriter bufferStreamWriter = new BufferedWriter(outputStreamWriter);
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("Please input a command followed by the name, age, and salary: ");
                String message = scanner.nextLine();

                System.out.println("Would you like to save this employee to the file or database?");
                String fileType = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    bufferStreamWriter.write(encryptorService.encryptMessage("exit"));
                    bufferStreamWriter.newLine();
                    bufferStreamWriter.flush();
                    break;
                }

                String finalMessage = message.concat(" " + fileType);
                String encryptedMessage = encryptorService.encryptMessage(finalMessage);

                bufferStreamWriter.write(encryptedMessage);
                bufferStreamWriter.newLine();
                bufferStreamWriter.flush();

                String serverMessage = bufferStreamReader.readLine();
                System.out.println("Server: " + serverMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}