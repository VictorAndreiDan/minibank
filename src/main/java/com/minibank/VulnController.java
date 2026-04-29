package com.minibank;

import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class VulnController {

    // VULNERABILITY 1: SQL Injection (OWASP A03:2021 - Injection)
    // VULNERABILITY 1 FIXED: Using Prepared Statements + Try-With-Resources
    public void getUserData(Connection dbConnection, String username) throws Exception {
        
        String query = "SELECT * FROM accounts WHERE username = ?";
        
        // The try-with-resources block automatically closes the statement when done
        try (PreparedStatement pstmt = dbConnection.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.executeQuery();
            
        } // <--- pstmt is automatically closed right here
    }

    // VULNERABILITY 2: Command Injection (OWASP A03:2021 - Injection)
    public String pingServer(String ipAddress) throws Exception {
        // BAD: Passing unsanitized user input directly to the operating system
        Process process = Runtime.getRuntime().exec("ping -c 4 " + ipAddress);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return reader.readLine(); 
    }

    // VULNERABILITY 3: Reflected Cross-Site Scripting / XSS (OWASP A03:2021 - Injection)
    public String generateWelcomePage(String nameInput) {
        // BAD: Returning user input directly to the browser without HTML encoding
        return "<html><body><h1>Welcome back to Mini-Bank, " + nameInput + "!</h1></body></html>";
    }
}