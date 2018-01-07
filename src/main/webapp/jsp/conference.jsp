<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Главная</title>
    <link href="../css/bootstrap/bootstrap.css" rel="stylesheet"/>
    <link href="../css/half-slider.css" rel="stylesheet"/>
    <link href="../css/style.css" rel="stylesheet"/>

</head>

<body>

<jsp:include page="header.jsp"/>

<section class="py-5">
    <div class="conference-div">
        <h3 align="center">Ближайшие конференции</h3>
        <div class="table-responsive d-sm-table">
            <table class="table table-hover">
                <thead>
                <tr bgcolor="#87cefa" align="center">
                    <th>Дедлайн подачи заявок</th>
                    <th>Название конференции</th>
                    <th>Количество участников</th>
                    <th>Место проведения</th>
                    <th>Статус</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${conferences}" var="current">
                    <tr>
                            <%--<td hidden>${current.idconference}</td>--%>
                        <td><c:out value="${current.deadline}"/></td>
                        <td><c:out value="${current.topic}"/></td>
                        <td><c:out value="${current.participantsnumber}"/></td>
                        <td><c:out value="${current.place}"/></td>
                        <td><a href="">Оставить заявку</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>


</body>
