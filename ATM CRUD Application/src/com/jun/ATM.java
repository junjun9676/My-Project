package com.jun;

import javax.xml.namespace.QName;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    //This class is to use for system perform all tasks. eg, Menu page, create Acc, Transfer

    private ArrayList<Account> accounts = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private Random r = new Random();
    private Account loginAcc; // Used to remember logined account

    public void start(){

        while (true) {
            System.out.println("==Welcome to Jus ATM Bank System==");
            System.out.println("1. Account Login");
            System.out.println("2. Account Registration");
            System.out.println("Please select your command to choose operation:");
            int command = sc.nextInt();
            switch (command){
                case 1:
                    // account login
                    login();
                    break;
                case 2:
                    // account registration
                    createAccount();
                    break;
                default:
                    System.out.println("Does not have this command, please try again~~");
            }
        }
    }

    public void createAccount(){
        Account acc = new Account();

        System.out.println("Insert your name: ");
        String name = sc.next();
        acc.setName(name);

        //check gender
        while (true) {
            System.out.println("Insert your gender: ");
            String gender = sc.next();
            if (gender.equals("male") || gender.equals("female")){
                acc.setGender(gender);
                break;
            }else {
                System.out.println("Your insert for gender can only be male or female");
            }
        }

        // Check password
        while (true) {
            System.out.println("Insert your password: ");
            String password = sc.next();
            System.out.println("Please insert again to confirm password: ");
            String okPassword = sc.next();
            if (okPassword.equals(password)){
                acc.setPassword(password);
                break;
            }else {
                System.out.println("Your entered password was not matched!");
            }
        }

        // Transaction limit
        System.out.println("Insert your transaction limit per time: ");
        double limit = sc.nextDouble();
        acc.setLimit(limit);

        String cardId = createCardId();
        acc.setCardId(cardId);

        accounts.add(acc);
        System.out.println("Congratulations, " + acc.getName() + "! You have successfully created an account."
        + "\n Your card id is " + cardId);

    }

    private String createCardId(){

        while (true) {
            // 1. initialize a string to remember 8 digit card id
            StringBuilder cardIdBuilder  = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                // 0 1 2 3 4 5 6 7
                int num = r.nextInt(10);  // 0 - 9 number
                cardIdBuilder.append(num);
            }

            String cardId = cardIdBuilder.toString();

            // 2. check if the number of remembered card id was it repeated, if no repeated then only can return as a new cardId
            if (getAccountByCardId(cardId) == null){
                return cardId; // Return once we find a unique ID
            }
        }
    }

    /** Based on the card id to check for account object and return
     * accounts = [c1, c2, c3]
     * */
    private Account getAccountByCardId(String cardId){
        // iterate the whole account object
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            // check if this card id is it the one that we look for
            if(acc.getCardId().equals(cardId)){
                return acc;
            }
        }
        return null;
    }


    private void login(){
        int count = 3;

        System.out.println("==Login ATM System==");

        // 1. Check if the system have account object, if no then not allow login
        if (accounts.isEmpty()){
            System.out.println("No account exist in system. Please go to account registration");
            start();
        }

        while (true) {
            //2. check the account object, can start to login
            System.out.println("Enter your card Id to login: ");
            String cardId = sc.next();
            //3. Check if this card id exist
            Account acc = getAccountByCardId(cardId);
            if (acc == null) {
                // not exist
                System.out.println("The card Id was not exist, please confirm");
            }else{
                // Account exist, then login password
                while (true) {

                    System.out.println("Please enter your login password: ");
                    String password = sc.next();
                    if (acc.getPassword().equals(password)){
                        loginAcc = acc;
                        System.out.println("Login Successfully. Welcome back, " + acc.getName() + ". Your card id is " + acc.getCardId());
                        menu();
                        return;
                    }else {
                        count--;
                        String warning = (count < 1) ? "No more attempts" : "Incorrect password. " + count + "more attemps";
                        System.out.println(warning);
                        if (count < 1) start();
                    }
                }
            }
        }
    }

    public void menu() {
        while (true) {
            System.out.println("====Account Management ===");
            System.out.println("1. Check Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Change Password");
            System.out.println("6. Delete Account");
            System.out.println("7. Exit");
            int command = sc.nextInt();
            switch (command){
                case 1:
                    // check acc
                    showLoginAccount();
                    break;
                case 2:
                    // deposit money
                    depositMoney();

                    break;
                case 3:
                    // withdraw money
                    withdrawMoney();
                    break;
                case 4:
                    transferMoney();
                    break;
                case 5:
                    changePassword();
                    break;
                case 6:
                    if (deleteAccount()) {
                        return;
                    }
                        break; //break the switch loop, but will appear again the menu and ask user input again
                case 7:
                    System.out.println("You have successfully exit system!");
                    return; // jump out from the method
                default:
                    System.out.println("Please enter valid number~~");
            }
        }
    }

    private boolean deleteAccount() {
        while (true) {
            System.out.println("====Delete Account====");
            System.out.println("Are you sure want to delete account? (y/n)");
            String command = sc.next();
            if (command.toLowerCase().charAt(0) == 'y'){
                // check if account has balance
                if (loginAcc.getBalance() > 0){
                    System.out.println("your account still have balance. Delete account not allowed");
                    return false;
                }
                else {
                    // continue remove account
                    accounts.remove(loginAcc);
                    System.out.println("Account Remove Succesfully!\n\n");
                    return true;
                }
                // if command not n
            } else if (command.toLowerCase().charAt(0) == 'n') {
                System.out.println("Returning to menu...");
                return false;
            }else {
                System.out.println("Invalid input, please try again");
            }
        }
    }

    private void changePassword() {
        while (true) {
        System.out.println("====Change Password====");
        System.out.println("Enter your current password: ");
        String currentPassword = sc.next();

            if (loginAcc.getPassword().equals(currentPassword)){
                while (true) {
                    System.out.println("Enter your new password: ");
                    String newPassword = sc.next();
                    System.out.println("Please Re-enter again your passord: ");
                    String okPassword = sc.next();
                    if (okPassword.equals(newPassword)){
                        if (newPassword.equals(loginAcc.getPassword())) {
                            System.out.println("Your new password cannot be same with old password!!");
                        }
                        else {
                            loginAcc.setPassword(okPassword);
                            System.out.println("Password changed successfully!");
                            return;
                        }
                    }else {
                        System.out.println("Password entered not match, please try again~");
                    }
                }
            }else {
                System.out.println("Incorrect Password. Please retype");
            }
        }


    }

    private void transferMoney() {
        System.out.println("==== Transfer Money ====");

        if (accounts.size() < 2) {
            System.out.println("There are no other accounts in the system.");
            return;
        }

        System.out.println("Enter the card ID you want to transfer to: ");
        String cardId = sc.next();

        Account person = getAccountByCardId(cardId);
        if (person == null) {
            System.out.println("The card ID does not exist. Please try again.");
            return;
        }

        // **验证接收人姓名的首字母**
        while (true) {
            System.out.println("Please enter the first letter of the cardholder's name [*" + person.getName().substring(1) + "] :");
            char name = sc.next().charAt(0);

            if (Character.toLowerCase(person.getName().charAt(0)) == Character.toLowerCase(name)) {
                break;
            } else {
                System.out.println("Incorrect first letter. Please try again.");
            }
        }

        // **金额转账**
        while (true) {
            System.out.println("Enter the amount you want to transfer: ");
            double money = sc.nextDouble();

            if (loginAcc.getBalance() >= money) {
                person.setBalance(person.getBalance() + money);
                loginAcc.setBalance(loginAcc.getBalance() - money);
                System.out.println("Transfer to " + person.getName() + " successfully.");
                System.out.printf("Your card balance is RM%.2f%n", loginAcc.getBalance());
                return; // 转账成功后退出
            } else {
                System.out.println("Insufficient balance. Try again? (y/n)");
                char cmd = sc.next().charAt(0);
                if (cmd == 'n' || cmd == 'N') return; // 用户选择退出
            }
        }
    }


    private void withdrawMoney() {
        System.out.println("==== Withdraw Money ====");

        while (true) {
            // 1️⃣ Ask for withdrawal amount
            System.out.println("Enter the amount to withdraw: ");
            double money = sc.nextDouble();

            // 2️⃣ Check if balance is enough
            if (loginAcc.getBalance() < 100) {
                System.out.println("Your balance is less than RM100, withdrawal not allowed.");
                return;
            }

            if (loginAcc.getBalance() >= money) {
                if (money >= loginAcc.getLimit()){
                    System.out.println("Your withdraw money exceeds your transaction limit");

                }else {
                    loginAcc.setBalance(loginAcc.getBalance() - money);
                    System.out.printf("Withdrawal successful. Current Balance: RM %.2f\n", loginAcc.getBalance());
                    return; // Exit the method after successful withdrawal
                }
            } else {
                // 3️⃣ Insufficient balance, ask user if they want to retry
                System.out.println("Insufficient Balance. Would you like to try again? (y/n)");
                char command = sc.next().charAt(0);

                if (command == 'n' || command == 'N') {
                    return; // Exit the withdrawal process
                } else if (command != 'y' && command != 'Y') {
                    System.out.println("Invalid input, please enter 'y' or 'n'.");
                }
            }
        }
    }


    private void depositMoney() {
        System.out.println("====Deposit Money====");
        double balance = loginAcc.getBalance();

        System.out.println("Enter the amount of money to deposit: ");
        double moneyAdd = sc.nextDouble();
        balance += moneyAdd;
        loginAcc.setBalance(balance);
        System.out.println("Successfully deposited, your current balance is " + "RM" + loginAcc.getBalance());

    }

    private void showLoginAccount() {
        System.out.println("=======Your Account Info======");
        System.out.println("Card Number:" + loginAcc.getCardId());
        System.out.println("Name:" + loginAcc.getName());
        System.out.println("Balance:" + loginAcc.getBalance());
        System.out.println("Transaction limit:" + loginAcc.getLimit());
        System.out.println();
        System.out.println();

    }
}
