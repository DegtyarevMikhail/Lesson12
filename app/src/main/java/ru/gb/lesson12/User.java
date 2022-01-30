package ru.gb.lesson12;

public class User {
    private String firstName;
    private String lastName;

    public User(String firstName, String secondName) {
        this.firstName = firstName;
        this.lastName = secondName;
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
}
