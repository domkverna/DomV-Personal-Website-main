package com.example.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Login {

    
    @PostMapping("/login")
    @ResponseBody
    public void login( @RequestParam("username") String enteredUsername, @RequestParam("password") String enteredPassword) 
    {

        // MySQL database credentials
        String url = "jdbc:mysql://localhost:3306/my_website_database";
        String username = "root";
        String password = "0826";

        // Establishing the connection
        try 
        {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the MySQL database!");

            // Perform database operations here...
            
            String query = "SELECT * FROM user_table WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) 
            {
                statement.setString(1, enteredUsername);
                statement.setString(2, enteredPassword);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) 
                {
                    System.out.println("Username: " + enteredUsername + " and password: " + enteredPassword + " exist in the database!");
                } 

                else 
                {
                    System.out.println("Username: " + enteredUsername + " and password: " + enteredPassword + " do not exist in the database!");
                }
            }

            // Closing the connection
            connection.close();
            System.out.println("Connection closed.");
        } 

        catch (SQLException e) 
        {
            System.out.println("Connection to the MySQL database failed!");
            e.printStackTrace();
        }

    }
}