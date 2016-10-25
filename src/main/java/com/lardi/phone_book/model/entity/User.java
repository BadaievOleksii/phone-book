package com.lardi.phone_book.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name= "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="fio")
    private String fio;


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
