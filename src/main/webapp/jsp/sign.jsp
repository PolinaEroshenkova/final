<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal fade" id="sign" tabindex="-1" role="dialog">
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
                        <form class="form-horizontal" role="form">

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

                            <div class="form-group">
                                <label class="col-md-4 control-label" for="signin"></label>
                                <div class="col-md-6">
                                    <button id="signinbutton" name="signin"
                                            class="btn btn-block btn-primary btn-primary">Войти
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="tab-pane" id="signup" role="tabpanel">
                    <form class="form-horizontal">

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
                            <label class="col-md-10 control-label" for="company">Компания</label>
                            <div class="col-md-10">
                                <input id="company" name="company" type="text" placeholder="Компания"
                                       class="form-control input-md">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-10 control-label" for="position">Должность</label>
                            <div class="col-md-10">
                                <input id="position" name="position" type="text" placeholder="Должность"
                                       class="form-control input-md">

                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-10">
                                <button id="signupbutton" name="signin" class="btn btn-block btn-primary btn-primary">
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
