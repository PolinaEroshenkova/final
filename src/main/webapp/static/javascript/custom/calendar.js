$(document).ready(function () {
    $('#date-start').datetimepicker({
        minDate: moment(),
        defaultDate: moment()
    });
    $('#date-end').datetimepicker();
    $('#date-deadline').datetimepicker();
});
