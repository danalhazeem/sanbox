package com.example.demo.User.bo;

import javax.management.relation.Role;

public class CreateUserRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;


    public CreateUserRequest () {
        // Default constructor
    }
    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
