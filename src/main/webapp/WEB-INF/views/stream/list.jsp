<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Next Social Network">
    <jsp:body>
        <div class="row">
        <div class="span-two-third status">
    		<form class="form-stacked share" action="" method="POST">
    			<div class="clearfix">
		            <label for="textarea">Share status:</label>
		            <div class="input">
		              <textarea class="xxlarge" id="statusUpdate" name="statusUpdate" rows="3"></textarea>
		            </div>
	            </div>
                <div class="clearfix">
                    <input type="submit" class="btn primary" value="Update status">
                </div>
            </form>
          </div>
          <div class="span-one-third">
                <div class="page-header">
                    <h2>Friend Requests</h2>
                </div>
                <ul>
                    <c:forEach items="${unaccepedFriends}" var="unaccepedFriends">
                        <li><a href="/v1/users/${unaccepedFriends.secondaryUser.id}">${unaccepedFriends.secondaryUser.prename} ${unaccepedFriends.secondaryUser.surname} </a><a
                            href="/v1/users/${profile.id}/accept/${unaccepedFriends.secondaryUser.id}"><button class="btn success"
                                    onclick="alert('${unaccepedFriends.secondaryUser.prename} ${unaccepedFriends.secondaryUser.surname} as friend accepted');">Accept</button></a>   <a
                            href="/v1/users/${profile.id}/ignore/${unaccepedFriends.secondaryUser.id}"><button class="btn danger"
                                    onclick="alert('${unaccepedFriends.secondaryUser.prename} ${unaccepedFriends.secondaryUser.surname} as friend ignored');">Ignore</button></a></li>
                        </br>
                    </c:forEach>
                </ul>
          </div>
          </div>
        <div class="row">
            <div class="span-one-third">
                <div class="page-header">
                    <h2>
                        <joda:format value="${today}" style="L-" />
                    </h2>
                </div>
                <c:forEach items="${stream.todaysActivities}" var="todaysActivity">
                	<t:activity activity="${todaysActivity}" />
	            </c:forEach>
            </div>
            <div class="span-one-third">
                <div class="page-header">
                    <h2>
                        <joda:format value="${yesterday}" style="L-" />
                    </h2>
                </div>
                <c:forEach items="${stream.yesterdaysActivities}" var="yesterdaysActivity">
                	<t:activity activity="${yesterdaysActivity}" />
	            </c:forEach>
            </div>
            <div class="span-one-third">
                <div class="page-header">
                    <h2>Past</h2>
                </div>
               <c:forEach items="${stream.pastActivities}" var="pastActivity">
                	<t:activity activity="${pastActivity}" />
	            </c:forEach>
            </div>
        </div>
    </jsp:body>
</t:layout>
