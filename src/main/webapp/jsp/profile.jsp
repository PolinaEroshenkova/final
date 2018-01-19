<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Профиль</title>

    <link href="../static/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/style.css" rel="stylesheet">

</head>

<body>

<jsp:include page="header.jsp"/>

<section class="py-5">
    <div class="container">
        <div class="row">
            <div class="col-md-5 toppad pull-right col-md-offset-3">
                <a href="/updateUserInfo" class="btn btn-primary btn-profile">Редактировать</a>
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
                                        <td class="label">Логин:</td>
                                        <td>${user.login}</td>
                                    </tr>
                                    <tr>
                                        <td class="label">Пароль:</td>
                                        <td>${user.password}</td>
                                    </tr>
                                    <tr>
                                        <td class="label">email:</td>
                                        <td>${user.email}</td>
                                    </tr>

                                    <tr>
                                        <td class="label">Сфера деятельности:</td>
                                        <td>${participant.scope}</td>
                                    </tr>
                                    <tr>
                                        <td class="label">Должность:</td>
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
                                        <td class="label">Компания:</td>
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
    </div>

    <h3 class="profile-h3">Ваши конференции: </h3>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr bgcolor="#87cefa" align="center">
                <td>Дата проведения</td>
                <td>Название конференции</td>
                <td>Секции</td>
                <td>Статус</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${entries}" var="current">
                <tr>
                    <td><c:out value="${current.conference.begin}"/><br/>
                        <c:out value="${current.conference.end}"/>
                    </td>
                    <td><c:out value="${current.conference.topic}"/></td>
                    <td>
                        <ul type="circle">
                            <c:forEach items="${current.conference.sections}" var="section">
                                <li><c:out value="${section.title}"/></li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td>
                        <c:if test="${current.status eq 'Ожидает' or current.status eq 'Одобрено'}">
                            <c:out value="Отменить заявку"/>
                        </c:if>
                        <c:if test="${current.status eq 'Отклонено'}">
                            <c:out value="${current.status}"/>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<script src="../static/javascript/lib/jquery.js"></script>
<script src="../static/javascript/bootstrap/bootstrap.bundle.min.js"></script>

</body>

</html>