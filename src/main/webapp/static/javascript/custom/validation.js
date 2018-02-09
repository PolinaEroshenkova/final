// function validateForm() {
//
//     $('#sections').find('option').prop('selected', true);
//
//
//     if($("#sections").find("option").length===0 || !$('#sections').val()){
//         $('#sections').addClass("validation");
//         return false;
//     } else{
//         $('#sections').removeClass("validation");
//     }
//
//     if($('.valid').val()===""){
//         $('.valid').addClass("validation");
//         return false;
//     } else{
//         $('select').removeClass("validation");
//     }
//
//     return true;
// }
$(document).ready(function () {

    $('#editprofile').validate({
        excluded: 'disabled',
        lang: $('html').attr('lang'),
        rules: {
            login: {
                required: true,
                maxlength: 30,
                nowhitespace: true
            },
            password: {
                required: true,
                maxlength: 30,
                nowhitespace: true
            },
            email: {
                required: true,
                email: true,
                maxlength: 255
            },
            surname: {
                required: true,
                maxlength: 35,
                nowhitespace: true,
                lettersonly: true
            },
            name: {
                required: true,
                maxlength: 35,
                nowhitespace: true,
                lettersonly: true
            },
            scope: {
                required: true,
                maxlength: 100
            },
            position: {
                maxlength: 50
            },
            company: {
                maxlength: 50
            }
        }
    });

    $('#entry').validate({
        lang: $('html').attr('lang'),
        rules: {
            sections: {
                required: true
            }
        }
    });

    $('#registration').validate({
        lang: $('html').attr('lang'),
        rules: {
            login: {
                required: true,
                maxlength: 30,
                nowhitespace: true
            },
            password: {
                required: true,
                maxlength: 30,
                nowhitespace: true
            },
            secpassword: {
                required: true,
                maxlength: 30,
                nowhitespace: true,
                equalTo: '#regpassword'
            },
            email: {
                required: true,
                email: true,
                maxlength: 255
            },
            surname: {
                required: true,
                maxlength: 35,
                nowhitespace: true,
                lettersonly: true
            },
            name: {
                required: true,
                maxlength: 35,
                nowhitespace: true,
                lettersonly: true
            },
            scope: {
                required: true,
                maxlength: 100
            },
            position: {
                maxlength: 50
            },
            company: {
                maxlength: 50
            }
        }
    });

    $('#faq-user').validate({
        lang: $('html').attr('lang'),
        rules: {
            question: {
                required: true,
                maxlength: 255,
                minlength: 2
            }
        }
    });

    $('#faq-admin').validate({
        lang: $('html').attr('lang'),
        rules: {
            questionadmin: {
                required: true,
                maxlength: 255,
                minlength: 2
            },
            answeradmin: {
                required: true,
                maxlength: 255,
                minlength: 2
            }
        }
    });

    $('#newconference').validate({
        lang: $('html').attr('lang'),
        rules: {
            topic: {
                required: true,
                maxlength: 255
            },
            number: {
                required: true,
                maxlength: 5,
                number: true
            },
            place: {
                required: true,
                maxlength: 60
            },
            datestart: {
                required: true
            },
            dateend: {
                required: true
            },
            timestart: {
                required: true
            },
            timeend: {
                required: true
            },
            deadline: {
                required: true
            }
        }
    });

    $('#LoginForm').validate({
        lang: $('html').attr('lang'),
        rules: {
            login: {
                required: true,
                maxlength: 30,
                nowhitespace: true
            },
            passsword: {
                required: true,
                maxlength: 30,
                nowhitespace: true
            }
        }
    });


    // $('#question').restrictLength($('#question').attr('maxlength'));
    // $('#question-admin').restrictLength($('#question-admin').attr('maxlength'));
    // $('#answer-admin').restrictLength($('#answer-admin').attr('maxlength'));

    $('#submit').click(function () {
        $('#sections').find('option').prop('selected', true);
    });
});
