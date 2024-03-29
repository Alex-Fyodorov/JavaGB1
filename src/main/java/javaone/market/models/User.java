package javaone.market.models;

import java.time.LocalDate;

public class User {
    private Integer id;
    private final String username;
    private final Sex sex;
    private final LocalDate birthDate;
    private String phone;

    public User(String name, Sex sex, LocalDate birthDate, String phone) {
        this.username = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Sex getSex() {
        return sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", sex=" + sex.getValue() +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                '}';
    }
}
