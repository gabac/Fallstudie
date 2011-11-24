<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:layout title="Next Social Network">
    <jsp:body>
        <!-- Example row of columns -->
        <div class="page-header">
            <h1>Registration</h1>
        </div>
        <div class="row">
            <div class="span-one-third">
                <p>You'd like to join the Next Social Network…? What a great decision, you are awesome! Please fill out all fields, as we'd like to know everything about you - we are evil.</p>
            </div>
            <div class="span-two-thirds">
              <form:form modelAttribute="user" action="" method="post">
                <fieldset>
                    <legend>Personal information</legend>
                  <div class="clearfix">
                    <form:label for="prename" path="prename" cssErrorClass="error">Prename</form:label>
                    <div class="input">
                      <form:input path="prename" cssClass="xlarge" cssErrorClass="xlarge error" />
                      <span class="help-block error"><form:errors path="prename" /></span>
                    </div>
                  </div>
                  <div class="clearfix">
                    <form:label for="surname" path="surname" cssErrorClass="error">Surname</form:label>
                    <div class="input">
                      <form:input path="surname" cssClass="xlarge" cssErrorClass="xlarge error" />
                      <span class="help-block error"><form:errors path="surname" /></span>
                    </div>
                  </div>
                  <div class="clearfix">
                    <label for="birthdate">Birthdate</label>
                    <div class="input">
                      <input id="birthdate" name="birthdate" size="30"
                                    type="text" />
                      <span class="help-block">YYYY-MM-DD</span>
                    </div>
                  </div>
                  <div class="clearfix">
                    <label for="city">City</label>
                    <div class="input">
                      <input class="xlarge" id="city" name="city"
                                    size="30" type="text" />
                    </div>
                  </div>
                  <div class="clearfix">
                    <label for="photo">Photo</label>
                    <div class="input">
                      <input class="input-file" id="photo" name="photo"
                                            type="file">
                    </div>
                  </div>
                  </fieldset>
                  <fieldset>
                    <legend>Login information</legend>
                  <div class="clearfix">
                    <form:label for="email" path="email" cssErrorClass="error">E-Mail</form:label>
                    <div class="input">
                      <form:input path="email" cssClass="xlarge" cssErrorClass="xlarge error" />
                      <span class="help-block error"><form:errors path="email" /></span>
                    </div>
                  </div>
                  <div class="clearfix">
                    <label for="password">Password</label>
                    <div class="input">
                      <input class="xlarge" id="password" name="password"
                                    size="30" type="password" />
                    </div>
                  </div>
                  <div class="clearfix">
                    <label for="repeat">Repeat Password</label>
                    <div class="input">
                      <input class="xlarge" id="repeat" name="repeat"
                                    size="30" type="password" />
                    </div>
                  </div>
                  <div class="actions">
                    <input type="submit" class="btn primary"
                                value="Register">&nbsp;<a href="/" class="btn">Cancel</a>
                  </div>
                </fieldset>
              </form:form>
            </div>
        </div>
    </jsp:body>
</t:layout>
