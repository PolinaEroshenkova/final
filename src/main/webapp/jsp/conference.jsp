<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Конференции</title>
    <link href="../static/css/bootstrap/bootstrap.css" rel="stylesheet"/>
    <link href="../static/css/custom/style.css" rel="stylesheet"/>

</head>

<body>

<jsp:include page="part/header.jsp"/>

<section class="py-5">
    <div class="mt-4">
        <c:if test="${sessionScope.type eq 'admin'}">
            <a class="btn btn-primary ml-2" href="/newConference">Добавить новую конференцию</a>
        </c:if>
        <h3 align="center">Ближайшие конференции</h3>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" id="ErrorAlert">
                <strong>${errorMessage}</strong>
            </div>
        </c:if>
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr bgcolor="#87cefa" align="center">
                    <th hidden></th>
                    <th>Дедлайн подачи заявок</th>
                    <th>Название конференции</th>
                    <th>Даты проведения</th>
                    <th>Место проведения</th>
                    <c:if test="${not empty sessionScope.user}">
                        <th>Статус</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${conferences}" var="current">
                    <tr>
                        <td hidden><c:out value="${current.idconference}"/></td>
                        <td><c:out value="${current.deadline}"/></td>
                        <td><c:out value="${current.topic}"/></td>
                        <td><c:out value="${current.begin}"/><br/>
                            <c:out value="${current.end}"/></td>
                        <td><c:out value="${current.place}"/></td>
                        <c:choose>
                            <c:when test="${sessionScope.type eq 'user'}">
                                <td>
                                    <a href="\signUp?id=${current.idconference}" class="btn btn-primary entry">Оставить
                                        заявку</a>
                                </td>
                            </c:when>
                            <c:when test="${sessionScope.type eq 'admin'}">
                                <td>
                                    <a href="\deleteConference?id=${current.idconference}"
                                       class="btn btn-primary entry">Удалить конференцию</a>
                                </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<jsp:include page="part/footer.jsp"/>

<div id="modal">
    <jsp:include page="modal/sign.jsp"/>
</div>

<script src="../static/javascript/lib/jquery.js"></script>
<script src="../static/javascript/bootstrap/bootstrap.bundle.js"></script>
<script src="../static/javascript/custom/login.js"></script>

</body>
</html>
