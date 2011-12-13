<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:layout title="${user.prename} ${user.surname}, ${user.city}">
    <jsp:body>
        <!-- Example row of columns -->
        <div class="page-header">
            <h1>Update</h1>
        </div>
        <div class="row">
            <div class="span-one-third">
                <p>Please update your profile here.</p>
            </div>
            <div class="span-two-thirds">
              <form:form modelAttribute="user" action="/v1/users/${userid}" method="post" enctype="multipart/form-data">
                <spring:hasBindErrors name="user">
                    <div class="alert-message error">
                        <p><strong>We're not kidding, please fix the errors!</strong> <form:errors /></p>
                    </div>
                </spring:hasBindErrors>
                <fieldset>
                    <legend>Personal information</legend>
                  <div class="clearfix">
                    <form:label for="prename" path="prename" cssErrorClass="error">Prename</form:label>
                    <div class="input">
                      <form:input path="prename" cssClass="xlarge" cssErrorClass="xlarge error" value="${user.prename}"/>
                      <span class="help-block error"><form:errors path="prename" /></span>
                    </div>
                  </div>
                  <div class="clearfix">
                    <form:label for="surname" path="surname" cssErrorClass="error" >Surname</form:label>
                    <div class="input">
                      <form:input path="surname" cssClass="xlarge" cssErrorClass="xlarge error" value="${user.surname}" />
                      <span class="help-block error"><form:errors path="surname" /></span>
                    </div>
                  </div>
                  <div class="clearfix">
                    <form:label for="birthdate" path="birthdate" cssErrorClass="error">Birthdate</form:label>
                    <div class="input">
                      <form:input path="birthdate" cssClass="xlarge" cssErrorClass="xlarge error" value="${birthdate}" />
                      <span class="help-block">Format YYYY-MM-DD</span>
                      <span class="help-block error"><form:errors path="birthdate" /></span>
                    </div>
                  </div>
                  <div class="clearfix">
                    <form:label for="city" path="city" cssErrorClass="error">City</form:label>
                    <div class="input">
                      <form:input path="city" cssClass="xlarge" cssErrorClass="xlarge error" value="${user.city}"/>
                      <span class="help-block error"><form:errors path="city" /></span>
                    </div>
                  </div>
                  <div class="clearfix">
                    <form:label for="photo" path="photo" cssErrorClass="error">Photo</form:label>
                    <div class="input">
                        <c:if test="${user.hasPhoto}">
                            <img src="/v1/users/${user.id}/thumbnail" width="300" />
                        </c:if>
                        <c:if test="${not user.hasPhoto}">
                            <img src="/resources/images/user.png" width="300" height="300" />
                        </c:if>
                      <input class="input-file" id="photo" name="photo" type="file">
                      <span class="help-block error"><form:errors path="photo" /></span>
                    </div>
                  </div>
                  </fieldset>
                  <fieldset>
                    <legend>Login information</legend>
                  <div class="clearfix">
                    <form:label for="email" path="email" cssErrorClass="error">E-Mail</form:label>
                    <div class="input">
                      <form:input path="email" cssClass="xlarge" cssErrorClass="xlarge error" value="${user.email}" />
                      <span class="help-block error"><form:errors path="email" /></span>
                    </div>
                  </div>
                  <div class="clearfix">
                    <form:label for="password" path="password" cssErrorClass="error">Password</form:label>
                    <div class="input">
                      <form:password path="password" cssClass="xlarge" cssErrorClass="xlarge error" value="" />
                      <span class="help-block error"><form:errors path="password" /></span>
                    </div>
                  </div>
                  <div class="clearfix">
                    <form:label for="repeat" path="repeat" cssErrorClass="error">Repeat Password</form:label>
                    <div class="input">
                      <form:password path="repeat" cssClass="xlarge" cssErrorClass="xlarge error" value="" />
                      <span class="help-block error"><form:errors path="repeat" /></span>
                    </div>
                  </div>
                  <div class="actions">
                    <input type="submit" class="btn primary"
                                value="Update">&nbsp;<a href="/v1/users/${user.id}" class="btn">Cancel</a>
                  </div>
                </fieldset>
              </form:form>
            </div>
        </div>
    </jsp:body>
</t:layout>
