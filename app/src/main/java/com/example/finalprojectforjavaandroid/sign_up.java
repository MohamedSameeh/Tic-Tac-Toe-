package com.example.finalprojectforjavaandroid;

public class sign_up {
    String first_name, username, email, password;
    int id;

    public sign_up() {
    }

    public sign_up(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public sign_up(String username, String email, String password, int id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
