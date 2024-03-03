<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Sidebar -->
<nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-white" style="padding: 0px; margin-top: 65px;">
    <div class="position-sticky">
        <div class="list-group list-group-flush mx-3 mt-4">
            <c:if test="${sessionScope.acc.isSell == 1}">
                    <a href="manager" class="list-group-item list-group-item-action py-2 ripple">
                        <i class="fas fa-tshirt fa-fw me-3"></i><span>Product Management</span>
                    </a>
            </c:if> 
            <c:if test="${sessionScope.acc.isAdmin == 1}">
                    <a href="managerAccount" class="list-group-item list-group-item-action py-2 ripple">
                        <i class="fas fa-user fa-fw me-3"></i><span>Account management</span>
                    </a>
                <a href="doanhthutheothang" class="list-group-item list-group-item-action py-2 ripple">
                    <i class="fas fa-chart-bar fa-fw me-3"></i><span>Monthly revenue</span>
                </a>
            </c:if> 


        </div>
    </div>
</nav>
<!-- Sidebar -->