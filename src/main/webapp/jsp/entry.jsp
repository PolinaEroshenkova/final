<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="entry" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <form class="form-horizontal">
                    <fieldset>

                        <legend>Заявка на конференцию</legend>

                        <div class="form-group">
                            <label class="col-md-4 control-label" for="conference">Название конференции</label>
                            <div class="col-md-8">
                                <input id="conference" name="conference" type="text" placeholder=""
                                       class="form-control input-md" required="">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label" for="sections">Выберите секции</label>
                            <div class="col-md-8">
                                <select id="sections" name="sections" class="form-control" multiple="multiple">
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label" for="register"></label>
                            <div class="col-md-4">
                                <button id="register" name="register" class="btn btn-primary">Оставить заявку</button>
                            </div>
                        </div>

                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

