<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="entry" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <form class="form-horizontal">
                    <fieldset>

                        <!-- Form Name -->
                        <legend>Регистрация на конференцию</legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="conference">Конференция</label>
                            <div class="col-md-8">
                                <input id="conference" name="conference" type="text" placeholder=""
                                       class="form-control input-md">

                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="place">Место проведения</label>
                            <div class="col-md-8">
                                <input id="place" name="place" type="text" placeholder="" class="form-control input-md">

                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="date">Дата начала-дата окончания</label>
                            <div class="col-md-8">
                                <input id="date" name="date" type="text" placeholder="" class="form-control input-md">

                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="">Дедлайн подачи заявок</label>
                            <div class="col-md-8">
                                <input id="" name="" type="text" placeholder="" class="form-control input-md">

                            </div>
                        </div>

                        <!-- Select Multiple -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="sections">Выберите секции</label>
                            <div class="col-md-8">
                                <select id="sections" name="sections" class="form-control" multiple="multiple">
                                </select>
                            </div>
                        </div>

                        <!-- Button -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="close"></label>
                            <div class="col-md-4">
                                <button id="close" name="close" class="btn btn-inverse">Назад</button>
                            </div>
                        </div>

                        <!-- Button -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="submit"></label>
                            <div class="col-md-4">
                                <button id="submit" name="submit" class="btn btn-primary">Оставить заявку</button>
                            </div>
                        </div>

                    </fieldset>
                </form>

            </div>
        </div>
    </div>
</div>

