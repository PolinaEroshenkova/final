<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
    <link href="../../static/css/bootstrap/bootstrap.css" rel="stylesheet"/>
    <link href="../../static/css/custom/style.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="../part/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template text-center align-middle">
                <h1>Oops!</h1>
                <h2>${pageContext.errorData.statusCode}</h2>
                <p>Sorry, an error has occured! Try again later</p>
                <a href="/" class="btn btn-primary btn-lg">Take Me Home </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../part/footer.jsp"/>

<div id="modal">
    <jsp:include page="../modal/sign.jsp"/>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../../static/javascript/bootstrap/bootstrap.bundle.js"></script>
<script src="../../static/javascript/custom/login.js"></script>
</body>
</html>
