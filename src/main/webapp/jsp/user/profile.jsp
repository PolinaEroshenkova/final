<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_EN'}"/>
<fmt:setBundle basename="properties.content"/>

<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><fmt:message key="profile.title"/></title>

    <link href="../../static/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../../static/css/custom/profile.css" rel="stylesheet">
    <link href="../../static/css/custom/style.css" rel="stylesheet">

</head>

<body>

<jsp:include page="../part/header.jsp"/>

<section class="py-5 mt-4">
    <div class="container">
        <div class="row">
            <div class="col-md-5 toppad pull-right col-md-offset-3">
                <c:if test="${not empty emailError}">
                    <div class="alert alert-danger" id="emailErrorAlert">
                        <strong id="emailError"><fmt:message key="profile.error.email"/></strong>
                    </div>
                </c:if>
                <a href="/conferences/updateUserInfo" class="btn btn-primary btn-profile"
                   data-toggle="modal" data-target="#updateProfileModal"
                   data-login="${user.login}" data-surname="${user.participant.surname}"
                   data-name="${user.participant.name}" data-email="${user.email}"
                   data-scope="${user.participant.scope}" data-position="${user.participant.position}"
                   data-company="${user.participant.company}"><fmt:message key="profile.edit"/></a>
                <a href="" class="btn btn-primary btn-profile" data-toggle="modal"
                   data-target="#updatePasswordModal">
                    <fmt:message key="profile.editpassword"/></a>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">

                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger" id="ErrorAlert">
                        <strong><fmt:message key="error.servererror"/> </strong>
                    </div>
                </c:if>

                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">${user.participant.surname} ${user.participant.name}</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class=" col-md-9 col-lg-9 ">
                                <table class="table table-user-information">
                                    <tbody>
                                    <tr>
                                        <td class="label"><fmt:message key="profile.login"/>:</td>
                                        <td>${user.login}</td>
                                    </tr>
                                    <tr>
                                        <td class="label"><fmt:message key="profile.email"/>:</td>
                                        <td>${user.email}</td>
                                    </tr>

                                    <tr>
                                        <td class="label"><fmt:message key="profile.scope"/>:</td>
                                        <td>${user.participant.scope}</td>
                                    </tr>
                                    <tr>
                                        <td class="label"><fmt:message key="profile.position"/>:</td>
                                        <td>
                                            <c:if test="${empty user.participant.position}">
                                                <fmt:message key="profile.notindicated"/>
                                            </c:if>
                                            <c:if test="${not empty user.participant.position}">
                                                <c:out value="${user.participant.position}"/>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label"><fmt:message key="profile.company"/>:</td>
                                        <td>
                                            <c:if test="${empty user.participant.company}">
                                                <fmt:message key="profile.notindicated"/>
                                            </c:if>
                                            <c:if test="${not empty user.participant.company}">
                                                <c:out value="${user.participant.company}"/>
                                            </c:if>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br/>
    </div>

    <h3 class="profile-h3"><fmt:message key="profile.header.myentries"/>: </h3>
    <c:choose>
        <c:when test="${empty entries}">
            <h4><fmt:message key="profile.header.noentries"/></h4>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr bgcolor="#87cefa" align="center">
                        <td class="fixed-column"><fmt:message key="profile.table.date"/></td>
                        <td><fmt:message key="profile.table.topic"/></td>
                        <td><fmt:message key="profile.table.sections"/></td>
                        <td><fmt:message key="profile.table.status"/></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${entries}" var="current">
                        <tr>
                            <td class="fixed-column"><ctg:date-time
                                    type="datetime">${current.conference.begin}</ctg:date-time><br/>
                                <ctg:date-time type="datetime">${current.conference.end}</ctg:date-time>
                            </td>
                            <td><c:out value="${current.conference.topic}"/></td>
                            <td>
                                <ul type="circle">
                                    <c:forEach items="${current.conference.sections}" var="section">
                                        <li><c:out value="${section.title}"/></li>
                                    </c:forEach>
                                </ul>
                            </td>
                            <td>
                                <c:if test="${current.status eq 'Waiting' or current.status eq 'Approved'}">
                                    <a href="/conferences/profile?command=deleteEntry&id=${current.identry}"
                                       class="btn btn-primary"><fmt:message key="profile.table.declineentry"/></a>
                                </c:if>
                                <c:if test="${current.status eq 'Disapproved'}">
                                    <fmt:message key="profile.table.disaproved"/>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</section>

<jsp:include page="../part/footer.jsp"/>

<div id="modal">
    <jsp:include page="../modal/updateprofile.jsp"/>
    <jsp:include page="../modal/updatepassword.jsp"/>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../../static/javascript/bootstrap/bootstrap.bundle.min.js"></script>
<script src="../../static/javascript/custom/reformatprofile.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/additional-methods.min.js"></script>
<script src="../../static/javascript/custom/validation.js"></script>
<script src="../../static/javascript/lib/messages.js"></script>

</body>

</html>