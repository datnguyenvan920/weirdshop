/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author thang
 */
public class UserDao extends BaseDao {
    public ArrayList<User> getAllUser() {
    ArrayList<User> customers = new ArrayList<>();
    String sql = "SELECT u.*, r.RoleName as rolename "
            + " FROM Users u "
            + " LEFT JOIN Role r ON u.Role = r.RoleID "
            + " WHERE u.Role != 1"
            ;
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
                User product = new User();
                product.setUserID(rs.getInt("UserID"));
                product.setUserName(rs.getString("UserName"));
                product.setEmail(rs.getString("Email"));
                product.setRole(rs.getInt("Role"));
                product.setRoleName(rs.getString("rolename"));
                product.setAddress(rs.getString("Address"));
                product.setMobileNumber(rs.getString("MobileNumber"));
                customers.add(product);
            }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return customers;
}
}
