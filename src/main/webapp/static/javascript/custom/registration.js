$(document).ready(function () {
    $('#reglogin').val(localStorage.getItem("login"));
    $('#email').val(localStorage.getItem("email"));
    $('#surname').val(localStorage.getItem("surname"));
    $('#name').val(localStorage.getItem("name"));
    $('#scope').val(localStorage.getItem("scope"));
    $('#company').val(localStorage.getItem("company"));
    $('#position').val(localStorage.getItem("position"));

    var state = $('#state').text();
    if (state) {
        $('#success-register').modal('show');
    }

    $('#signupbutton').click(function () {
        $('#ErrorAlert').hide();
        localStorage.setItem("login", $('#reglogin').val());
        localStorage.setItem("email", $('#email').val());
        localStorage.setItem("surname", $('#surname').val());
        localStorage.setItem("name", $('#name').val());
        localStorage.setItem("scope", $('#scope').val());
        localStorage.setItem("company", $('#company').val());
        localStorage.setItem("position", $('#position').val());
    });

    $('#close-register').click(function () {
        localStorage.clear();
    });
});