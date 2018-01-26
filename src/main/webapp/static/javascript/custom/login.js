$(document).ready(function () {
    var error = $('#loginError').text();
    if (error) {
        $('#signModal').modal('show');
    }

    $('#signModal').on('hidden.bs.modal', function (e) {
        $('#loginErrorAlert').hide();
    });
});