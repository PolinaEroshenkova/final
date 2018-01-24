<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="signModal" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header mx-auto">
                <h3>Войдите в аккаунт</h3>
            </div>
            <form name="LoginForm" class="form-horizontal" action="Controller"
                  method="post" autocomplete="on">
                <input type="hidden" name="command" value="Login"/>

                <div class="form-group">
                    <div class="col-md-10 mx-auto">
                        <input id="login" name="login" type="text" placeholder="Логин"
                               class="form-control input-md" required="">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-10 mx-auto">
                        <input id="password" name="password" type="password" placeholder="Пароль"
                               class="form-control input-md" required="">
                    </div>
                </div>

                <c:if test="${not empty loginError}">
                    <div class="alert alert-danger" id="loginErrorAlert">
                        <strong id="loginEror">${loginError}</strong>
                    </div>
                </c:if>

                <div class="form-group">
                    <div class="col-md-10 mx-auto">
                        <button id="signinbutton" name="signin"
                                class="btn btn-block btn-primary">Войти
                        </button>
                    </div>
                </div>
                <div class="form-group ml-3 text-center">
                    <a href="/registration" class="text-info small">Еще нет аккаунта? Зарегистрироваться</a>
                </div>
            </form>
        </div>
    </div>
</div>
