package com.example.bankingapp;

public class UserD {
    String name;
    String email;
    int phone;
    int id;
    double amount;
    public UserD(int id, String name,double amount,String email,int phone){
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.amount=amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
