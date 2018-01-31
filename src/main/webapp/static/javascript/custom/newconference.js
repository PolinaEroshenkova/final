$(document).ready(function () {
    $('#date-start').datetimepicker({
        format: 'L'
    });
    $('#date-end').datetimepicker({
        format: 'L'
    });
    $('#deadline').datetimepicker({
        format: 'L'
    });

    $('#time-start').datetimepicker({
        format: 'LT'
    });

    $('#time-end').datetimepicker({
        format: 'LT'
    });

    $('#add-section').click(function () {
        var section = $('#section').val();
        $("#sections").append('<option>' + section + '</option>');
        $('#section').val('');
    });

    $('#delete-section').click(function () {
        $('#sections :selected').remove();
    });

    $('#submit').click(function () {
        $('#sections').find('option').prop('selected', true);
    });
});