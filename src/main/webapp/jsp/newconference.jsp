<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_EN'}"/>
<fmt:setBundle basename="properties.content"/>

<html lang="${language}">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title><fmt:message key="newconference.title"/></title>
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
            <form role="form" method="post" action="/conferences/" class="new-conference" id="newconference"
                  name="newconference">
                <h2><fmt:message key="newconference.header"/></h2>
                <input type="hidden" name="command" id="command" value="RegisterConference"/>

                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger" id="ErrorAlert">
                        <strong><fmt:message key="error.servererror"/> </strong>
                    </div>
                </c:if>

                <div class="form-group mx-auto">
                    <input id="topic" name="topic" type="text"
                           placeholder="<fmt:message key="newconference.form.topic"/>*"
                           class="form-control input-md valid">
                </div>

                <div class="form-row">
                    <div class="form-group mx-auto col-6">
                        <input id="number" name="number" type="text"
                               placeholder="<fmt:message key="newconference.form.numberofparticipants"/>*"
                               class="form-control input-md valid">
                    </div>

                    <div class="form-group mx-auto col-6">
                        <input id="place" name="place" type="text"
                               placeholder="<fmt:message key="newconference.form.place"/>*"
                               class="form-control input-md valid" maxlength="60">
                    </div>
                </div>

                <div class="form-row">

                    <div class="form-group mx-auto col-2" id="date-picker-start">
                        <label class="col-md-12 control-label label" for="datestart"><fmt:message
                                key="newconference.form.datestart"/></label>
                        <div class="input-group date">
                            <input id="datestart" name="datestart" type="text" class="form-control valid">
                        </div>
                    </div>

                    <div class="form-group mx-auto col-2" id="time-picker-start">
                        <label class="col-md-12 control-label label" for="timestart"><fmt:message
                                key="newconference.form.timestart"/></label>
                        <div class="input-group date">
                            <input id="timestart" name="timestart" type="text" class="form-control valid">
                        </div>
                    </div>

                    <div class="form-group mx-auto col-2" id="date-picker-end">
                        <label class="col-md-12 control-label label" for="dateend"><fmt:message
                                key="newconference.form.dateend"/></label>
                        <input id="dateend" name="dateend" type="text" class="form-control valid">
                    </div>

                    <div class="form-group mx-auto col-2" id="time-picker-end">
                        <label class="col-md-12 control-label label" for="timeend"><fmt:message
                                key="newconference.form.timeend"/></label>
                        <div class="input-group date">
                            <input id="timeend" name="timeend" type="text" class="form-control valid">
                        </div>
                    </div>


                    <div class="form-group mx-auto col-3" id="date-picker-deadline">
                        <label class="col-md-12 control-label label" for="deadline"><fmt:message
                                key="newconference.form.deadline"/></label>
                        <input id="deadline" name="deadline" type="text" class="form-control valid">
                    </div>

                </div>

                <div class="form-row">
                    <div class="form-group mx-auto col-10">
                        <input id="section" name="section" type="text" class="form-control"
                               placeholder="<fmt:message key="newconference.form.namesection"/>">
                    </div>

                    <div class="form-group mx-auto col-2">
                        <button type="button" name="add-section" class="btn btn-primary" id="add-section"><fmt:message
                                key="newconference.form.addsection"/>
                        </button>
                    </div>
                </div>

                <div class="form-group mx-auto">
                    <label class="col-md-4 control-label label" for="sections"><fmt:message
                            key="newconference.form.sections"/></label>
                    <select id="sections" name="sections" class="form-control valid" multiple="multiple" size="5">
                    </select>
                    <button type="button" class="btn btn-secondary" id="delete-section" name="delete-section">
                        <fmt:message
                            key="newconference.form.deletesection"/></button>
                </div>

                <button type="submit" class="btn btn-primary" id="submit" name="submit"><fmt:message
                        key="newconference.form.addconference"/></button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="part/footer.jsp"/>

<div id="modal">
    <jsp:include page="modal/sign.jsp"/>
</div>

<script src="../static/javascript/lib/moment-with-locales.min.js"></script>
<script src="../static/javascript/lib/jquery.js"></script>
<script src="../static/javascript/bootstrap/bootstrap.bundle.min.js"></script>
<script src="../static/javascript/bootstrap/bootstrap-datetimepicker.js"></script>
<script src="../static/javascript/custom/newconference.js"></script>
<script src="../static/javascript/custom/login.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/additional-methods.min.js"></script>
<script src="../static/javascript/custom/validation.js"></script>
<script src="../static/javascript/lib/messages.js"></script>
</body>
</html>
