package com.jun;

public class Account {
    private String cardId;
    private String name;
    private String gender;
    private String password;
    private double balance;
    private double limit;

    public Account(){

    }

    public Account(String cardId, String name, String gender, String password, double balance, double limit) {
        this.cardId = cardId;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.balance = balance;
        this.limit = limit;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        return  (gender.equals("male") ? "Mr. " : "Ms. " ) + formattedName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
