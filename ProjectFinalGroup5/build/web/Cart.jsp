

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Cart" %>
<%@page import="Model.Product" %>
<%@page import="DAO.ProductDAO"%>
<%@page import="DAO.CartDAO"%>
<%@page import="java.util.List" %>@
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>

    <body onload="loadTotalMoney()">
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">

                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                            <c:if test="${error!=null }">
                                            <div class="alert alert-danger" role="alert">
                                                ${error}
                                            </div>
                                        </c:if>
                                        <c:if test="${mess!=null }">
                                            <div class="alert alert-success" role="alert">
                                                ${mess}
                                            </div>
                                        </c:if>
                                        <tr>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="p-2 px-3 text-uppercase">Product's name</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Unit price</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Color</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Delivery</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Size </div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Quantity</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Delete</div>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listCart}" var="o">
                                                <c:forEach items="${listProduct}" var="p">
                                                    <c:if test="${o.productID == p.id}">
                                                        <tr>
                                                            <th scope="row">
                                                                <div class="p-2">

                                                                    <img src="${p.image}" alt="" width="70" class="img-fluid rounded shadow-sm">

                                                                    <div class="ml-3 d-inline-block align-middle">
                                                                        <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${p.name}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                                    </div>
                                                                </div>
                                                            </th>
                                                            <td class="align-middle"><strong>${p.price}$</strong></td>
                                                            <td class="align-middle"><strong>${p.color}</strong></td>
                                                            <td class="align-middle"><strong>${p.delivery}</strong></td>

                                                            <td class="align-middle">
                                                                <strong>${o.size}</strong>
                                                            </td>

                                                            <td class="align-middle">
                                                                <a href="subAmountCart?productID=${o.productID}&amount=${o.amount}"><button class="btnSub">-</button></a> 
                                                                <strong>${o.amount}</strong>
                                                                <a href="addAmountCart?productID=${o.productID}&amount=${o.amount}"><button class="btnAdd">+</button></a>
                                                            </td>
                                                            <td class="align-middle"><a href="deleteCart?productID=${o.productID }" class="text-dark">
                                                                    <button type="button" class="btn btn-danger">Delete</button>
                                                                </a>
                                                            </td>
                                                        </tr> 
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- End -->
                            </div>
                        </div>

                        <div class="row py-5 p-4 bg-white rounded shadow-sm">

                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Grand total</div>

                                <div class="p-4">
                                    <ul class="list-unstyled mb-4" id="contentTotalMoney">
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Amount due</strong>
                                            <%  CartDAO da = new CartDAO();
                                                //sua veg...thanh model tuong ung
                                                 List<Cart> listCart = da.getAll();
                                                 ProductDAO dao = new ProductDAO();
                                                //sua veg...thanh model tuong ung
                                                 List<Product> listProduct = dao.getAllProduct();
                                                double totalAmount = 0.0;
                                                for (Cart item : listCart) {
                                                    for (Product product : listProduct) {
                                                        if (item.getProductID() == product.getId()) {
                                                            double productPrice = product.getPrice();
                                                            int productAmount = item.getAmount();
                                                            double productTotal = productPrice * productAmount;
                                                            totalAmount += productTotal;
                                                        }
                                                    }
                                                }
                                            %>
                                            <strong><%= totalAmount %> $</strong>
                                        </li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Transport fee</strong><strong>Free ship</strong></li>
                                       
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total payment</strong>
                                            <% double totalPayment = totalAmount;
                                            %>
                                            <strong><%= totalPayment %> $</strong>
                                        </li> 
                                    </ul><a href="order" class="btn btn-dark rounded-pill py-2 btn-block text-white">Pay</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
        function loadTotalMoney() {
            $.ajax({
                url: "/WebsiteBanGiay/totalMoneyCart",
                type: "get", //send it through get method
                data: {

                },
                success: function (responseData) {
                    document.getElementById("contentTotalMoney").innerHTML = responseData;
                }
            });
        }

        window.addEventListener("load", function loadAmountCart() {
            $.ajax({
                url: "/WebsiteBanGiay/loadAllAmountCart",
                type: "get", //send it through get method
                data: {

                },
                success: function (responseData) {
                    document.getElementById("amountCart").innerHTML = responseData;
                }
            });
        }, false);
        </script>
    </body>

</html>
</html>
