<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_EN'}"/>
<fmt:setBundle basename="properties.content"/>

<html lang="${language}">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title><fmt:message key="conference.title"/></title>
    <link href="../static/css/bootstrap/bootstrap.css" rel="stylesheet"/>
    <link href="../static/css/custom/style.css" rel="stylesheet"/>

</head>

<body>

<jsp:include page="part/header.jsp"/>

<section class="py-5">
    <div class="mt-4">
        <c:if test="${sessionScope.type eq 'admin'}">
            <a class="btn btn-primary ml-2" href="/conferences/newConference"><fmt:message
                    key="conference.addconference"/></a>
        </c:if>
        <h3 align="center"><fmt:message key="conference.header"/></h3>
        <c:choose>
            <c:when test="${empty conferences}">
                <h4><fmt:message key="conference.header.noconferences"/></h4>
            </c:when>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr bgcolor="#87cefa" align="center">
                            <th><fmt:message key="conference.table.deadline"/></th>
                            <th><fmt:message key="conference.table.topic"/></th>
                            <th><fmt:message key="conference.table.date"/></th>
                            <th><fmt:message key="conference.table.place"/></th>
                            <c:if test="${not empty sessionScope.user}">
                                <th><fmt:message key="conference.table.place"/></th>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${conferences}" var="current">
                            <tr>
                                <td><c:out value="${current.deadline}"/></td>
                                <td><c:out value="${current.topic}"/></td>
                                <td><c:out value="${current.begin}"/><br/>
                                    <c:out value="${current.end}"/></td>
                                <td><c:out value="${current.place}"/></td>
                                <c:choose>
                                    <c:when test="${sessionScope.type eq 'user'}">
                                        <td>
                                            <a href="/conferences/signUp?id=${current.idconference}"
                                               class="btn btn-primary entry"><fmt:message
                                                    key="conference.table.signup"/></a>
                                        </td>
                                    </c:when>
                                    <c:when test="${sessionScope.type eq 'admin'}">
                                        <td>
                                            <a href="/conferences/deleteConference?id=${current.idconference}"
                                               class="btn btn-primary entry"><fmt:message
                                                    key="conference.table.delete"/></a>
                                        </td>
                                    </c:when>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
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
