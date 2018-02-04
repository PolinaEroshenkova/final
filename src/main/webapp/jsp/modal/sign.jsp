<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="properties.content"/>

<div class="modal fade" id="signModal" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header mx-auto">
                <h3><fmt:message key="signin.header"/></h3>
            </div>
            <form name="LoginForm" class="form-horizontal" action="/conferences/"
                  method="post" autocomplete="on">
                <input type="hidden" name="command" value="Login"/>

                <div class="form-group">
                    <div class="col-md-10 mx-auto">
                        <input id="login" name="login" type="text" placeholder="<fmt:message key="signin.login"/>"
                               class="form-control input-md" required="">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-10 mx-auto">
                        <input id="password" name="password" type="password"
                               placeholder="<fmt:message key="signin.password"/>"
                               class="form-control input-md" required="">
                    </div>
                </div>

                <c:if test="${not empty loginError}">
                    <div class="alert alert-danger" id="loginErrorAlert">
                        <strong id="loginError">${loginError}</strong>
                    </div>
                </c:if>

                <div class="form-group">
                    <div class="col-md-10 mx-auto">
                        <button id="signinbutton" name="signin"
                                class="btn btn-block btn-primary"><fmt:message key="signin.signin"/>
                        </button>
                    </div>
                </div>
                <div class="form-group ml-3 text-center">
                    <a href="/conferences/registration" class="text-info small"><fmt:message
                            key="signin.donthaveaccount"/></a>
                </div>
            </form>
        </div>
    </div>
</div>
