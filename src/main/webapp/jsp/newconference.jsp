<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Новая конференция</title>
    <link href="../static/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <link href="../static/css/bootstrap/bootstrap-datetimepicker-standalone.css" rel="stylesheet"/>
    <link href="../static/css/bootstrap/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
    <link href="../static/css/custom/style.css" rel="stylesheet"/>

</head>
<body>

<jsp:include page="part/header.jsp"/>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-12">
            <form role="form" method="post" action="Controller">
                <h2>Регистрация конференции</h2>
                <input type="hidden" name="command" value="RegisterConference"/>

                <div class="form-group mx-auto">
                    <input id="topic" name="topic" type="text" placeholder="Тема*"
                           class="form-control input-md" required="">
                </div>

                <div class="form-row">
                    <div class="form-group mx-auto col-6">
                        <input id="number" name="number" type="text" placeholder="Количество участников*"
                               class="form-control input-md" required="">
                    </div>

                    <div class="form-group mx-auto col-6">
                        <input id="place" name="place" type="text" placeholder="Место проведения*"
                               class="form-control input-md" required="">
                    </div>
                </div>

                <div class="form-row">

                    <div class="form-group mx-auto col-2" id="date-picker-start">
                        <label class="col-md-12 control-label label" for="date-start">Дата начала</label>
                        <div class="input-group date">
                            <input id="date-start" name="date-start" type="text" class="form-control" required="">
                        </div>
                    </div>

                    <div class="form-group mx-auto col-2" id="time-picker-start">
                        <label class="col-md-12 control-label label" for="time-start">Время начала</label>
                        <div class="input-group date">
                            <input id="time-start" name="time-start" type="text" class="form-control" required="">
                        </div>
                    </div>

                    <div class="form-group mx-auto col-2" id="date-picker-end">
                        <label class="col-md-12 control-label label" for="date-end">Дата окончания</label>
                        <input id="date-end" name="date-end" type="text" class="form-control" required="">
                    </div>

                    <div class="form-group mx-auto col-2" id="time-picker-end">
                        <label class="col-md-12 control-label label" for="time-end">Время окончания</label>
                        <div class="input-group date">
                            <input id="time-end" name="time-end" type="text" class="form-control" required="">
                        </div>
                    </div>


                    <div class="form-group mx-auto col-3" id="date-picker-deadline">
                        <label class="col-md-12 control-label label" for="deadline">Дедлайн подачи заявок</label>
                        <input id="deadline" name="deadline" type="text" class="form-control" required="">
                    </div>

                </div>

                <div class="form-row">
                    <div class="form-group mx-auto col-10">
                        <input id="section" name="section" type="text" class="form-control"
                               placeholder="Название секции">
                    </div>

                    <div class="form-group mx-auto col-2">
                        <button type="button" name="add-section" class="btn btn-primary" id="add-section">Добавить
                            секцию
                        </button>
                    </div>
                </div>

                <div class="form-group mx-auto">
                    <label class="col-md-4 control-label label" for="sections">Секции</label>
                    <select id="sections" name="sections" class="form-control" multiple="multiple" size="5"
                            required>
                    </select>
                    <button type="button" class="btn btn-secondary" id="delete-section">Удалить секцию</button>
                </div>

                <button type="submit" class="btn btn-primary" id="submit">Добавить конференцию</button>

            </form>
        </div>
    </div>
</div>

<jsp:include page="part/footer.jsp"/>

<div id="modal">
    <jsp:include page="modal/sign.jsp"/>
</div>

<script src="../static/javascript/lib/moment.min.js"></script>
<script src="../static/javascript/lib/jquery.js"></script>
<script src="../static/javascript/bootstrap/bootstrap.bundle.min.js"></script>
<script src="../static/javascript/bootstrap/bootstrap-datetimepicker.js"></script>
<script src="../static/javascript/custom/newconference.js"></script>
<script src="../static/javascript/custom/login.js"></script>
</body>
</html>
