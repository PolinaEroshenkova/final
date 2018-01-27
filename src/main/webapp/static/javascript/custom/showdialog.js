$(document).ready(function () {
    var state = $('#state').text();
    if (state) {
        $('#success').modal('show');
    }
});