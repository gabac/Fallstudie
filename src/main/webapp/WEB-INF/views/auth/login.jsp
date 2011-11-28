<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Next Social Network">
    <jsp:body>
      <div class="hero-unit">
        <h1>Hello, friend!</h1>
        <p>We are ready to welcome you in our Social Network. Your Next Social Network and your friends are waiting for you. Please register yourself or login below.</p>
        <p><a class="btn primary large" href="/v1/users/">Sign-up now!</a></p>
      </div>
    
        <!-- Example row of columns -->
        <div class="row">
            <div class="span-one-third">
                <div class="page-header">
                    <h1>About</h1>
                </div>
                <p>The Next Social Network is just another Social Network. Users can sign-in with the form on the right, new ones can sign-up with the <a href="/v1/users/">registration</a>.</p>
            </div>
            <div class="span-two-thirds">
                <div class="page-header">
                    <h1>Login</h1>
                </div>
                <form method="POST" action="j_security_check">
                  <fieldset>
                  <div class="clearfix">
                    <label for="j_username">E-Mail</label>
                    <div class="input">
                      <input id="j_username" name="j_username" size="30" type="text" />
                    </div>
                  </div>
                  <div class="clearfix">
                    <label for="j_password">Password</label>
                    <div class="input">
                      <input id="j_password" name="j_password" size="30" type="password" />
                    </div>
                  </div>
                  <div class="actions">
                    <input type="submit" class="btn primary" value="Login">
                  </div>
                </fieldset>
              </form>
            </div>
        </div>
    </jsp:body>
</t:layout>
