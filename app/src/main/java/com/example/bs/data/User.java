package com.example.bs.data;

public class User {
    Integer id;
    String firstName;
    String lastName;
    String email;
    String password;
    Boolean isParent;
    Boolean isBabySitter;

    public User(String firstName, String lastName, String email, String password, Boolean isParent, Boolean isBabySitter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isParent = isParent;
        this.isBabySitter = isBabySitter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public Boolean getBabySitter() {
        return isBabySitter;
    }

    public void setBabySitter(Boolean babySitter) {
        isBabySitter = babySitter;
    }

    public User(Integer id, String firstName, String lastName, String email, String password, Boolean isParent, Boolean isBabySitter) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isParent = isParent;
        this.isBabySitter = isBabySitter;
    }

    public User() {
    }
}
