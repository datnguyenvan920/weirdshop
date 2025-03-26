<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Product</title>
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
            <c:if test="${not empty id}">
                <h2>Update Product</h2>
            </c:if>
            <c:if test="${empty id}">
                <h2>Add Product</h2>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/admin/productedits" method="post">
                <c:if test="${not empty id}">
                <input type="text" value="${id}" name="id" placeholder="ProductId" readonly>
                </c:if>
                <input type="text" value="${name}" name="name" placeholder="Product Name" >
                <input type="text" value="${image}" name="image" placeholder="Image URL">
                <input type="text" value="${producer}" name="producer" placeholder="Producer" >
                <input type="text" value="${description}" name="description" placeholder="Descriptions" >
                <select name="type">
                    <option value="">Select Category</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.categoryID}" 
                                <c:if test="${category.categoryID == type}">selected</c:if>>
                            ${category.categoryName}
                        </option>
                    </c:forEach>
                </select>

                <c:if test="${empty id}">
                    <button type="submit" class="button" name="submit" value="add">Add Product</button>
                </c:if>
                <c:if test="${not empty id}">
                    <button type="submit" class="button" name="submit" value="update">Update Product</button>
                </c:if>
                <button type="submit" class="button" name="submit" value="cancel">Cancel</button>

            </form>
        </div>
    </body>
</html>
