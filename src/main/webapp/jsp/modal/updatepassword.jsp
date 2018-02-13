<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_EN'}"/>
<fmt:setBundle basename="properties.content"/>

<div class="modal" tabindex="-1" role="dialog" id="updatePasswordModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form action="/conferences/" id="editpassword" name="editpassword" method="post">
                    <input type="hidden" name="command" value="updatePassword">
                    <div class="form-group mx-auto">
                        <label class="col-md-12 control-label label" for="updatepassword"><fmt:message
                                key="profile.password"/></label>
                        <div class="input-group">
                            <input id="updatepassword" name="updatepassword" type="password" class="form-control">
                        </div>
                    </div>
                    <div class="form-group mx-auto">
                        <label class="col-md-12 control-label label" for="secpassword"><fmt:message
                                key="profile.confirmpassword"/></label>
                        <div class="input-group">
                            <input id="secpassword" name="secpassword" type="password" class="form-control">
                        </div>
                    </div>
                    <button type="submit" name="submit" class="btn btn-primary"><fmt:message
                            key="profile.edit"/></button>
                </form>
            </div>
        </div>
    </div>
</div>