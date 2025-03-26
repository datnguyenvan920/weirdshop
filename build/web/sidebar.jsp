<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="sidebar">
    <h2>Dashboard</h2>
    <ul>
        <li><a href="<%= request.getContextPath() %>/admin/productlist">🛒 Products</a></li>
        <li><a href="orders.jsp">📦 Orders</a></li>
        <li><a href="<%= request.getContextPath() %>/admin/userlist">👤 Customers</a></li>
        <li><a href="settings.jsp">⚙️ Settings</a></li>
        <li><a href="<%= request.getContextPath() %>/home">🏠 ️Home</a></li>
    </ul>
</div>

<style>
    .sidebar {
        width: 250px;
        height: 100vh;
        background-color: #f0bd56;
        position: fixed;
        top: 0;
        left: 0;
        padding: 20px;
        box-shadow: 2px 0 10px rgba(0, 0, 0, 0.2);
    }

    .sidebar h2 {
        color: white;
        text-align: center;
        margin-bottom: 20px;
    }

    .sidebar ul {
        list-style: none;
        padding: 0;
    }

    .sidebar ul li {
        margin: 15px 0;
    }

    .sidebar ul li a {
        text-decoration: none;
        color: white;
        font-size: 18px;
        display: block;
        padding: 10px;
        border-radius: 5px;
        transition: background 0.3s;
    }

    .sidebar ul li a:hover {
        background: #d48806;
    }
</style>
