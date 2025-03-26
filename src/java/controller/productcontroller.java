/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ColorDao;
import dao.ProductDao;
import dao.SizeDao;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Color;
import model.Product;

/**
 *
 * @author thang
 */
@WebServlet(name = "productcontroller", urlPatterns = {"/admin/productlist", "/admin/productadd", "/admin/productupdate", "/admin/productedits", "/admin/productdelete",
    "/admin/productvariantlist", "/admin/productaddvariant", "/admin/productupdatevariant", "/admin/productdetailedits", "/admin/productdetaildelete", "/admin/productattributecolor",
    "/admin/productattributeaddcolor", "/admin/productattributeeditcolor", "/admin/productattributeeditscolor", "/admin/productattributeeditssize"})
public class productcontroller extends HttpServlet {

    ProductDao productDAO = new ProductDao();
    ColorDao colorDao = new ColorDao();
    SizeDao sizeDao = new SizeDao();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet productcontroller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productcontroller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        List<Category> cat = productDAO.getallCategory();
        RequestDispatcher dispatcher;
        switch (url) {
            case "/admin/productlist":
                handleListProduct(request, response);
                break;
            case "/admin/productadd":
                request.setAttribute("categories", cat);
                dispatcher = request.getRequestDispatcher("/addProduct.jsp");
                dispatcher.forward(request, response);
                break;
            case "/admin/productupdate":
                String id = request.getParameter("id");
                request.setAttribute("categories", cat);
                String name = request.getParameter("name");
                String image = request.getParameter("image");
                String producer = request.getParameter("producer");
                String description = request.getParameter("description");
                String type = request.getParameter("type");
                // Set attributes to pass them back to JSP
                request.setAttribute("id", id);
                request.setAttribute("name", name);
                request.setAttribute("image", image);
                request.setAttribute("producer", producer);
                request.setAttribute("description", description);
                request.setAttribute("type", type);
                dispatcher = request.getRequestDispatcher("/addProduct.jsp");
                dispatcher.forward(request, response);
                break;
            case "/admin/productvariantlist":
                handleListProductVariety(request, response);
                break;
            case "/admin/productaddvariant":
                String ida = request.getParameter("productID");
                request.setAttribute("colorlist", colorDao.getActiveColor(ida));
                request.setAttribute("sizelist", sizeDao.getActiveSize(ida));
                request.setAttribute("productID", ida);
                dispatcher = request.getRequestDispatcher("/addproductdetail.jsp");
                dispatcher.forward(request, response);
                break;
            case "/admin/productupdatevariant":
                int detailID = Integer.parseInt(request.getParameter("id"));
                int productID = Integer.parseInt(request.getParameter("productId"));
                String colorid = request.getParameter("colorid");
                String sizeid = request.getParameter("sizeid");
                String quantity = request.getParameter("quantity");
                String price = request.getParameter("price");

                request.setAttribute("detailID", detailID);
                request.setAttribute("productID", productID);
                request.setAttribute("colorid", colorid);
                request.setAttribute("sizeid", sizeid);
                request.setAttribute("quantity", quantity);
                request.setAttribute("price", price);
                request.setAttribute("colorlist", colorDao.getActiveColor(request.getParameter("productId")));
                request.setAttribute("sizelist", sizeDao.getActiveSize(request.getParameter("productId")));
                dispatcher = request.getRequestDispatcher("/addproductdetail.jsp");
                dispatcher.forward(request, response);
                break;
            case "/admin/productattributecolor":
                handleListProductAttributeColor(request, response);
                break;
            case "/admin/productattributeeditcolor":
                String idc = request.getParameter("id");
                request.setAttribute("id", idc);
                request.setAttribute("colorid", request.getParameter("colorid"));
                request.setAttribute("color", request.getParameter("color"));
                request.setAttribute("status", request.getParameter("status"));
                request.getRequestDispatcher("/coloradd.jsp").forward(request, response);
                break;
            case "/admin/productattributeaddcolor":
                String idb = request.getParameter("id");
                request.setAttribute("id", idb);
                request.getRequestDispatcher("/coloradd.jsp").forward(request, response);
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();

        switch (url) {
            case "/admin/productedits":
                String value = request.getParameter("submit");
                if (value.equalsIgnoreCase("add")) {
                    handleAddProduct(request, response);
                } else if (value.equalsIgnoreCase("update")) {
                    handleUpdateProduct(request, response);
                } else {
                    handleListProduct(request, response);
                }
                break;
            case "/admin/productdelete":
                handleDeleteProduct(request, response);
                break;

            case "/admin/productdetailedits":
                String value1 = request.getParameter("submit");
                if (value1.equalsIgnoreCase("add")) {
                    handleAddProductVariety(request, response);
                } else if (value1.equalsIgnoreCase("update")) {
                    handleUpdateProductVariety(request, response);
                } else {
                    handleListProductVariety(request, response);
                }
                break;
            case "/admin/productdetaildelete":
                handleUpdateProductVarietyStatus(request, response);
                break;
            case "/admin/productattributeeditscolor":
                String value2 = request.getParameter("submit");
                if (value2.equalsIgnoreCase("add")) {
                    handleAddProductAttributeColor(request, response);
                } else if (value2.equalsIgnoreCase("update")) {
                    handleEditProductAttributeColor(request, response);
                } else {
                    handleListProductVariety(request, response);
                }
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private void handleListProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> productList = productDAO.getAllProduct();

        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product.jsp");
        dispatcher.forward(request, response);
    }

    private void handleAddProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String typeStr = request.getParameter("type");
        String image = request.getParameter("image");
        String producer = request.getParameter("producer");
        String description = request.getParameter("description");

        if (name == null || name.isEmpty() || typeStr == null || typeStr.isEmpty() || image == null || image.isEmpty()
                || producer == null || producer.isEmpty() || description == null || description.isEmpty()) {
            request.setAttribute("errorMessage", "Please enter all fields");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/addProduct.jsp");
            dispatcher.forward(request, response);
            return; // Stop further execution
        }

        try {
            int type = Integer.parseInt(typeStr);
            Product product = new Product();
            product.setProductName(name);
            product.setProductType(type);
            product.setDescription(description);
            product.setImage(image);
            product.setProducer(producer);
            productDAO.addProduct(product);
            handleListProduct(request, response);
            // Proceed with storing or processing the product
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid number format for price, type, or quantity.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/addProduct.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void handleDeleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productidparam = request.getParameter("id");
        String status = request.getParameter("status");
        int id = Integer.parseInt(productidparam);
        productDAO.changeStatusProduct(id, status);
        handleListProduct(request, response);
    }

    private void handleUpdateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String producer = request.getParameter("producer");
        String description = request.getParameter("description");
        String typeStr = request.getParameter("type");

        if (name == null || name.isEmpty() || typeStr == null || typeStr.isEmpty() || image == null || image.isEmpty()
                || producer == null || producer.isEmpty() || description == null || description.isEmpty()) {
            request.setAttribute("id", idStr);
            request.setAttribute("name", name);
            request.setAttribute("image", image);
            request.setAttribute("producer", producer);
            request.setAttribute("description", description);
            request.setAttribute("type", typeStr);
            request.setAttribute("errorMessage", "Please enter all fields");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/addProduct.jsp");
            dispatcher.forward(request, response);
            return; // Stop further execution
        }

        try {
            int type = Integer.parseInt(typeStr);
            int id = Integer.parseInt(idStr);
            Product product = new Product();
            product.setProductID(id);
            product.setProductName(name);
            product.setProductType(type);
            product.setDescription(description);
            product.setImage(image);
            product.setProducer(producer);
            productDAO.updateProduct(product);
            handleListProduct(request, response);
            // Proceed with storing or processing the product
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid number format for price, type, or quantity.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/addProduct.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void handleListProductVariety(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            List<Product> productList = productDAO.getAllProductDetail(id);

            request.setAttribute("productList", productList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/productdetail.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {

        }

    }

    private void handleAddProductVariety(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productID = request.getParameter("productID");
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        String quantityStr = request.getParameter("quantity");
        String priceStr = request.getParameter("price");

        if (productID == null || productID.isEmpty()
                || color == null || color.isEmpty()
                || size == null || size.isEmpty()
                || quantityStr == null || quantityStr.isEmpty()
                || priceStr == null || priceStr.isEmpty()) {
            request.setAttribute("errorMessage", "Please fill in all fields.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/addproductdetail.jsp");
            dispatcher.forward(request, response);
            return; // Stop further execution
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            float price = Float.parseFloat(priceStr);
            int id = Integer.parseInt(productID);

            Product p = new Product();
            p.setProductID(id);
            p.setColor(color);
            p.setPrice(price);
            p.setSize(size);
            p.setQuantity(quantity);

            productDAO.addProductdetail(p);
            request.setAttribute("id", id);
            response.sendRedirect(request.getContextPath() + "/admin/productvariantlist?id=" + id);
        } catch (NumberFormatException e) {
            request.setAttribute("productID", productID);
            request.setAttribute("color", color);
            request.setAttribute("size", size);
            request.setAttribute("quantity", quantityStr);
            request.setAttribute("price", priceStr);
            request.setAttribute("errorMessage", "Something is wrong.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/addproductdetail.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void handleUpdateProductVarietyStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productidparam = request.getParameter("id");
        String statusParam = request.getParameter("status");
        int id = Integer.parseInt(productidparam);
        int status = Integer.parseInt(statusParam);
        productDAO.updateProductDetailStatus(id, status);
        handleListProduct(request, response);
    }

    private void handleUpdateProductVariety(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the form
        String detailIdStr = request.getParameter("detailID");
        String productIdStr = request.getParameter("id");
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        String quantityStr = request.getParameter("quantity");
        String priceStr = request.getParameter("price");

        // Check if any field is null or empty
        if (detailIdStr == null || detailIdStr.trim().isEmpty()
                || productIdStr == null || productIdStr.trim().isEmpty()
                || color == null || color.trim().isEmpty()
                || size == null || size.trim().isEmpty()
                || quantityStr == null || quantityStr.trim().isEmpty()
                || priceStr == null || priceStr.trim().isEmpty()) {
            request.setAttribute("detailID", detailIdStr);
            request.setAttribute("productID", productIdStr);
            request.setAttribute("color", color);
            request.setAttribute("size", size);
            request.setAttribute("quantity", quantityStr);
            request.setAttribute("price", priceStr);

            request.setAttribute("errorMessage", "Please fill in all fields.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/addproductdetail.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {

            int productID = Integer.parseInt(productIdStr);
            int detailID = Integer.parseInt(detailIdStr);
            float price = Float.parseFloat(priceStr);
            int quantity = Integer.parseInt(quantityStr);
            int colorid = Integer.parseInt(color);
            int sizeid = Integer.parseInt(size);

            Product productDetail = new Product();
            productDetail.setColorID(colorid);
            productDetail.setProductID(productID);
            productDetail.setPrice(price);
            productDetail.setQuantity(quantity);
            productDetail.setDetailID(detailID);
            productDetail.setSizeID(sizeid);
            productDAO.updateProductDetail(productDetail);
            request.setAttribute("id", productID);
            response.sendRedirect(request.getContextPath() + "/admin/productvariantlist?id=" + productID);

        } catch (Exception e) {

        }
        // Create a ProductDetail object

        // Call DAO to update product detail
    }

    private void handleListProductAttributeColor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("id");
        List<Color> colorlist = colorDao.getAllColor(productId);
        request.setAttribute("colorlist", colorlist);
        request.setAttribute("id", productId);
        request.getRequestDispatcher("/productattributecolor.jsp").forward(request, response);
    }

    private void handleAddProductAttributeColor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdparam = request.getParameter("id");
        String colorname = request.getParameter("color");
        String statusparam = request.getParameter("status");

        try {
            System.out.println(productIdparam);
            int productId = Integer.parseInt(productIdparam);
            int status = Integer.parseInt(statusparam);
            if (status == -1 || colorname.trim().isEmpty() || colorname.trim().isBlank()) {
                request.setAttribute("id", productIdparam);
                request.setAttribute("errorMessage", "Please input all the information");
                request.getRequestDispatcher("/coloradd.jsp").forward(request, response);
                return;
            }
            if(colorDao.colorExists(colorname)){
                request.setAttribute("id", productIdparam);
                request.setAttribute("errorMessage", "Color already exist");
                request.getRequestDispatcher("/coloradd.jsp").forward(request, response);
                return;
            }

            Color c = new Color();
            c.setColor(colorname);
            c.setProductID(productId);
            c.setStatus(status);
            colorDao.addColor(c);
            handleListProductAttributeColor(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("id", productIdparam);
            request.setAttribute("errorMessage", "Please input all the information");
            request.getRequestDispatcher("/coloradd.jsp").forward(request, response);

        }
    }

    private void handleEditProductAttributeColor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdparam = request.getParameter("id");
        String colorIdparam = request.getParameter("colorid");
        String colorname = request.getParameter("color");
        String statusparam = request.getParameter("status");
        try {
            int productId = Integer.parseInt(productIdparam);
            int status = Integer.parseInt(statusparam);
            int colorId = Integer.parseInt(colorIdparam);
            
            if (status == -1 || colorname.trim().isEmpty() || colorname.trim().isBlank()) {
                request.setAttribute("id", productIdparam);
                request.setAttribute("colorid", colorId);
                request.setAttribute("color", colorname);
                request.setAttribute("status", statusparam);
                request.setAttribute("errorMessage", "Please input all the information");
                request.getRequestDispatcher("/coloradd.jsp").forward(request, response);
                return;
            }
            
            if(colorDao.colorExistsUpdate(colorname, colorId)){
                request.setAttribute("id", productIdparam);
                request.setAttribute("colorid", colorId);
                request.setAttribute("color", colorname);
                request.setAttribute("status", statusparam);
                request.setAttribute("errorMessage", "Color already exist");
                request.getRequestDispatcher("/coloradd.jsp").forward(request, response);
                return;
            }
            
            Color c = new Color();
            c.setColor(colorname);
            c.setColorID(colorId);
            c.setProductID(productId);
            c.setStatus(status);
            
            colorDao.editColor(c);
            
            handleListProductAttributeColor(request, response);
            
        } catch (NumberFormatException e) {
            handleListProductAttributeColor(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
