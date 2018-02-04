<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="properties.content"/>

<div class="modal" tabindex="-1" role="dialog" id="answerModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form action="/conferences/">
                    <input type="hidden" name="command" value="answerQuestion">
                    <input type="hidden" name="id" value="">
                    <input type="hidden" name="login" value="">
                    <div class="form-group mx-auto">
                        <label for="answer"><fmt:message key="answer.answer"/></label>
                        <textarea class="form-control" name="answer" id="answer" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message key="answer.typeanswer"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
