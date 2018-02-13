$(document).ready(function () {
    var lang = $('html').attr('lang');
    $('#datestart').datetimepicker({
        format: 'L',
        locale: lang,
        minDate: moment()
    });
    $('#dateend').datetimepicker({
        format: 'L',
        locale: lang,
        minDate: moment()
    });
    $('#deadline').datetimepicker({
        format: 'L',
        locale: lang,
        minDate: moment()
    });

    $('#timestart').datetimepicker({
        format: 'LT',
        locale: lang
    });

    $('#timeend').datetimepicker({
        format: 'LT',
        locale: lang
    });

    $('#add-section').click(function () {
        var section = $('#section').val();
        if (section) {
            $("#sections").append('<option>' + section + '</option>');
            $('#section').val('');
        }
    });

    $('#delete-section').click(function () {
        $('#sections').find(':selected').remove();
    });

    // $('#submit').click(function () {
    //     $('#sections').find('option').prop('selected', true);
    // });
});