<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/">Конференции</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Главная
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/conferences/conferences">Конференции</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="">FAQ</a>
                </li>
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${empty sessionScope.user}">
                            <a class="btn btn-primary btn-info" href="" id="signbutton" data-toggle="modal"
                               data-target="#signModal">Войти</a>
                        </c:when>
                        <c:when test="${sessionScope.type eq 'admin'}">
                            <a class="nav-link" href="/conferences/management" id="signbutton">Заявки</a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-primary btn-info" href="/conferences/profile" id="signbutton">Личный
                                кабинет</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="nav-item">
                    <c:if test="${not empty sessionScope.user}">
                        <a class="nav-link" href="/conferences/Logout?command=Logout">Выйти</a>
                    </c:if>
                </li>
            </ul>
        </div>
    </div>
</nav>