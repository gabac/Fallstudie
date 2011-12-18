<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:layout title="Privacy Settings">
    <jsp:body>
        <div class="page-header">
            <h1>Privacy Settings</h1>
        </div>
        <div class="row">
            <div class="span-one-third">
                <p>Something about privacy settings.</p>
            </div>
            <div class="span-two-thirds">
              <form:form modelAttribute="user" action="/v1/users/${profile.id}/privacy" method="post">
                <spring:hasBindErrors name="user">
                    <div class="alert-message error">
                        <p><strong>We're not kidding, please fix the errors!</strong> <form:errors /></p>
                    </div>
                </spring:hasBindErrors>
                <fieldset>
                  <div class="clearfix">
                    <form:label for="privacyProfile" path="privacyProfile" cssErrorClass="error">Personal information</form:label>
                    <div class="input">
                       <form:select path="privacyProfile" items="${privacies}" cssErrorClass="error" />
                      <span class="help-block error"><form:errors path="privacyProfile" /></span>
                    </div>
                  </div>
                  <div class="clearfix">
                    <form:label for="privacyEmail" path="privacyEmail" cssErrorClass="error">E-Mail address</form:label>
                    <div class="input">
                       <form:select path="privacyEmail" items="${privacies}" cssErrorClass="error" />
                      <span class="help-block error"><form:errors path="privacyEmail" /></span>
                    </div>
                  </div>
                  <div class="actions">
                    <input type="submit" class="btn primary"
                                value="Save">&nbsp;<a href="/v1/users/${user.id}" class="btn">Cancel</a>
                  </div>
                </fieldset>
              </form:form>
            </div>
        </div>
    </jsp:body>
</t:layout>
