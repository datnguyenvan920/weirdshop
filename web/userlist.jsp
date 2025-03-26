<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <title>User List</title>
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
                max-width: 1000px;
                margin: auto;
                background: white;
                padding: 20px;
                border-radius: 12px;
                box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
            }
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                padding: 12px;
                text-align: center;
            }
            th {
                background-color: #f0bd56;
                color: white;
            }
            tr:nth-child(even) td {
                background-color: #ffe6b3;
            }
            .btn {
                padding: 8px 12px;
                text-decoration: none;
                color: white;
                border-radius: 5px;
                border: none;
                cursor: pointer;
            }
            .btn-add {
                background-color: #4CAF50;
            }
            .btn-update {
                background-color: #2196F3;
            }
            .btn-delete {
                background-color: #f44336;
            }
        </style>
    </head>
    <body>

        <%@ include file="sidebar.jsp" %>
        <div class="content">
            <div class="container">
                <h2>Customer List</h2>
                
                <a href="addUser.jsp" class="btn btn-add">Add New User</a>
                <br><br>
                
                <table>
                    <thead>
                        <tr>
                            <th>User ID</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Address</th>
                            <th>Mobile Number</th>
                            <th style="width:170px">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${userList}">
                            <tr>
                                <td>${user.userID}</td>
                                <td>${user.userName}</td>
                                <td>${user.email}</td>  
                                <td>
                                    <c:choose>
                                        <c:when test="${empty user.address}">Not Yet</c:when>
                                        <c:otherwise>${user.address}</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty user.mobileNumber}">Not Yet</c:when>
                                        <c:otherwise>${user.mobileNumber}</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="updateUser.jsp?userID=${user.userID}" class="btn btn-update">Update</a>
                                    <a href="deleteUser?userID=${user.userID}" class="btn btn-delete" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
