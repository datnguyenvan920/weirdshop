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
            <c:if test="${not empty colorid}">
                <h2>Update Color</h2>
            </c:if>
            <c:if test="${empty colorid}">
                <h2>Add Color</h2>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/admin/productattributeeditscolor" method="post">
                <input type="hidden" value="${id}" name="id" readonly="">
                <c:if test="${not empty colorid}">
                    <input type="text" value="${colorid}" name="colorid" placeholder="ProductId" readonly>
                </c:if>
                <input type="text" value="${color}" name="color" placeholder="Color Name" >
                <select name="status">
                    <option value="-1">Select Status</option>
                    <option value="1" <c:if test="${status == 1}">selected</c:if>>Active</option>
                    <option value="0" <c:if test="${status == 0}">selected</c:if>>Inactive</option>
                    </select>



                <c:if test="${empty colorid}">
                    <button type="submit" class="button" name="submit" value="add">Add Product</button>
                </c:if>
                <c:if test="${not empty colorid}">
                    <button type="submit" class="button" name="submit" value="update">Update Product</button>
                </c:if>
                <button type="submit" class="button" name="submit" value="cancel">Cancel</button>

            </form>
        </div>
    </body>
</html>
