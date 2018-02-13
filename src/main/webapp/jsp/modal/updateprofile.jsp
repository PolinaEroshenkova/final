<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_EN'}"/>
<fmt:setBundle basename="properties.content"/>

<div class="modal" tabindex="-1" role="dialog" id="updateProfileModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form action="/conferences/" id="editprofile" name="editprofile" method="post">
                    <input type="hidden" name="command" value="updateUserInfo">
                    <div class="form-group mx-auto">
                        <label class="col-md-12 control-label label" for="surname"><fmt:message
                                key="profile.surname"/></label>
                        <div class="input-group">
                            <input id="surname" name="surname" type="text" class="form-control valid">
                        </div>
                    </div>
                    <div class="form-group mx-auto">
                        <label class="col-md-12 control-label label" for="name"><fmt:message
                                key="profile.name"/></label>
                        <div class="input-group date">
                            <input id="name" name="name" type="text" class="form-control valid">
                        </div>
                    </div>
                    <div class="form-group mx-auto">
                        <label class="col-md-12 control-label label" for="login"><fmt:message
                                key="profile.login"/></label>
                        <div class="input-group date">
                            <input id="login" name="login" type="text" class="form-control valid">
                        </div>
                    </div>
                    <input id="password" name="password" type="hidden" class="form-control valid" value="">
                    <div class="form-group mx-auto">
                        <label class="col-md-12 control-label label" for="email"><fmt:message
                                key="profile.email"/></label>
                        <div class="input-group date">
                            <input id="email" name="email" type="text" class="form-control valid">
                        </div>
                    </div>
                    <div class="form-group mx-auto">
                        <label class="col-md-12 control-label label" for="scope"><fmt:message
                                key="profile.scope"/></label>
                        <div class="input-group date">
                            <input id="scope" name="scope" type="text" class="form-control valid">
                        </div>
                    </div>
                    <div class="form-group mx-auto">
                        <label class="col-md-12 control-label label" for="position"><fmt:message
                                key="profile.position"/></label>
                        <div class="input-group date">
                            <input id="position" name="position" type="text" class="form-control valid">
                        </div>
                    </div>
                    <div class="form-group mx-auto">
                        <label class="col-md-12 control-label label" for="company"><fmt:message
                                key="profile.company"/></label>
                        <div class="input-group date">
                            <input id="company" name="company" type="text" class="form-control valid">
                        </div>
                    </div>
                    <div class="form-group mx-auto">
                        <button type="submit" class="btn btn-primary" id="submit">
                            <fmt:message key="profile.edit"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
