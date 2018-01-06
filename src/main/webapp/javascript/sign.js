$(document).ready(function () {
    var state = $("#state").attr("value");
    if (state !== "") {
        var button = $("#signbutton");
        button.text("Личный кабинет");
        button.removeAttr("data-target");
        button.removeAttr("data-toggle");
        button.attr("href", "controller?command=ProfileGeneration");
    }
});