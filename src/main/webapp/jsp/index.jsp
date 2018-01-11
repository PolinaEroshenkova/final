<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Главная</title>
    <link href="../static/css/bootstrap/bootstrap.css" rel="stylesheet"/>
    <link href="../static/css/half-slider.css" rel="stylesheet"/>
    <link href="../static/css/style.css" rel="stylesheet"/>

</head>

<body>

<jsp:include page="header.jsp"/>

<header>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="carousel-item active"
                 style="background-image: url('../static/resources/pictures/index/event1.jpg')">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
            <div class="carousel-item" style="background-image: url('../static/resources/pictures/index/event1.jpg')">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
            <div class="carousel-item" style="background-image: url('../static/resources/pictures/index/event3.jpg')">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</header>

<section class="py-5">
    <div class="container">
        <h1>Информационная поддержка конференций</h1>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<div id="modal">
    <jsp:include page="sign.jsp"/>
</div>

<!-- Bootstrap core JavaScript -->
<script src="../static/javascript/lib/jquery.js"></script>
<script src="../static/javascript/bootstrap/bootstrap.bundle.js"></script>

</body>
</html>