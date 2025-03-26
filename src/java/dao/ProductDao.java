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
import model.Category;
import model.Product;

/**
 *
 * @author thang
 */
public class ProductDao extends BaseDao {

    public ArrayList<Product> getNewProduct() {
        ArrayList<Product> customers = new ArrayList<>();
        String sql = "SELECT p.ProductID, p.ProductName, p.ProductType, p.Image, p.Producer, "
                + " MIN(pd.Price) AS minPrice, MAX(pd.Price) AS maxPrice "
                + " FROM Product p "
                + " LEFT JOIN ProductDetail pd ON p.ProductID = pd.ProductID WHERE Status = 1 "
                + " GROUP BY p.ProductID, p.ProductName, p.ProductType, p.Image, p.Producer";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Float minPrice = rs.getObject("minPrice") != null ? rs.getFloat("minPrice") : null;
                Float maxPrice = rs.getObject("maxPrice") != null ? rs.getFloat("maxPrice") : null;

                // Only add the product if both minPrice and maxPrice are NOT null
                if (minPrice != null && maxPrice != null) {
                    Product product = new Product();
                    product.setProductID(rs.getInt("ProductID"));
                    product.setProductName(rs.getString("ProductName"));
                    product.setProductType(rs.getInt("ProductType"));
                    product.setImage(rs.getString("Image"));
                    product.setProducer(rs.getString("Producer"));
                    product.setMinPrice(minPrice);
                    product.setMaxPrice(maxPrice);
                    customers.add(product);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return customers;
    }

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * "
                + " FROM Product p  ";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductType(rs.getInt("ProductType"));
                product.setImage(rs.getString("Image"));
                product.setProducer(rs.getString("Producer"));
                product.setDescription(rs.getString("Description"));
                product.setStatus(rs.getInt("Status"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving products", e);
        }
        return products;
    }

    public boolean addProduct(Product product) {
        String sql = "INSERT INTO Product (ProductName, ProductType, Image, Producer, Description) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getProductType());
            ps.setString(3, product.getImage());
            ps.setString(4, product.getProducer());
            ps.setString(5, product.getDescription());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int productId = generatedKeys.getInt(1);
                    product.setProductID(productId); // Set the generated ID to the product object
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean addProductdetail(Product product) {
        String sql = "INSERT INTO ProductDetail (ProductID, ColorID, SizeID, Quantity, Price) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, product.getProductID());
            ps.setString(2, product.getColor());
            ps.setString(3, product.getSize());
            ps.setInt(4, product.getQuantity());
            ps.setFloat(5, product.getPrice());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int productId = generatedKeys.getInt(1);
                    product.setProductID(productId); // Set the generated ID to the product object
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public List<Category> getallCategory() {
        List<Category> products = new ArrayList<>();
        String sql = "SELECT * "
                + " FROM Category ";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category product = new Category();
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setCategoryName(rs.getString("CategoryName"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving products", e);
        }
        return products;
    }

    public boolean changeStatusProduct(int productID, String status) {
        String sql = "UPDATE Product SET Status = ? WHERE ProductID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, productID);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Returns true if at least one row was updated
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product status", e);
        }
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE Product SET ProductName = ?, ProductType = ?, Image = ?, Producer = ?, Description = ? WHERE ProductID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getProductType());
            ps.setString(3, product.getImage());
            ps.setString(4, product.getProducer());
            ps.setString(5, product.getDescription());
            ps.setInt(6, product.getProductID());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Returns true if at least one row was updated
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product", e);
        }
    }

    public ArrayList<Product> getAllProductDetail(int productId) {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT d.DetailID, d.ProductID, c.Color AS colorname, s.Size AS sizename, d.Quantity, d.Price, d.ColorID, d.SizeID, d.Status "
                + "FROM ProductDetail d "
                + "LEFT JOIN Product p ON p.ProductID = d.ProductID "
                + "LEFT JOIN Color c ON d.ColorID = c.ColorID "
                + "LEFT JOIN Size s ON d.SizeID = s.SizeID "
                + "WHERE p.ProductID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setDetailID(rs.getInt("DetailID"));
                    product.setProductID(rs.getInt("ProductID"));
                    product.setColor(rs.getString("colorname"));
                    product.setSize(rs.getString("sizename"));
                    product.setColorID(rs.getInt("ColorID"));
                    product.setSizeID(rs.getInt("SizeID"));
                    product.setQuantity(rs.getInt("Quantity"));
                    product.setPrice(rs.getFloat("Price"));
                    product.setStatus(rs.getInt("Status"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving product details", e);
        }
        return products;
    }

    public boolean updateProductDetailStatus(int detailId, int status) {
        String sql = "UPDATE ProductDetail SET Status = ? WHERE DetailID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, status);
            ps.setInt(2, detailId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product detail status for DetailID: " + detailId, e);
        }
    }

    public boolean updateProductDetail(Product productDetail) {
        String sql = "UPDATE ProductDetail SET ColorID = ?, SizeID = ?, Quantity = ?, Price = ? WHERE DetailID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productDetail.getColorID());
            ps.setInt(2, productDetail.getSizeID());
            ps.setInt(3, productDetail.getQuantity());
            ps.setDouble(4, productDetail.getPrice());
            ps.setInt(5, productDetail.getDetailID());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product detail with DetailID: " + productDetail.getDetailID(), e);
        }
    }

}
