<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Login for Next Social Network">
    <jsp:body>
    
        <!-- Example row of columns -->
        <div class="page-header">
            <h1>Login</h1>
        </div>
        <div class="row">
            <div class="span-one-third">
                <p>You'd like to join the Next Social Network…? What a great decision, you are awesome! Please fill out all fields, as we'd like to know everything about you - we are evil.</p>
            </div>
            <div class="span-two-thirds">
              <form method="POST" action="j_security_check">
                  <fieldset>
                    <legend>Login information</legend>
                  <div class="clearfix">
                    <label for="j_username">E-Mail</label>
                    <div class="input">
                      <input class="xlarge" id="j_username" name="j_username"
                                    size="30" type="text" />
                    </div>
                  </div>
                  <div class="clearfix">
                    <label for="j_password">Password</label>
                    <div class="input">
                      <input class="xlarge" id="j_password" name="j_password"
                                    size="30" type="password" />
                    </div>
                  </div>
                  <div class="actions">
                    <input type="submit" class="btn primary"
                                value="Login">
                  </div>
                </fieldset>
              </form>
            </div>
        </div>
    </jsp:body>
</t:layout>
