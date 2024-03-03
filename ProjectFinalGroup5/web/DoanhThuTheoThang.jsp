<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Statistic</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <!--   Bootstrap tooltips -->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
        <style>
            body {
                margin: 0;
                padding: 0;
            }
        </style>
        <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"><link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&amp;display=swap"><link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb5/3.8.1/compiled.min.css"><link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb-plugins-gathered.min.css"><style>body {
                background-color: #fbfbfb;
            }
            @media (min-width: 991.98px) {
                main {
                    padding-left: 240px;
                }
            }

            /* Sidebar */
            .sidebar {
                position: fixed;
                top: 0;
                bottom: 0;
                left: 0;
                padding: 58px 0 0; /* Height of navbar */
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
                width: 240px;
                z-index: 600;
            }

            @media (max-width: 991.98px) {
                .sidebar {
                    width: 100%;
                }
            }
            .sidebar .active {
                border-radius: 5px;
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
            }

            .sidebar-sticky {
                position: relative;
                top: 0;
                height: calc(100vh - 48px);
                padding-top: 0.5rem;
                overflow-x: hidden;
                overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
            }</style>
    </head>
    <body >

        <!--Main Navigation-->
        <header>
            <jsp:include page="LeftAdmin.jsp"></jsp:include>

            </header>
            <!--Main Navigation-->

            <!--Main layout-->
            <main>
                <div class="container pt-4">



                    <!-- Section: Main chart 2 -->
                    <section class="mb-4" id="doanhThuThang">
                        <div class="card">
                            <div class="card-header py-3">
                                <h5 class="mb-0 text-center"><strong>Revenue by month</strong></h5>
                            </div>
                            <div class="card-body">
                                <canvas id="horizontalBar"></canvas>
                            </div>
                        </div>
                    </section>
                    <!-- Section: Main chart 2 -->

                </div>
            </main>

            <!--     MDB Ecommerce JavaScript -->
            <script>// Graph
    // Horizontal Bar Chart
                new Chart(document.getElementById("horizontalBar"), {
                    "type": "horizontalBar",
                    "data": {
                        "labels": ["December", "November", "October", "September", "August", "July", "June", "May", "April", "March", "February", "January"],
                        "datasets": [{
                                "label": "Revenue $",
                                "data": [${totalMoneyMonth12}, ${totalMoneyMonth11}, ${totalMoneyMonth10}, ${totalMoneyMonth9}, ${totalMoneyMonth8}, ${totalMoneyMonth7}, ${totalMoneyMonth6}, ${totalMoneyMonth5}, ${totalMoneyMonth4}, ${totalMoneyMonth3}, ${totalMoneyMonth2}, ${totalMoneyMonth1}],
                                "fill": false,
                                "backgroundColor": [
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 159, 64, 0.2)",
                                    "rgba(255, 205, 86, 0.2)",
                                    "rgba(75, 192, 192, 0.2)",
                                    "rgba(54, 162, 235, 0.2)",
                                    "rgba(153, 102, 255, 0.2)",
                                    "rgba(201, 203, 207, 0.2)",
                                    "rgba(255, 0, 0, 0.4)",
                                    "rgba(0, 300, 0, 0.4)",
                                    "rgba(0, 0, 300, 0.4)",
                                    "rgba(0, 200, 300, 0.4)",
                                    "#DDC488"
                                ],
                                "borderColor": [
                                    "rgb(255, 99, 132)",
                                    "rgb(255, 159, 64)",
                                    "rgb(255, 205, 86)",
                                    "rgb(75, 192, 192)",
                                    "rgb(54, 162, 235)",
                                    "rgb(153, 102, 255)",
                                    "rgb(201, 203, 207)",
                                    "rgb(1000, 0, 0)",
                                    "rgb(0, 300, 0)",
                                    "rgba(0, 100, 500)",
                                    "rgba(0, 250, 300)",
                                    "#ECAB53"
                                ],
                                "borderWidth": 2
                            }]
                    },
                    "options": {
                        "scales": {
                            "xAxes": [{
                                    "ticks": {
                                        "beginAtZero": true
                                    }
                                }]
                        }
                    }
                });

        </script>
    </body>
</html>