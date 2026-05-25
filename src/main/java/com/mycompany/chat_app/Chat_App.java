package com.mycompany.chat_app;

import java.util.Scanner;

public class Chat_App {
    String storedUsername;
    String storedPassword;
    String firstName;
    String lastName;
    
// Username check
    boolean checkUsername (String username){
        return username.contains("_") && username.length() <=5;
    }
    
    // Password check
    boolean checkPasswordComplexity (String Password){
    if (Password.length() >=8 && Password.matches(".*\\d.*") && Password.matches(".*[A-Z].*") && Password.matches(".*[a-zA-Z0-9].*")){
        return true;
    }else{
        System.out.println("Password must be at least 8 characters and include a capital letter, number, and contain a special character");
    }
        return false;
    }



    // Phone number check
    boolean checkCellPhoneNumber (String PhoneNumber){
        return PhoneNumber.matches("^\\+27[0-9]{9}$");
    }

    //Register user
    String registerUser (String username, String password, String PhoneNumber){
        if (!checkUsername (username)){
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 character is length";
        }
        if (!checkPasswordComplexity (password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special";
        }
        if (!checkCellPhoneNumber (PhoneNumber)){
            return "Cell phone number incorrectly formatted or does not contain international code";
        }
        storedUsername = username;
        storedPassword = password;
        return "User successfully registered.";
        }

    // Login check
    boolean loginUser(String username, String password){
        return username.matches(storedUsername) && password.matches(storedPassword);
    }

    // Login status message
    String returnLoginStatus(boolean loginStatus){
        if (loginStatus){
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        }else {
            return "Username or password incorrect, plrase try again.";
        }
    }
            
    // Main Method
     public static void main(String[] args){
         Scanner input = new Scanner(System.in);
         Chat_App app = new Chat_App();
         
        //Get user details
        System.out.println("Enter first name: ");
        app.firstName = input.nextLine();
        System.out.println("Enter LastName: ");
        app.lastName = input.nextLine();
        System.out.println("Enter username: ");
        String username = input.nextLine();
        System.out.println("Enter password: ");
        String password = input.nextLine();
        System.out.println("Enter phone number (+27...): ");
        String phone = input.nextLine();
        
        //Register
        String registerMessage = app.registerUser(username, password, phone);
        System.out.println(registerMessage);
        
        // Login
            System.out.println("\n=== LOGIN ===");
            System.out.println("Enter username: ");
            String loginUser = input.nextLine();
            System.out.println("Enter password: ");
            String loginPass = input.nextLine();
        boolean login = app.loginUser(loginUser, loginPass);
            System.out.println(app.returnLoginStatus(login));
            
     }
}
