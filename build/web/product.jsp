<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Product List</title>
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
                max-width: 1100px; /* Increased from 1000px */
                margin: auto;
                background: white;
                padding: 20px;
                border-radius: 12px;
                box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
            }

            .button-container {
                text-align: right;
                margin-bottom: 15px;
            }

            .button {
                background-color: #f0bd56;
                border: none;
                padding: 10px 15px;
                color: white;
                font-size: 14px;
                cursor: pointer;
                border-radius: 6px;
                text-decoration: none;
                margin: 10px;
            }

            .button:hover {
                background-color: #d48806;
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

            img {
                border-radius: 8px;
                width: 60px;
                height: 60px;
                object-fit: cover;
            }

            .action-buttons a {
                padding: 6px 10px;
                font-size: 12px;
                color: white;
                border-radius: 5px;
                text-decoration: none;
            }

            .edit {
                background-color: #007bff;
            }

            .edit:hover {
                background-color: #0056b3;
            }

            .delete {
                background-color: #dc3545;
            }

            .delete:hover {
                background-color: #a71d2a;
            }
            .variety {
                background-color: #28a745; /* Green color */
            }

            .variety:hover {
                background-color: #1e7e34;
            }
            .modal {
                display: none;
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                justify-content: center;
                align-items: center;
            }

            .modal-content {
                background: white;
                padding: 20px;
                border-radius: 10px;
                text-align: center;
                width: 30%;
                box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.3);
            }

            .delete-warning {
                color: red;
                font-weight: bold;
            }

            .close {
                float: right;
                font-size: 22px;
                cursor: pointer;
            }

            .button.cancel {
                background-color: gray;
            }

            .button.cancel:hover {
                background-color: darkgray;
            }

        </style>
    </head>
    <body>

        <%@ include file="sidebar.jsp" %> 

        <div class="content">
            <div class="container">
                <h2>Product List</h2>

                <div class="button-container">
                    <a href="<%= request.getContextPath() %>/admin/productadd" class="button">Add Product</a>
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Producer</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${productList}">
                            <tr>
                                <td>${product.productID}</td>
                                <td>${product.productName}</td>
                                <td><img src="${product.image}" alt="Product Image"></td>
                                <td>${product.producer}</td>
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

                                <td class="action-buttons" >
                                    <a href="<c:url value='/admin/productupdate'>
                                           <c:param name='id' value='${product.productID}'/>
                                           <c:param name='name' value='${product.productName}'/>
                                           <c:param name='type' value='${product.productType}'/>
                                           <c:param name='image' value='${product.image}'/>
                                           <c:param name='producer' value='${product.producer}'/>
                                           <c:param name='description' value='${product.description}'/>
                                           <c:param name='status' value='${product.status}'/>
                                       </c:url>" class="button edit">
                                        Update
                                    </a>


                                    <c:choose>
                                        <c:when test="${product.status == 1}">
                                            <a href="javascript:void(0);" class="button delete" onclick="confirmDelete('${product.productID}')">Deactivate</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascript:void(0);" class="button delete" onclick="confirmActive('${product.productID}')">Activate</a>
                                        </c:otherwise>
                                    </c:choose>
                                    <a href="${pageContext.request.contextPath}/admin/productvariantlist?id=${product.productID}" class="button variety">See Variety</a>
                                    <a href="${pageContext.request.contextPath}/admin/productattributecolor?id=${product.productID}" class="button variety">Color</a>
                                    <a href="${pageContext.request.contextPath}/admin/productattributesize?id=${product.productID}" class="button variety">Size</a>
                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Delete Confirmation Modal -->
                <div id="deleteModal" class="modal">
                    <div class="modal-content">
                        <span class="close">&times;</span>
                        <h3>Are you sure?</h3>
                        <p>Deactivate this product will also <span class="delete-warning">DEACTIVATE</span> its variants.</p>
                        <form id="deactivateForm" action="" method="post">
                            <input type="hidden" name="id" id="deleteProductId"> 
                            <button type="submit" class="button delete">Confirm</button>
                            <button type="button" class="button cancel">Cancel</button>
                        </form>

                    </div>
                </div>

                <div id="activeModal" class="modal">
                    <div class="modal-content">
                        <span class="close">&times;</span>
                        <h3>Are you sure?</h3>
                        <p>Active this product will also <span class="delete-warning">ACTIVE</span> its variants.</p>
                        <form id="activateForm" action="" method="post">
                            <input type="hidden" name="id" id="deleteProductId"> 
                            <button type="submit" class="button delete">Confirm</button>
                            <button type="button" class="button cancel">Cancel</button>
                        </form>

                    </div>
                </div>

            </div>
        </div>

        <script>
            // Get modal elements
            var modal = document.getElementById("deleteModal");
            var modal1 = document.getElementById("activeModal");
            var closeBtn = document.querySelector(".close");
            var cancelBtn = document.querySelector(".button.cancel");

// Function to open the modal and set form action
            function confirmDelete(productID) {
                var form = document.getElementById("deactivateForm");
                var productIdInput = document.getElementById("deleteProductId");

                productIdInput.value = productID;
                form.action = "<%= request.getContextPath() %>/admin/productdelete?status=0&id=" + productID;

                modal.style.display = "flex";
            }

            function confirmActive(productID) {
                var form = document.getElementById("activateForm");
                var productIdInput = document.getElementById("deleteProductId");

                productIdInput.value = productID;
                form.action = "<%= request.getContextPath() %>/admin/productdelete?status=1&id=" + productID;

                modal1.style.display = "flex";
            }

// Close modal when clicking "X" or "Cancel"
            var closeBtns = document.querySelectorAll(".close");
            closeBtns.forEach(btn => btn.onclick = () => {
                    modal.style.display = "none";
                    modal1.style.display = "none";
                });


// Close modal when clicking outside the content
            var cancelBtns = document.querySelectorAll(".button.cancel");
            cancelBtns.forEach(btn => btn.onclick = () => {
                    modal.style.display = "none";
                    modal1.style.display = "none";
                });

            window.onclick = function (event) {
                if (event.target === modal) {
                    modal.style.display = "none";
                }
                if (event.target === modal1) {
                    modal1.style.display = "none";
                }
            };

        </script>
    </body>
</html>
