<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_EN'}"/>
<fmt:setBundle basename="properties.content"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/"><fmt:message key="header.conferences"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/"><fmt:message key="header.home"/>
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/conferences/conferences"><fmt:message key="header.name"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/conferences/faq"><fmt:message key="header.faq"/></a>
                </li>
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${empty sessionScope.user}">
                            <a class="btn btn-primary btn-info" href="" id="signbutton" data-toggle="modal"
                               data-target="#signModal"><fmt:message key="header.signin"/></a>
                        </c:when>
                        <c:when test="${sessionScope.type eq 'admin'}">
                            <a class="nav-link" href="/conferences/management" id="signbutton"><fmt:message
                                    key="header.entries"/></a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-primary btn-info" href="/conferences/profile" id="signbutton"><fmt:message
                                    key="header.account"/></a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="nav-item">
                    <c:if test="${not empty sessionScope.user}">
                        <a class="nav-link" href="/conferences/Logout"><fmt:message
                                key="header.signout"/></a>
                    </c:if>
                </li>
            </ul>
        </div>
    </div>
</nav>