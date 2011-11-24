<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout>
    <jsp:attribute name="title">Next Social Network</jsp:attribute>
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
              <form>
                <fieldset>
                    <legend>Personal information</legend>
                  <div class="clearfix">
                    <label for="prename">Prename</label>
                    <div class="input">
                      <input class="xlarge" id="prename" name="prename"
                                    size="30" type="text" />
                    </div>
                  </div>
                  <div class="clearfix">
                    <label for="surname">Surname</label>
                    <div class="input">
                      <input class="xlarge" id="surname" name="surname"
                                    size="30" type="text" />
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
                    <label for="email">E-Mail</label>
                    <div class="input">
                      <input class="xlarge" id="email" name="email"
                                    size="30" type="text" />
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
                                value="Register">&nbsp;<button
                                type="reset" class="btn">Reset</button>
                  </div>
                </fieldset>
              </form>
            </div>
        </div>
    </jsp:body>
</t:layout>
