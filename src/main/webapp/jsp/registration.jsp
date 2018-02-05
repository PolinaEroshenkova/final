<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_EN'}"/>
<fmt:setBundle basename="properties.content"/>

<html lang="${language}">
<head>
    <title><fmt:message key="registration.title"/></title>
    <link href="../static/css/bootstrap/bootstrap.css" rel="stylesheet"/>
    <link href="../static/css/custom/style.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="part/header.jsp"/>


<div class="container registration-form">
    <div class="row justify-content-center">
        <div class="col-6">
            <h2 class="text-center"><fmt:message key="registration.header"/></h2>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger" id="ErrorAlert">
                    <strong>${errorMessage}</strong>
                </div>
            </c:if>
            <form role="form" method="post" action="/conferences/">
                <input type="hidden" name="command" value="CreateProfile"/>

                <div class="form-group mx-auto">
                    <input id="reglogin" name="reglogin" type="text"
                           placeholder="<fmt:message key="registration.form.login"/>*"
                           class="form-control input-md" required="">
                </div>

                <c:if test="${not empty loginError}">
                    <div class="alert alert-danger" id="ErrorAlert">
                        <strong id="loginRegisterError">${loginError}</strong>
                    </div>
                </c:if>

                <div class="form-row justify-content-center">
                    <div class="form-group col-6">
                        <input id="regpassword" name="regpassword" type="password"
                               placeholder="<fmt:message key="registration.form.password"/>*"
                               class="form-control input-md" required="">
                    </div>

                    <div class="form-group col-6">
                        <input id="secpassword" name="secpassword" type="password"
                               placeholder="<fmt:message key="registration.form.confirmpassword"/>*"
                               class="form-control input-md" required="">
                    </div>
                </div>

                <div class="form-group mx-auto">
                    <input id="email" name="email" type="text"
                           placeholder="<fmt:message key="registration.form.email"/>*"
                           class="form-control input-md" required="">

                </div>

                <c:if test="${not empty emailError}">
                    <div class="alert alert-danger" id="ErrorAlert">
                        <strong>${emailError}</strong>
                    </div>
                </c:if>

                <div class="form-row justify-content-center">
                    <div class="form-group col-6">
                        <input id="surname" name="surname" type="text"
                               placeholder="<fmt:message key="registration.form.surnmae"/>*"
                               class="form-control input-md" required="">
                    </div>

                    <div class="form-group col-6">
                        <input id="name" name="name" type="text"
                               placeholder="<fmt:message key="registration.form.name"/>*"
                               class="form-control input-md" required="">
                    </div>
                </div>

                <div class="form-group mx-auto">
                    <input id="scope" name="scope" type="text"
                           placeholder="<fmt:message key="registration.form.scope"/>*"
                           class="form-control input-md" required="">

                </div>

                <div class="form-group mx-auto">
                    <input id="company" name="company" type="text"
                           placeholder="<fmt:message key="registration.form.company"/>"
                           class="form-control input-md">
                </div>

                <div class="form-group mx-auto">
                    <input id="position" name="position" type="text"
                           placeholder="<fmt:message key="registration.form.position"/>"
                           class="form-control input-md">
                </div>

                <div class="form-group mx-auto">
                    <button id="signupbutton" type="submit" name="signin"
                            class="btn btn-block btn-primary btn-primary">
                        <fmt:message key="registration.form.register"/>
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
<script src="../static/javascript/custom/showdialog.js"></script>

</body>
</html>