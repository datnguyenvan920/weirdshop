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
import model.Color;

/**
 *
 * @author ADMIN
 */
public class ColorDao extends BaseDao {

    public List<Color> getActiveColor(String productId) {
        List<Color> colorList = new ArrayList<>();
        String sql = "SELECT * FROM Color WHERE Status = 1 AND ProductID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Color color = new Color();
                    color.setColorID(rs.getInt("ColorID"));
                    color.setColor(rs.getString("Color"));
                    color.setStatus(rs.getInt("Status"));
                    colorList.add(color);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching active colors", e);
        }

        return colorList;
    }

    public List<Color> getAllColor(String productId) {
        List<Color> colorList = new ArrayList<>();
        String sql = "SELECT * FROM Color WHERE ProductID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Color color = new Color();
                    color.setColorID(rs.getInt("ColorID"));
                    color.setProductID(rs.getInt("ProductID"));
                    color.setColor(rs.getString("Color"));
                    color.setStatus(rs.getInt("Status"));
                    colorList.add(color);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching active colors", e);
        }

        return colorList;
    }

    public boolean addColor(Color color) {
        String sql = "INSERT INTO Color (ProductID, Color, Status) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, color.getProductID());
            ps.setString(2, color.getColor());
            ps.setInt(3, color.getStatus());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error adding color", e);
        }
    }

    public boolean editColor(Color color) {
        String sql = "UPDATE Color SET Color = ?, Status = ? WHERE ColorID = ? AND ProductID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, color.getColor());
            ps.setInt(2, color.getStatus());
            ps.setInt(3, color.getColorID());
            ps.setInt(4, color.getProductID());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error editing color", e);
        }
    }

    public boolean colorExists(String colorName) {
        String sql = "SELECT COUNT(*) FROM Color WHERE Color COLLATE SQL_Latin1_General_CP1_CI_AS = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, colorName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking color existence", e);
        }
        return false;
    }
    
    public boolean colorExistsUpdate(String colorName, int color_id) {
        String sql = "SELECT COUNT(*) FROM Color WHERE Color COLLATE SQL_Latin1_General_CP1_CI_AS = ? "
                + "AND ColorID != ? ";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, colorName);
            ps.setInt(2, color_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking color existence", e);
        }
        return false;
    }
}
