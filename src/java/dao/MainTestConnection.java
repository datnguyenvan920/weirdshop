package dao;

import java.sql.Connection;
public class MainTestConnection {


    public static void main(String[] args) {
        MainTestConnection testConnection = new MainTestConnection();
        
        if (testConnection.getConnection() != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Failed to establish connection.");
        }
        
        // Close the connection after testing
        testConnection.closeConnection();
    }

    // Add the following methods to match your existing BaseDal methods
    public Connection getConnection() {
        BaseDao baseDal = new BaseDao() {}; // Anonymous class to create an instance
        return baseDal.getConnection();
    }

    public void closeConnection() {
        BaseDao baseDal = new BaseDao() {};
        baseDal.closeConnection();
    }
}
