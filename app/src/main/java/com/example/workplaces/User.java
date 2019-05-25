package com.example.workplaces;

public class User {

    private String Name;
    private String LastName;
    private String Phone;
    private String Email;

    public User(String name, String lastName, String phone, String email) {
        Name = name;
        LastName = lastName;
        Phone = phone;
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
