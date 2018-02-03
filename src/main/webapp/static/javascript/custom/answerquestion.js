$(document).ready(function () {
    $('#answerModal').on('show.bs.modal', function (e) {
        var id = $(e.relatedTarget).data('id');
        $(e.currentTarget).find('input[name="id"]').val(id);
        var login = $(e.relatedTarget).data('login');
        $(e.currentTarget).find('input[name="login"]').val(login);
    });
});