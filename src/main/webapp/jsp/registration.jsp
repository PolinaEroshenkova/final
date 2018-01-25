<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Регистрация</title>
    <link href="../static/css/bootstrap/bootstrap.css" rel="stylesheet"/>
    <link href="../static/css/custom/style.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="part/header.jsp"/>


<div class="container registration-form">
    <div class="row justify-content-center">
        <div class="col-6">
            <h2 class="text-center">Форма регистрации</h2>
            <form role="form" method="post" action="Controller">
                <input type="hidden" name="command" value="CreateProfile"/>

                <div class="form-group mx-auto">
                    <input id="reglogin" name="reglogin" type="text" placeholder="Логин*"
                           class="form-control input-md" required="">
                </div>

                <c:if test="${not empty loginError}">
                    <div class="alert alert-danger" id="ErrorAlert">
                        <strong id="loginRegisterError">${loginError}</strong>
                    </div>
                </c:if>

                <div class="form-row justify-content-center">
                    <div class="form-group col-6">
                        <input id="regpassword" name="regpassword" type="password" placeholder="Пароль*"
                               class="form-control input-md" required="">
                    </div>

                    <div class="form-group col-6">
                        <input id="secpassword" name="secpassword" type="password" placeholder="Подтверждение пароля*"
                               class="form-control input-md" required="">
                    </div>
                </div>

                <div class="form-group mx-auto">
                    <input id="email" name="email" type="text" placeholder="email*"
                           class="form-control input-md" required="">

                </div>

                <c:if test="${not empty emailError}">
                    <div class="alert alert-danger" id="ErrorAlert">
                        <strong>${emailError}</strong>
                    </div>
                </c:if>

                <div class="form-row justify-content-center">
                    <div class="form-group col-6">
                        <input id="surname" name="surname" type="text" placeholder="Фамилия*"
                               class="form-control input-md" required="">
                    </div>

                    <div class="form-group col-6">
                        <input id="name" name="name" type="text" placeholder="Имя*"
                               class="form-control input-md" required="">
                    </div>
                </div>

                <div class="form-group mx-auto">
                    <input id="scope" name="scope" type="text" placeholder="Сфера деятельности*"
                           class="form-control input-md" required="">

                </div>

                <div class="form-group mx-auto">
                    <input id="company" name="company" type="text" placeholder="Компания"
                           class="form-control input-md">
                </div>

                <div class="form-group mx-auto">
                    <input id="position" name="position" type="text" placeholder="Должность"
                           class="form-control input-md">
                </div>

                <div class="form-group mx-auto">
                    <button id="signupbutton" type="submit" name="signin"
                            class="btn btn-block btn-primary btn-primary">
                        Зарегистрироваться
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div hidden>
    <p id="state">${state}</p>
</div>


<jsp:include page="part/footer.jsp"/>

<div id="modal">
    <jsp:include page="modal/sign.jsp"/>
    <jsp:include page="modal/success.jsp"/>
</div>

<script src="../static/javascript/lib/jquery.js"></script>
<script src="../static/javascript/bootstrap/bootstrap.bundle.js"></script>
<script src="../static/javascript/custom/registration.js"></script>

</body>
</html>