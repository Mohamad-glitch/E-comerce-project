package com.example.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_email")
    private User user;

    public User getUser() {
        return user;
    }

    public void addUser(User userPayment) {
        this.user = userPayment;
    }

    @Column(name ="full_name")
    private String fullName;

    @Column(name ="address")
    private String address;

    @Column(name ="city")
    private String city;

    @Column(name ="state")
    private String state;

    @Column(name ="zip_code")
    private String zipCode;

    @Column(name ="card_number")
    private String cardNumber;

    @Column(name ="expiry_date")
    private String expiryDate;

    @Column(name ="cvv")
    private String cvv;

    @Column(name = "country")
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Payment(User userPayment, String fullName, String address,
                   String city, String state, String zipCode,
                   String cardNumber, String expiryDate, String cvv) {
        this.user = userPayment;
        this.fullName = fullName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public Payment() {
    }

    public Payment(String fullName, String address, String city,
                   String state, String zipCode, String cardNumber,
                   String expiryDate, String cvv) {
        this.fullName = fullName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
