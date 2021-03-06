<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_EN'}"/>
<fmt:setBundle basename="properties.content"/>

<html lang="${language}">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title><fmt:message key="main.title"/></title>
    <link href="../../static/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <link href="../../static/css/custom/half-slider.css" rel="stylesheet"/>
    <link href="../../static/css/custom/style.css" rel="stylesheet"/>

</head>

<body>

<jsp:include page="../part/header.jsp"/>

<header>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="carousel-item active"
                 style="background-image: url('../../static/resources/pictures/index/event2.jpg')">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
            <div class="carousel-item"
                 style="background-image: url('../../static/resources/pictures/index/event1.jpg')">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
            <div class="carousel-item"
                 style="background-image: url('../../static/resources/pictures/index/event3.jpg')">
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
    <div class="container mb-auto">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" id="ErrorAlert">
                <strong>${errorMessage}</strong>
            </div>
        </c:if>
        <h1>
            <fmt:message key="main.header"/>
        </h1>
    </div>
</section>

<jsp:include page="../part/footer.jsp"/>

<div id="modal">
    <jsp:include page="../modal/sign.jsp"/>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../../static/javascript/bootstrap/bootstrap.bundle.js"></script>
<script src="../../static/javascript/custom/login.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/additional-methods.min.js"></script>
<script src="../../static/javascript/custom/validation.js"></script>
<script src="../../static/javascript/lib/messages.js"></script>

</body>
</html>