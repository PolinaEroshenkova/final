<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Конференции</title>
    <link href="../static/css/bootstrap/bootstrap.css" rel="stylesheet"/>
    <link href="../static/css/custom/half-slider.css" rel="stylesheet"/>
    <link href="../static/css/custom/style.css" rel="stylesheet"/>

</head>

<body>

<jsp:include page="part/header.jsp"/>

<section class="py-5">
    <div class="toppad">
        <h3 align="center">Ближайшие конференции</h3>
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
                        <c:if test="${not empty sessionScope.user}">
                            <td>
                                <a href="\signUp?id=${current.idconference}" class="btn btn-primary entry">Оставить
                                    заявку</a>
                            </td>
                        </c:if>
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
