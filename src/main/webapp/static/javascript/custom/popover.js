$(document).ready(function () {
    var toggles = $('[data-toggle="popover"]');
    toggles.each(function (index) {
        var id = $(this).data('id');
        $(this).popover({
            html: true,
            trigger: 'hover',
            content: function () {
                var result = '#' + id;
                return $(result).html();
            }
        });
    });
});