package ru.geekbrains.android.arch.mvp.model.entities;

public class User {

    private final String name;
    private final String username;
    private final String email;
    private final String phone;
    private final String city;

    public User(String name, String username, String email, String phone, String city) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
