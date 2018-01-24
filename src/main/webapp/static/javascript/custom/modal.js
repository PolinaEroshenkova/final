$(document).ready(function () {
    var error = $('#loginEror').text();
    if (error) {
        $('#signModal').modal('show');
    }

    $('#signModal').on('hidden.bs.modal', function (e) {
        $('#loginErrorAlert').hide();
    });
});