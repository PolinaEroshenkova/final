<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal" tabindex="-1" role="dialog" id="answerModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Ответ</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/conferences/">
                    <input type="hidden" name="command" value="answerQuestion">
                    <input type="hidden" name="id" value="">
                    <input type="hidden" name="login" value="">
                    <div class="form-group mx-auto">
                        <label for="answer">Введите ответ</label>
                        <textarea class="form-control" name="answer" id="answer" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Ответить</button>
                </form>
            </div>
        </div>
    </div>
</div>
