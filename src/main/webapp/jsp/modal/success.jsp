<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal" tabindex="-1" role="dialog" id="success">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Поздравляем!</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>${message}</p>
            </div>
            <div class="modal-footer">
                <a href="${href}" class="btn btn-primary" id="close">Закрыть</a>
            </div>
        </div>
    </div>
</div>