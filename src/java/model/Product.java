/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thang
 */
public class Product {
    private int ProductID;
    private String ProductName;
    private int ProductType;
    private String Color;
    private String Size;
    private int ColorID;
    private int SizeID;
    private String Producer;
    private String Description;
    private int DetailID;
    private int Quantity;
    private float minPrice;
    private float maxPrice;
    private float Price;
    private String Image;
    private int Status;

    public Product(int ProductID, String ProductName, int ProductType, String Color, String Size, String Producer, String Description, int Quantity, float minPrice, float maxPrice, String Image) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductType = ProductType;
        this.Color = Color;
        this.Size = Size;
        this.Producer = Producer;
        this.Description = Description;
        this.Quantity = Quantity;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.Image = Image;
    }

    public Product() {
    }
    
    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getProductType() {
        return ProductType;
    }

    public void setProductType(int ProductType) {
        this.ProductType = ProductType;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public String getProducer() {
        return Producer;
    }

    public void setProducer(String Producer) {
        this.Producer = Producer;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public int getDetailID() {
        return DetailID;
    }

    public void setDetailID(int DetailID) {
        this.DetailID = DetailID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getColorID() {
        return ColorID;
    }

    public void setColorID(int ColorID) {
        this.ColorID = ColorID;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }
    
    
}
