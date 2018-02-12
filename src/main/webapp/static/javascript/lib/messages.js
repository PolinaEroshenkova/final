var language = $('html').attr('lang');
switch (language) {
    case 'en':
        $.extend($.validator.messages, {
            required: "This field must be filled in.",
            remote: "Please enter a valid value.",
            email: "Please enter a valid email address.",
            url: "Please enter a valid URL.",
            date: "Please enter a valid date.",
            dateISO: "Please enter a valid date in the ISO format.",
            number: "Please enter a number.",
            digits: "Please enter only digits.",
            creditcard: "Please enter a valid credit card number.",
            equalTo: "Please enter the same value again.",
            extension: "Please select the file with the correct extension.",
            maxlength: $.validator.format("Please enter no more than {0} characters."),
            minlength: $.validator.format("Please enter at least {0} characters."),
            rangelength: $.validator.format("Please enter a value from {0} to {1} characters long."),
            range: $.validator.format("Please enter a number from {0} to {1}."),
            max: $.validator.format("Please enter a number less than or equal to {0}."),
            min: $.validator.format("Please enter a number greater than or equal to {0}.")
        });
        break;
    case 'be':
        $.extend($.validator.messages, {
            required: "Гэта поле неабходна запоўніць.",
            remote: "Калі ласка, увядзіце правільнае значэнне.",
            email: "Калі ласка, увядзіце карэктны адрас электроннай пошты.",
            url: "Калі ласка, увядзіце карэктны URL.",
            date: "Калі ласка, увядзіце карэктную дату.",
            dateISO: "Калі ласка, увядзіце карэктную дату ў фармаце ISO.",
            number: "Калі ласка, увядзіце лік.",
            digits: "Калі ласка, уводзьце толькі лічбы.",
            creditcard: "Калі ласка, увядзіце правільны нумар крэдытнай карты.",
            equalTo: "Калі ласка, увядзіце такое ж значэнне яшчэ раз.",
            extension: "Калі ласка, абярыце файл з правільным пашырэннем.",
            maxlength: $.validator.format("Калі ласка, увядзіце не больш за {0} знакаў."),
            minlength: $.validator.format("Калі ласка, увядзіце ня менш {0} знакаў."),
            rangelength: $.validator.format("Калі ласка, увядзіце значэнне даўжынёй ад {0} да {1} знакаў."),
            range: $.validator.format("Калі ласка, увядзіце лік ад {0} да {1}."),
            max: $.validator.format("Калі ласка, увядзіце лік, меншае або роўнае {0}."),
            min: $.validator.format("Калі ласка, увядзіце лік, большая ці роўнае {0}.")
        });
        break;
    case 'ru':
        $.extend($.validator.messages, {
            required: "Это поле необходимо заполнить.",
            remote: "Пожалуйста, введите правильное значение.",
            email: "Пожалуйста, введите действительный адрес электронной почты.",
            url: "Пожалуйста, введите корректный URL.",
            date: "Пожалуйста, введите правильную дату.",
            dateISO: "Пожалуйста, введите корректную дату в формате ISO.",
            number: "Пожалуйста, введите число.",
            digits: "Пожалуйста, вводите только цифры.",
            creditcard: "Пожалуйста, введите правильный номер кредитной карты.",
            equalTo: "Пожалуйста, введите такое же значение еще раз.",
            extension: "Пожалуйста, выберите файл с правильным расширением.",
            maxlength: $.validator.format("Пожалуйста, введите не более {0} знаков."),
            minlength: $.validator.format("Пожалуйста, введите не менее {0} символов."),
            rangelength: $.validator.format("Пожалуйста, введите значение длиной от {0} до {1} знаков."),
            range: $.validator.format("Пожалуйста, введите число от {0} до {1}."),
            max: $.validator.format("Пожалуйста, введите число, меньшее или равное {0}."),
            min: $.validator.format("Пожалуйста, введите число, большее или равное {0}.")
        });
        break;
}
