/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author thang
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.User;

public class LoginDao extends BaseDao {

    public ArrayList<User> getAll() {
        ArrayList<User> customers = new ArrayList<>();
        String sql = "SELECT *"
                + "FROM [Users]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User customer = new User();
                customer.setUserID(rs.getInt("UserID"));
                customer.setEmail(rs.getString("Email"));
                customer.setRole(rs.getInt("Role"));
                customer.setUserName(rs.getString("UserName"));
                customer.setPassword(rs.getString("Password"));
                customers.add(customer);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return customers;

    }

    public ArrayList<User> getUserEmail() {
        ArrayList<User> customers = new ArrayList();
        String sql = "SELECT *"
                + " FROM [Users]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User customer = new User();
                customer.setEmail(rs.getString("Email"));
                customer.setUserID(rs.getInt("UserID"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("e");
        }
        return customers;
    }

    public ArrayList<User> getUserPassword() {
        ArrayList<User> customers = new ArrayList();
        String sql = "SELECT *"
                + "FROM [Users]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User customer = new User();
                customer.setPassword(rs.getString("Password"));
                customer.setUserID(rs.getInt("UserID"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("e");
        }
        return customers;
    }

    public int registerUser(String username, String email, String password) {
        String sql = "INSERT INTO [Users] (UserName, Email, Password, Role) VALUES (?, ?, ?, ?)";
        int result = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, 2);

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

            // Retrieve the generated department_id if needed
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated department_id
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int findLatestUser() {
        int latestUserId = -1; // Default to -1 in case no users are found
        String sql = "SELECT MAX(UserID) AS LatestUserID FROM [Users]";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                latestUserId = rs.getInt("LatestUserID");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // Print full error message
            e.printStackTrace();
        }

        return latestUserId; // Return the highest UserID
    }

    public User findUserbyId(int userId) {
        User customer = null;
        String sql = "SELECT * FROM [Users] WHERE UserID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);  // Set the userId parameter
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                customer = new User();
                customer.setUserID(rs.getInt("UserID"));
                customer.setEmail(rs.getString("Email"));
                customer.setAddress(rs.getString("Address"));
                customer.setUserName(rs.getString("UserName"));
                customer.setMobileNumber(rs.getString("MobileNumber"));
                customer.setRole(rs.getInt("Role"));
                customer.setStatus(rs.getInt("Status"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

}
