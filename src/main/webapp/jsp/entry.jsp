<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Заявка</title>
    <link href="../static/css/bootstrap/bootstrap.css" rel="stylesheet"/>
    <link href="../static/css/custom/style.css" rel="stylesheet"/>

</head>

<body>

<jsp:include page="part/header.jsp"/>
<section class="py-5">
    <div class="container toppad">
        <form class="form-horizontal" action="Controller" method="post">
            <fieldset>

                <legend>Регистрация на конференцию</legend>
                <input type="hidden" name="command" value="entryProcessing">

                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger" id="ErrorAlert">
                        <strong>${errorMessage}</strong>
                    </div>
                </c:if>

                <div class="form-group">
                    <label class="col-md-4 label control-label" for="conference">Конференция</label>
                    <div class="col-md-12">
                        <output id="conference" name="conference" type="text" class="form-control input-md">
                            ${conference.topic}</output>
                    </div>
                </div>

                <table class="table table-hover">
                    <thead>
                    <tr bgcolor="#87cefa">
                        <th>Место проведения</th>
                        <th>Дата начала</th>
                        <th>Дата окончания</th>
                        <th>Дедлайн подачи заявок</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${conference.place}</td>
                        <td>${conference.begin}</td>
                        <td>${conference.end}</td>
                        <td>${conference.deadline}</td>
                    </tr>
                    </tbody>
                </table>

                <div class="form-group">
                    <label class="col-md-4 control-label label" for="sections">Выберите секции</label>
                    <div class="col-md-12">
                        <select id="sections" name="sections" class="form-control" multiple="multiple" size="5"
                                required>
                            <c:forEach items="${sections}" var="section">
                                <option name="idsection" value="${section.idsection}">${section.title}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary">Оставить заявку</button>
                        <a href="/showConferences" class="btn btn-secondary">Назад</a>
                    </div>
                </div>

            </fieldset>
        </form>
    </div>
</section>

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
<script src="../static/javascript/custom/showdialog.js"></script>

</body>
</html>
