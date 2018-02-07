<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_EN'}"/>
<fmt:setBundle basename="properties.content"/>

<html lang="${language}">
<head>
    <title><fmt:message key="management.title"/></title>
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
                <strong><fmt:message key="error.servererror"/> </strong>
            </div>
        </c:if>
        <h2><fmt:message key="management.header.newentries"/></h2>
        <c:choose>
            <c:when test="${empty entries}">
                <h4><fmt:message key="management.header.noentries"/></h4>
            </c:when>
            <c:otherwise>
                <table class="table table-hover">
                    <thead>
                    <tr bgcolor="#87cefa" align="center">
                        <th><fmt:message key="management.table.login"/></th>
                        <th><fmt:message key="management.table.topic"/></th>
                        <th class="fixed-column"><fmt:message key="management.table.date"/></th>
                        <th><fmt:message key="management.table.place"/></th>
                        <c:if test="${not empty sessionScope.user}">
                            <th><fmt:message key="management.table.status"/></th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${entries}" var="entry">
                        <tr>
                            <td><c:out value="${entry.login}"/></td>
                            <td><c:out value="${entry.conference.topic}"/></td>
                            <td class="fixed-column"><fmt:formatDate type="both" value="${entry.conference.begin}"
                                                                     timeStyle="short"/><br/>
                                    <fmt:formatDate type="both" value="${entry.conference.end}" timeStyle="short"/>
                            <td><c:out value="${entry.conference.place}"/></td>
                            <td>
                                <form method="post" action="/conferences/">
                                    <input type="hidden" name="command" value="changeStatus">
                                    <input type="hidden" name="id" value="${entry.identry}">
                                    <input type="hidden" name="login" value="${entry.login}">
                                    <button type="submit" name="status" value="Disapproved">
                                        <fmt:message key="management.declineentry"/>
                                    </button>
                                    <button type="submit" name="status" value="Approved">
                                        <fmt:message key="management.acceptentry"/></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</section>

<jsp:include page="part/footer.jsp"/>

<script src="../static/javascript/lib/jquery.js"></script>
<script src="../static/javascript/bootstrap/bootstrap.bundle.js"></script>
<script src="../static/javascript/custom/login.js"></script>


</body>
</html>
