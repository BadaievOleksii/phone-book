package com.lardi.phone_book.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "phonebook_records")
public class Record extends BaseEntity{
    @Id
    @Column(name= "record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordId;

    @Column(name= "owner_id")
    private int ownerId;
    @Column(name= "surname")
    private String surname;
    @Column(name= "name")
    private String name;
    @Column(name= "patronymic")
    private String patronymic;
    @Column(name= "mobile_phone")
    private String mobilePhone;
    @Column(name= "home_phone")
    private String homePhone;
    @Column(name= "address")
    private String address;
    @Column(name= "email")
    private String email;


    @Override
    public String toString() {
        return "Record{" +
                "surname='" + surname + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
