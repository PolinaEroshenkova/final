<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Главная</title>
    <link href="../static/css/bootstrap/bootstrap.css" rel="stylesheet"/>
    <link href="../static/css/half-slider.css" rel="stylesheet"/>
    <link href="../static/css/style.css" rel="stylesheet"/>

</head>

<body>

<jsp:include page="header.jsp"/>

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
                    <th>Статус</th>
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
                        <td><a href="" class="btn btn-primary" data-toggle="modal" data-target="#entry">Оставить
                            заявку</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<div id="modal">
    <jsp:include page="entry.jsp"/>
</div>

<script src="../static/javascript/lib/jquery.js"></script>
<script src="../static/javascript/bootstrap/bootstrap.bundle.js"></script>

</body>
</html>
