<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_EN'}"/>
<fmt:setBundle basename="properties.content"/>

<html lang="${language}">
<head>
    <title><fmt:message key="faq.title"/></title>
    <link href="../../static/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <link href="../../static/css/custom/style.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="../part/header.jsp"/>

<section class="py-5 mt-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-8">
                <c:choose>
                    <c:when test="${sessionScope.type eq 'admin'}">
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger" id="ErrorAlert">
                                <strong><fmt:message key="error.servererror"/> </strong>
                            </div>
                        </c:if>
                        <form role="form" method="post" action="/conferences/" name="faq-admin" id="faq-admin">
                            <h2><fmt:message key="faq.header"/></h2>
                            <input type="hidden" name="command" id=command" value="publishQuestion"/>

                            <div class="form-group mx-auto">
                                <label for="questionadmin"><fmt:message key="faq.form.enterquestion"/></label>
                                <textarea class="form-control valid" name="questionadmin" id="questionadmin"
                                          rows="3"></textarea>
                            </div>

                            <div class="form-group mx-auto">
                                <label for="answeradmin"><fmt:message key="faq.form.enteranswer"/></label>
                                <textarea class="form-control valid" name="answeradmin" id="answeradmin"
                                          rows="3"></textarea>
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary"><fmt:message key="faq.publish"/></button>
                            </div>
                        </form>
                        <c:choose>
                            <c:when test="${empty noAnswerQuestions}">
                                <h4><fmt:message key="faq.header.noquestions"/></h4>
                            </c:when>
                            <c:otherwise>
                                <table class="table table-hover">
                                    <thead>
                                    <tr bgcolor="#87cefa" align="center">
                                        <th><fmt:message key="faq.table.login"/></th>
                                        <th><fmt:message key="faq.table.question"/></th>
                                        <th><fmt:message key="faq.table.status"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${noAnswerQuestions}" var="question">
                                        <tr>
                                            <td>${question.login}</td>
                                            <td>${question.question}</td>
                                            <td><a href="" class="btn btn-primary" data-toggle="modal"
                                                   data-target="#answerModal" data-id="${question.idquestion}"
                                                   data-login="${question.login}"><fmt:message
                                                    key="faq.table.answer"/></a>
                                                <a href="/conferences/faq?command=deleteQuestion&id=${question.idquestion}"
                                                   class="btn btn-primary"><fmt:message key="faq.deletequestion"/></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:when test="${sessionScope.type eq 'user'}">
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger" id="ErrorAlert">
                                <strong><fmt:message key="error.servererror"/> </strong>
                            </div>
                        </c:if>
                        <form role="form" method="post" action="/conferences/" id="faq-user" name="faq-user">
                            <h2><fmt:message key="faq.header.havequestion"/></h2>
                            <input type="hidden" name="command" value="askQuestion"/>

                            <div class="form-group mx-auto">
                                <label for="question"><fmt:message key="faq.header.askadmin"/></label>
                                <textarea class="form-control valid" name="question" id="question" rows="3"></textarea>
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary"><fmt:message key="faq.ask"/></button>
                            </div>
                        </form>
                    </c:when>
                </c:choose>
                <div>
                    <c:choose>
                        <c:when test="${empty questions}">
                            <h4><fmt:message key="faq.header.nopublishedquestions"/></h4>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${questions}" var="question">
                                <h5>${question.question}</h5>
                                <p>${question.answer}</p>
                                <c:if test="${type eq 'admin'}">
                                    <a href="/conferences/faq?command=deleteQuestion&id=${question.idquestion}"
                                       class="btn btn-primary"><fmt:message key="faq.deletequestion"/></a>
                                </c:if>
                                <hr/>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</section>


<jsp:include page="../part/footer.jsp"/>

<div id="modal">
    <jsp:include page="../modal/sign.jsp"/>
</div>

<div id="modal">
    <jsp:include page="../modal/answer.jsp"/>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../../static/javascript/bootstrap/bootstrap.bundle.js"></script>
<script src="../../static/javascript/custom/login.js"></script>
<script src="../../static/javascript/custom/answerquestion.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/additional-methods.min.js"></script>
<script src="../../static/javascript/custom/validation.js"></script>
<script src="../../static/javascript/lib/messages.js"></script>
</body>
</html>
