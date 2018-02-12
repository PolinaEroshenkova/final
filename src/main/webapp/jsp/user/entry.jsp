<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_EN'}"/>
<fmt:setBundle basename="properties.content"/>

<html lang="${language}">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title><fmt:message key="entry.title"/></title>
    <link href="../../static/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <link href="../../static/css/custom/style.css" rel="stylesheet"/>

</head>

<body>

<jsp:include page="../part/header.jsp"/>
<section class="py-5">
    <div class="container toppad">
        <form class="form-horizontal need-validation" action="/conferences/" method="post" name="entry" id="entry">
            <fieldset>

                <legend><fmt:message key="entry.header"/></legend>
                <input type="hidden" name="command" value="entryProcessing">

                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger" id="ErrorAlert">
                        <strong><fmt:message key="error.servererror"/></strong>
                    </div>
                </c:if>

                <div class="form-group">
                    <label class="col-md-4 label control-label" for="conference"><fmt:message
                            key="entry.form.conference"/></label>
                    <div class="col-md-12">
                        <output id="conference" name="conference" type="text" class="form-control input-md">
                            ${conference.topic}</output>
                    </div>
                </div>

                <table class="table table-hover">
                    <thead>
                    <tr bgcolor="#87cefa">
                        <th><fmt:message key="entry.form.place"/></th>
                        <th><fmt:message key="entry.form.datestart"/></th>
                        <th><fmt:message key="entry.form.dateend"/></th>
                        <th><fmt:message key="entry.form.deadline"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${conference.place}</td>
                        <td><ctg:date-time type="datetime">${conference.begin}</ctg:date-time></td>
                        <td><ctg:date-time type="datetime">${conference.end}</ctg:date-time></td>
                        <td><ctg:date-time type="date">${conference.deadline}</ctg:date-time></td>
                    </tr>
                    </tbody>
                </table>

                <div class="form-group">
                    <label class="col-md-4 control-label label" for="sections"><fmt:message
                            key="entry.form.choosesection"/></label>
                    <div class="col-md-12 select-valid">
                        <select id="sections" name="sections" class="form-control choose" multiple="multiple" size="5">
                            <c:forEach items="${conference.sections}" var="section">
                                <option name="idsection" value="${section.idsection}">${section.title}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary" id="submit-form"><fmt:message
                                key="entry.form.signup"/></button>
                        <a href="/conferences/conferences" class="btn btn-secondary"><fmt:message
                                key="entry.goback"/></a>
                    </div>
                </div>

            </fieldset>
        </form>
    </div>
</section>

<div hidden>
    <p id="state">${state}</p>
</div>

<jsp:include page="../part/footer.jsp"/>

<div id="modal">
    <jsp:include page="../modal/sign.jsp"/>
    <jsp:include page="../modal/success.jsp"/>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../../static/javascript/bootstrap/bootstrap.bundle.js"></script>
<script src="../../static/javascript/custom/showdialog.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/additional-methods.min.js"></script>
<script src="../../static/javascript/custom/validation.js"></script>
<script src="../../static/javascript/lib/messages.js"></script>

</body>
</html>
