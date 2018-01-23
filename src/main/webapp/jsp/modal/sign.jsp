<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="signModal" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active tab"><a href="#signin" data-toggle="tab" role="tab">Войти</a></li>
                    <li class="tab"><a href="#signup" data-toggle="tab" role="tab">Зарегистрироваться</a></li>
                </ul>
            </div>

            <div class="tab-content">

                <div class="tab-pane active" id="signin" role="tabpanel">
                    <div class="control-group">
                        <form name="LoginForm" class="form-horizontal" action="Controller"
                              method="post">
                            <input type="hidden" name="command" value="Login"/>

                            <div class="form-group">
                                <label class="col-md-4 control-label" for="login">Логин</label>
                                <div class="col-md-10">
                                    <input id="login" name="login" type="text" placeholder="Логин"
                                           class="form-control input-md" required="">

                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label" for="password">Пароль</label>
                                <div class="col-md-10">
                                    <input id="password" name="password" type="password" placeholder="Пароль"
                                           class="form-control input-md" required="">

                                </div>
                            </div>

                            <div class="error">
                                ${loginError}
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label" for="signin"></label>
                                <div class="col-md-6">
                                    <button id="signinbutton" name="signin"
                                            class="btn btn-block btn-primary">Войти
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="tab-pane" id="signup" role="tabpanel">
                    <form class="form-horizontal" method="post" action="${sessionScope.request.requestURL}">
                        <input type="hidden" name="command" value="Register"/>

                        <div class="form-group">
                            <label class="col-md-10 control-label" for="reglogin">Логин</label>
                            <div class="col-md-10">
                                <input id="reglogin" name="reglogin" type="text" placeholder="Логин"
                                       class="form-control input-md" required="">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-10 control-label" for="regpassword">Пароль</label>
                            <div class="col-md-10">
                                <input id="regpassword" name="regpassword" type="text" placeholder="Пароль"
                                       class="form-control input-md" required="">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-10 control-label" for="secpassword">Подтверждение пароля</label>
                            <div class="col-md-10">
                                <input id="secpassword" name="secpassword" type="text" placeholder="Пароль"
                                       class="form-control input-md" required="">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-10 control-label" for="email">email</label>
                            <div class="col-md-10">
                                <input id="email" name="email" type="text" placeholder="email"
                                       class="form-control input-md" required="">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-10 control-label" for="surname">Фамилия</label>
                            <div class="col-md-10">
                                <input id="surname" name="surname" type="text" placeholder="Фамилия"
                                       class="form-control input-md" required="">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-10 control-label" for="name">Имя</label>
                            <div class="col-md-10">
                                <input id="name" name="name" type="text" placeholder="Имя" class="form-control input-md"
                                       required="">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-10 control-label" for="scope">Сфера деятельности</label>
                            <div class="col-md-10">
                                <input id="scope" name="scope" type="text" placeholder="Сфера"
                                       class="form-control input-md" required="">

                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-10">
                                <button id="signupbutton" type="submit" name="signin"
                                        class="btn btn-block btn-primary btn-primary">
                                    Зарегистрироваться
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
