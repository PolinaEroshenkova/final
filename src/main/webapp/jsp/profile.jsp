<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Профиль</title>

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">

</head>

<body>

<jsp:include page="header.jsp"/>

<section class="py-5">
    <div class="container">
        <div class="row">
            <div class="col-md-5 toppad pull-right col-md-offset-3">
                <a href="" class="btn btn-primary btn-profile">Редактировать</a>
                <br/>
                <a href="controller?command=Logout" class="btn btn-primary btn-profile">Выйти</a>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">


                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">${participant.surname} ${participant.name}</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class=" col-md-9 col-lg-9 ">
                                <table class="table table-user-information">
                                    <tbody>
                                    <tr>
                                        <td>login:</td>
                                        <td>${user.login}</td>
                                    </tr>
                                    <tr>
                                        <td>password:</td>
                                        <td>${user.password}</td>
                                    </tr>
                                    <tr>
                                        <td>email:</td>
                                        <td>${user.email}</td>
                                    </tr>

                                    <tr>
                                    <tr>
                                        <td>scope:</td>
                                        <td>${participant.scope}</td>
                                    </tr>
                                    <tr>
                                        <td>position:</td>
                                        <td>
                                            <c:if test="${empty participant.position}">
                                                <c:out value="Не указано"/>
                                            </c:if>
                                            <c:if test="${not empty participant.position}">
                                                <c:out value="${participant.position}"/>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>company:</td>
                                        <td>
                                            <c:if test="${empty participant.company}">
                                                <c:out value="Не указано"/>
                                            </c:if>
                                            <c:if test="${not empty participant.company}">
                                                <c:out value="${participant.company}"/>
                                            </c:if>
                                        </td>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <br/>
        <h3>Ваши конференции: </h3>
        <table class="table">
            <tbody>
            <tr>
                <td>15 декабря</td>
                <td>Обучение английскому
                    <br/>15декабря-15января
                </td>
                <td>Бизнес-центр Цельсий</td>
                <td><a href="">Отменить заявку</a></td>
            </tr>
            </tbody>
        </table>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<script src="../javascript/lib/jquery.js"></script>
<script src="../javascript/bootstrap/bootstrap.bundle.min.js"></script>

</body>

</html>