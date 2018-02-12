<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_EN'}"/>
<fmt:setBundle basename="properties.content"/>

<html lang="${language}">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title><fmt:message key="conference.title"/></title>
    <link href="../../static/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <link href="../../static/css/custom/style.css" rel="stylesheet"/>
    <link href="../../static/css/custom/popover.css" rel="stylesheet"/>

</head>

<body>

<jsp:include page="../part/header.jsp"/>

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
                            <th class="fixed-column"><fmt:message key="conference.table.date"/></th>
                            <th><fmt:message key="conference.table.place"/></th>
                            <c:if test="${not empty sessionScope.user}">
                                <th><fmt:message key="conference.table.place"/></th>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${conferences}" var="current">
                            <tr>
                                    <%--<td><fmt:formatDate type="date" value="${current.deadline}" timeStyle="short"/></td>--%>
                                <td><ctg:date-time type="date">${current.deadline}</ctg:date-time></td>
                                <td>
                                    <span data-toggle="popover" data-id="${current.idconference}"><c:out
                                            value="${current.topic}"/></span>
                                </td>
                                <td class="fixed-column"><ctg:date-time
                                        type="datetime">${current.begin}"</ctg:date-time><br/>
                                    <ctg:date-time type="datetime">${current.end}</ctg:date-time>
                                </td>
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
                                <div class="popover-content" id=${current.idconference} style="display:none">
                                    <ul class="list-group custom-popover">
                                        <c:forEach items="${current.sections}" var="section">
                                            <li class="list-group-item">${section.title}</li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</section>

<jsp:include page="../part/footer.jsp"/>

<div id="modal">
    <jsp:include page="../modal/sign.jsp"/>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../../static/javascript/bootstrap/bootstrap.bundle.js"></script>
<script src="../../static/javascript/custom/login.js"></script>
<script src="../../static/javascript/custom/popover.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/additional-methods.min.js"></script>
<script src="../../static/javascript/custom/validation.js"></script>
<script src="../../static/javascript/lib/messages.js"></script>

</body>
</html>
