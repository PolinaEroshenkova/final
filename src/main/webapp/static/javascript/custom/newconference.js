$(document).ready(function () {
    var lang = $('html').attr('lang');
    $('#date-start').datetimepicker({
        format: 'L',
        locale: lang
    });
    $('#date-end').datetimepicker({
        format: 'L',
        locale: lang
    });
    $('#deadline').datetimepicker({
        format: 'L',
        locale: lang
    });

    $('#time-start').datetimepicker({
        format: 'LT',
        locale: lang
    });

    $('#time-end').datetimepicker({
        format: 'LT',
        locale: lang
    });

    $('#add-section').click(function () {
        var section = $('#section').val();
        $("#sections").append('<option>' + section + '</option>');
        $('#section').val('');
    });

    $('#delete-section').click(function () {
        $('#sections').find(':selected').remove();
    });

    $('#submit').click(function () {
        $('#sections').find('option').prop('selected', true);
    });
});