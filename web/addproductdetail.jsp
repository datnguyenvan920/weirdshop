<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title><c:if test="${empty detailID}">Add</c:if><c:if test="${not empty detailID}">Edit</c:if> Product Variant</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #fffaf0;
                    text-align: center;
                }
                .container {
                    width: 50%;
                    margin: auto;
                    background: white;
                    padding: 20px;
                    border-radius: 12px;
                    box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
                }
                input, select {
                    width: 90%;
                    padding: 10px;
                    margin: 8px 0;
                    border: 1px solid #ccc;
                    border-radius: 6px;
                }
                .button {
                    background-color: #f0bd56;
                    border: none;
                    padding: 10px 15px;
                    color: white;
                    cursor: pointer;
                    border-radius: 6px;
                }
                .button:hover {
                    background-color: #d48806;
                }
                .error-message {
                    color: red;
                    font-weight: bold;
                    margin-bottom: 10px;
                }
            </style>
        </head>
        <body>
        <%@ include file="sidebar.jsp" %> 
        <div class="container">
            <c:if test="${not empty detailID}">
                <h2>Edit Product Variant</h2>
            </c:if>
            <c:if test="${empty detailID}">
                <h2>Add Product Variant</h2>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/admin/productdetailedits" method="post">
                <c:if test="${not empty detailID}">
                    <input type="text" name="detailID" value="${detailID}" placeholder="Detail ID" readonly>
                </c:if>
                <input type="text" name="id" value="${productID}" placeholder="Product ID" readonly>

                <select name="color">
                    <option value="" disabled selected>Select Color</option>
                    <c:forEach var="color" items="${colorlist}">
                        <option value="${color.colorID}" ${color.colorID == colorid ? 'selected' : ''}>${color.color}</option>
                    </c:forEach>
                </select>

                <select name="size">
                    <option value="" disabled selected>Select Size</option>
                    <c:forEach var="size" items="${sizelist}">
                        <option value="${size.sizeID}" ${size.sizeID == sizeid ? 'selected' : ''}>${size.size}</option>
                    </c:forEach>
                </select>
                
                <input type="number" name="quantity" value="${quantity}" placeholder="Quantity">
                <input type="text" name="price" value="${price}" placeholder="Price">

                <c:if test="${empty detailID}">
                    <button type="submit" class="button" name="submit" value="add">Add Product Variant</button>
                </c:if>
                <c:if test="${not empty detailID}">
                    <button type="submit" class="button" name="submit" value="update">Update Product Variant</button>
                </c:if>
                <button type="submit" class="button" name="submit" value="cancel">Cancel</button>
            </form>
        </div>
    </body>
</html>
