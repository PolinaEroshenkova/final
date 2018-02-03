<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вопросы</title>
    <link href="../static/css/bootstrap/bootstrap.css" rel="stylesheet"/>
    <link href="../static/css/custom/style.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="part/header.jsp"/>

<section class="py-5 mt-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-8">
                <c:choose>
                    <c:when test="${sessionScope.type eq 'admin'}">
                        <form role="form" method="post" action="/conferences/">
                            <h2>Опубликовать вопрос</h2>
                            <input type="hidden" name="command" value="publishQuestion"/>

                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger" id="ErrorAlert">
                                    <strong>${errorMessage}</strong>
                                </div>
                            </c:if>

                            <div class="form-group mx-auto">
                                <label for="question-admin">Введите вопрос</label>
                                <textarea class="form-control" name="question-admin" id="question-admin"
                                          rows="3"></textarea>
                            </div>

                            <div class="form-group mx-auto">
                                <label for="answer-admin">Введите ответ</label>
                                <textarea class="form-control" name="answer-admin" id="answer-admin"
                                          rows="3"></textarea>
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Опубликовать вопрос</button>
                            </div>
                        </form>
                        <c:if test="${not empty noAnswerQuestions}">
                            <table class="table table-hover">
                                <thead>
                                <tr bgcolor="#87cefa" align="center">
                                    <th>Логин</th>
                                    <th>Вопрос</th>
                                    <th>Статус</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${noAnswerQuestions}" var="question">
                                    <tr>
                                        <td>${question.login}</td>
                                        <td>${question.question}</td>
                                        <td><a href="" class="btn btn-primary" data-toggle="modal"
                                               data-target="#answerModal" data-id="${question.idquestion}"
                                               data-login="${question.login}">Ответить</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </c:when>
                    <c:when test="${sessionScope.type eq 'user'}">
                        <form role="form" method="post" action="/conferences/">
                            <h2>Есть вопрос?</h2>
                            <input type="hidden" name="command" value="askQuestion"/>

                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger" id="ErrorAlert">
                                    <strong>${errorMessage}</strong>
                                </div>
                            </c:if>

                            <div class="form-group mx-auto">
                                <label for="question">Задайте вопрос администратору</label>
                                <textarea class="form-control" name="question" id="question" rows="3"></textarea>
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Задать вопрос</button>
                            </div>
                        </form>
                    </c:when>
                </c:choose>
                <div>
                    <c:forEach items="${questions}" var="question">
                        <h5>${question.question}</h5>
                        <p>${question.answer}</p>
                        <hr/>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>


<jsp:include page="part/footer.jsp"/>

<div id="modal">
    <jsp:include page="modal/sign.jsp"/>
</div>

<div id="modal">
    <jsp:include page="modal/answer.jsp"/>
</div>

<script src="../static/javascript/lib/jquery.js"></script>
<script src="../static/javascript/bootstrap/bootstrap.bundle.js"></script>
<script src="../static/javascript/custom/login.js"></script>
<script src="../static/javascript/custom/answerquestion.js"></script>
</body>
</html>
