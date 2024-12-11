# Encrypted Messenger

The **Encrypted Messenger** is a secure communication application built using the **Spring Framework**, adhering to the **Single Responsibility Principle** to ensure each class serves a distinct purpose. It employs a **RESTful API** architecture, enabling operations such as **POST**, **GET**, and **DELETE**, which can be tested using tools like **Postman**.

## Key Features

- **Data Persistence**: Utilizes **Hibernate ORM** to interact with a **MySQL database**, ensuring efficient storage and retrieval of messages and user data.
- **Client-Server Architecture**: Features a two-sided model where the **client** encrypts messages using **AES (Advanced Encryption Standard)** before sending them to the server.
- **Data Security**: Messages are **decrypted** on the server side before being securely stored in the database, ensuring data confidentiality and integrity during transit.
- **RESTful API**: Built-in endpoints facilitate CRUD operations for seamless interaction with the database.

## Design Highlights

- **Single Responsibility Principle**: Each class in the application has a clearly defined and isolated responsibility, promoting clean and maintainable code.
- **Spring Framework Integration**: The application leverages the power of Spring Boot for dependency injection, RESTful service creation, and application-level configuration.

This project demonstrates a commitment to robust security practices, efficient data handling, and adherence to modern software engineering principles.
