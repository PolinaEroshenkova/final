<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang : 'en'}"/>
<fmt:setLocale value="${language}"/>
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
                        <a class="nav-link" href="/conferences/Logout?command=Logout"><fmt:message
                                key="header.signout"/></a>
                    </c:if>
                </li>
                <li class="nav-item">
                    <a href="/conferences/?command=changeLanguage&language=en">EN</a>
                    <a href="/conferences/?command=changeLanguage&language=ru">RU</a>
                    <%--<form method="post" class="ml-1">--%>
                    <%--<input type="hidden" name="command" value="changeLanguage">--%>
                    <%--<select id="language" class="form-control btn-primary" name="language" onchange="submit()">--%>
                    <%--<option name="lang" value="en" ${language == 'en' ? 'selected' : ''}>EN</option>--%>
                    <%--<option name="lang" value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>--%>
                    <%--</select>--%>
                    <%--</form>--%>
                </li>
            </ul>
        </div>
    </div>
</nav>