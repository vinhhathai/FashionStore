<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark" style="position: fixed; top: 0; width:100%;  z-index: 1;  box-shadow: 0 2px 4px rgba(0.2, 0.2, 0.2, 0.1);">
    <div class="container">
        <a class="navbar-brand" href="home">FASHION STORE </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto" >
                <li class="nav-item">
                    <a class="nav-link" href="home" style="color: White;">HOME </a>
                </li> 
                <li class="nav-item">
                    <a class="nav-link" href="shop" >SHOP</a>
                </li> 

                <c:if test="${sessionScope.acc.isSell == 1}">
                    <li class="nav-item">
                        <a class="nav-link" href="manager">MANAGER </a>
                    </li>
                </c:if> 
                <c:if test="${sessionScope.acc != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="#">WELCOME ${sessionScope.acc.user}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">LOGOUT</a>
                    </li> 
                </c:if>
                <c:if test="${sessionScope.acc == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="Login.jsp">LOGIN</a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.acc != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="EditProfile.jsp" >EDIT PROFILE</a>
                    </li>
                </c:if>
                <%--  <c:if test="${sessionScope.acc.isAdmin == 1}">
                     <li class="nav-item">
                         <a class="nav-link" href="statistic">Statistic</a>
                     </li>
                 </c:if> --%>
            </ul>

            <form action="search" method="post" class="form-inline my-2 my-lg-0">

              <a href="managerCart" class="text-white ml-3 text-decoration-none" style="font-size: 20px;">
    <i class="fas fa-shopping-basket fa-lg"></i> 
    <span style="font-size: 14px;">CART</span>
    <b><span id="amountCart" class="badge badge-light" style="color:black; font-size: 12px;"></span></b>
</a>



            </form>
        </div>
    </div>
</nav>



<!--end of menu-->
