/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Size;

/**
 *
 * @author ADMIN
 */
public class SizeDao extends BaseDao {

    public List<Size> getActiveSize(String productId) {
        List<Size> sizeList = new ArrayList<>();
        String sql = "SELECT * FROM Size WHERE Status = 1 AND ProductID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Size size = new Size();
                    size.setSizeID(rs.getInt("SizeID"));
                    size.setProductID(rs.getInt("ProductID"));
                    size.setSize(rs.getString("Size"));
                    size.setStatus(rs.getInt("Status"));
                    sizeList.add(size);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching active sizes", e);
        }

        return sizeList;
    }

    public List<Size> getAllSize(String productId) {
        List<Size> sizeList = new ArrayList<>();
        String sql = "SELECT * FROM Size WHERE ProductID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Size size = new Size();
                    size.setSizeID(rs.getInt("SizeID"));
                    size.setProductID(rs.getInt("ProductID"));
                    size.setSize(rs.getString("Size"));
                    size.setStatus(rs.getInt("Status"));
                    sizeList.add(size);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching active sizes", e);
        }

        return sizeList;
    }
}
