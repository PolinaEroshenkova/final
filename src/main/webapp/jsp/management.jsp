<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Управление</title>
    <link href="../static/css/bootstrap/bootstrap.css" rel="stylesheet"/>
    <link href="../static/css/custom/half-slider.css" rel="stylesheet"/>
    <link href="../static/css/custom/style.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="part/header.jsp"/>

<section class="py-5">
    <div class="table-responsive">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" id="ErrorAlert">
                <strong>${errorMessage}</strong>
            </div>
        </c:if>
        <table class="table table-hover">
            <h2>Новые заявки на конференции</h2>
            <thead>
            <tr bgcolor="#87cefa" align="center">
                <th>Логин</th>
                <th>Название конференции</th>
                <th>Даты проведения</th>
                <th>Место проведения</th>
                <c:if test="${not empty sessionScope.user}">
                    <th>Статус</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${entries}" var="entry">
                <tr>
                    <td><c:out value="${entry.login}"/></td>
                    <td><c:out value="${entry.conference.topic}"/></td>
                    <td><c:out value="${entry.conference.begin}"/><br/>
                        <c:out value="${entry.conference.end}"/></td>
                    <td><c:out value="${entry.conference.place}"/></td>
                    <td>
                        <a href="/conferences/management?command=changeStatus&status=Отменена&id=${entry.identry}">Отменить
                            заявку</a>
                        <br/>
                        <a href="/conferences/management?command=changeStatus&status=Одобрено&id=${entry.identry}">Принять
                            заявку</a>
                    </td>
                    <c:choose>
                        <c:when test="${sessionScope.type eq 'user'}">
                            <td>
                                <a href="/conferences/signUp?id=${current.idconference}" class="btn btn-primary entry">Оставить
                                    заявку</a>
                            </td>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<jsp:include page="part/footer.jsp"/>

<script src="../static/javascript/lib/jquery.js"></script>
<script src="../static/javascript/bootstrap/bootstrap.bundle.js"></script>
<script src="../static/javascript/custom/login.js"></script>


</body>
</html>
