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
            .btn-delete {
                background-color: #d9534f;
            }
            .modal {
                display: none; /* Hide the modal by default */
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent background */
                justify-content: center;
                align-items: center;
            }

            .modal-content {
                background: white;
                padding: 20px;
                border-radius: 8px;
                text-align: center;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            }

        </style>
    </head>
    <body>
        <%@ include file="sidebar.jsp" %> 

        <div class="content">
            <div class="container">
                <h2>Product Details</h2>


                <table>
                    <thead>
                        <tr>
                            <th>Detail ID</th>
                            <th>Product ID</th>
                            <th>Color</th>
                            <th>Size</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${productList}" varStatus="status">
                            <tr>
                                <td>${not empty product.detailID ? product.detailID : 'N/A'}</td>
                                <td>${product.productID}</td>
                                <td>${product.color}</td>
                                <td>${product.size}</td>
                                <td>${product.quantity}</td>
                                <td>${product.price}</td>
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
                                    <c:if test="${not empty product.detailID}">
                                        <a href="<c:url value='/admin/productupdatevariant?id=${product.detailID}&productId=${product.productID}&colorid=${product.colorID}&sizeid=${product.sizeID}&quantity=${product.quantity}&price=${product.price}'/>" class="button btn-update">Update</a>
                                        <c:if test="${product.status == 1}">
                                        <a href="javascript:void(0);" class="button delete" onclick="confirmDelete('${product.detailID}')">Deactivate</a>
                                        </c:if>
                                        <c:if test="${product.status == 0}">
                                        <a href="javascript:void(0);" class="button activate" onclick="confirmActivate('${product.detailID}')">Activate</a>
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="buttons">
                    <a href="<c:url value='/admin/productaddvariant?productID=${not empty productList ? productList[0].productID : param.id}'/>" class="button">Add New Product Variant</a>
                </div>
                <!-- Delete Confirmation Modal -->
                <div id="deleteModal" class="modal">
                    <div class="modal-content">
                        <span class="close">&times;</span>
                        <h3>Are you sure?</h3>
                        <p><span class="delete-warning">DEACTIVATE</span> product variants will be hidden.</p>
                        <form id="deleteForm" action="" method="post">
                            <input type="hidden" name="id" id="deleteProductId"> 
                            <button type="submit" class="button delete">Confirm Delete</button>
                            <button type="button" class="button cancel">Cancel</button>
                        </form>

                    </div>
                </div>

                <div id="activateModal" class="modal">
                    <div class="modal-content">
                        <span class="close">&times;</span>
                        <h3>Are you sure?</h3>
                        <p><span class="activate-warning">ACTIVATE</span> product variants will be restored.</p>
                        <form id="activateForm" action="" method="post">
                            <input type="hidden" name="id" id="activateProductId">
                            <button type="submit" class="button activate">Confirm Activate</button>
                            <button type="button" class="button cancel">Cancel</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>

        <script>
// Get modal elements
            var modal = document.getElementById("deleteModal");
            var closeBtn = document.querySelector(".close");
            var cancelBtn = document.querySelector(".button.cancel");

// Function to open the modal and set form action
            function confirmDelete(detailID) {
                var form = document.getElementById("deleteForm");
                var productIdInput = document.getElementById("deleteProductId");

// Set hidden input value
                productIdInput.value = detailID;
// Set form action with detail ID in query param
                form.action = "<%= request.getContextPath() %>/admin/productdetaildelete?status=0&id=" + detailID;

// Show modal
                modal.style.display = "flex";
            }

// Close modal when clicking "X" or "Cancel"
            closeBtn.onclick = cancelBtn.onclick = function () {
                modal.style.display = "none";
            };

// Close modal when clicking outside the content
            window.onclick = function (event) {
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            };
        </script>

        <script>
// Get modal elements
            var activateModal = document.getElementById("activateModal");
            var closeActivateBtn = activateModal.querySelector(".close");
            var cancelActivateBtn = activateModal.querySelector(".button.cancel");

// Function to open the activation modal and set form action
            function confirmActivate(detailID) {
                var form = document.getElementById("activateForm");
                var productIdInput = document.getElementById("activateProductId");

                // Set hidden input value
                productIdInput.value = detailID;
                // Set form action with detail ID in query param
                form.action = "<%= request.getContextPath() %>/admin/productdetaildelete?status=1&id=" + detailID;

                // Show modal
                activateModal.style.display = "flex";
            }

// Close modal when clicking "X" or "Cancel"
            closeActivateBtn.onclick = cancelActivateBtn.onclick = function () {
                activateModal.style.display = "none";
            };

// Close modal when clicking outside the content
            window.onclick = function (event) {
                if (event.target === activateModal) {
                    activateModal.style.display = "none";
                }
            };
        </script>
    </body>
</html>