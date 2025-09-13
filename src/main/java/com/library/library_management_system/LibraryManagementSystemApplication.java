package com.library.library_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class that starts the Library Management System
 * This is the entry point of our Spring Boot application
 */
@SpringBootApplication
public class LibraryManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
        System.out.println("Library Management System started successfully!");
    }
}