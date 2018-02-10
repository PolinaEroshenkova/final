$(document).ready(function () {
    $('#updateProfileModal').on('show.bs.modal', function (e) {
        var login = $(e.relatedTarget).data('login');
        $(e.currentTarget).find('input[name="login"]').val(login);
        var surname = $(e.relatedTarget).data('surname');
        $(e.currentTarget).find('input[name="surname"]').val(surname);
        var name = $(e.relatedTarget).data('name');
        $(e.currentTarget).find('input[name="name"]').val(name);
        var email = $(e.relatedTarget).data('email');
        $(e.currentTarget).find('input[name="email"]').val(email);
        var password = $(e.relatedTarget).data('password');
        $(e.currentTarget).find('input[name="password"]').val(password);
        var scope = $(e.relatedTarget).data('scope');
        $(e.currentTarget).find('input[name="scope"]').val(scope);
        var position = $(e.relatedTarget).data('position');
        if (position !== 'Не указано' || position !== 'Не паказана' || position !== 'Not indicated') {
            $(e.currentTarget).find('input[name="position"]').val(position);
        }
        var company = $(e.relatedTarget).data('company');
        if (company !== 'Не указано' || company !== 'Не паказана' || company !== 'Not indicated') {
            $(e.currentTarget).find('input[name="company"]').val(company);
        }
    });
});