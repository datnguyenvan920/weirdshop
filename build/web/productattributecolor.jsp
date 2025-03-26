<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Product Details</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #fffaf0;
                margin: 0;
                padding: 0;
                display: flex;
            }
            .content {
                margin-left: 250px;
                padding: 20px;
                flex-grow: 1;
            }
            h2 {
                color: #d48806;
                text-align: center;
            }
            .container {
                max-width: 1100px;
                margin: auto;
                background: white;
                padding: 20px;
                border-radius: 12px;
                box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
                margin-bottom: 20px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                border-radius: 12px;
                overflow: hidden;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            }
            th, td {
                padding: 12px;
                text-align: center;
            }
            th {
                background-color: #f0bd56;
                color: white;
                font-weight: bold;
            }
            td {
                background-color: #fff;
            }
            tr:nth-child(even) td {
                background-color: #ffe6b3;
            }
            .buttons {
                margin-top: 15px;
                text-align: center;
            }
            .button {
                display: inline-block;
                padding: 10px 20px;
                margin: 5px;
                color: white;
                background-color: #f0bd56;
                border-radius: 5px;
                text-decoration: none;
                font-weight: bold;
                transition: background 0.3s;
            }
            .button:hover {
                background-color: #d48806;
            }
            .btn-update {
                background-color: #4CAF50;
            }
        </style>
    </head>
    <body>
        <%@ include file="sidebar.jsp" %> 

        <div class="content">
            <div class="container">
                <h2>Product Attribute</h2>
                <h5>This is Attribute table of Product: ${id}</h5>
                <table>
                    <thead>
                        <tr>
                            <th>ColorID</th>
                            <th>Color</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${colorlist}" varStatus="status">
                            <tr>
                                <td>${product.colorID}</td>
                                <td>${product.color}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${product.status == 1}">
                                            Active
                                        </c:when>
                                        <c:otherwise>
                                            Inactive
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:if test="${not empty product.colorID}">
                                        <a href="<c:url value='/admin/productattributeeditcolor?id=${id}&colorid=${product.colorID}&color=${product.color}&status=${product.status}'/>" class="button btn-update">Update</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="buttons">
                    <a href="<c:url value='/admin/productattributeaddcolor?id=${id}'/>" class="button">Add New Product Attribute</a>
                </div>`

            </div>

        </div>
    </body>
</html>