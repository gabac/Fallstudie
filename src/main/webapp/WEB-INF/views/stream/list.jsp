<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Next Social Network">
    <jsp:body>
        <div class="row">
            <div class="span-one-third">
                <div class="page-header">
                    <h2><joda:format value="${today}" style="L-" /></h2>
                </div>
                <c:forEach items="${stream.todaysActivities}" var="todaysActivity">
                	<blockquote>
	                	<p class="${todaysActivity.activityType}">${todaysActivity.content}</p>
	                	<small>${todaysActivity.user_id.prename} ${todaysActivity.user_id.surname} - <joda:format value="${todaysActivity.time}" style="F-" /></small>
	                </blockquote>
	            </c:forEach>
            </div>
            <div class="span-one-third">
                <div class="page-header">
                    <h2><joda:format value="${yesterday}" style="L-" /></h2>
                </div>
                <c:forEach items="${stream.yesterdaysActivities}" var="yesterdaysActivity">
                	<blockquote>
	                	<p class="${yesterdaysActivity.activityType}">${yesterdaysActivity.content}</p>
	                	<small>${yesterdaysActivity.user_id.prename} ${yesterdaysActivity.user_id.surname} - <joda:format value="${yesterdaysActivity.time}" style="F-" /></small>
	                </blockquote>
	            </c:forEach>
            </div>
            <div class="span-one-third">
                <div class="page-header">
                    <h2>Past</h2>
                </div>
               <c:forEach items="${stream.pastActivities}" var="pastActivity">
                	<blockquote>
	                	<p class="${pastActivity.activityType}">${pastActivity.content}</p>
	                	<small>${pastActivity.user_id.prename} ${pastActivity.user_id.surname} - <joda:format value="${pastActivity.time}" style="F-" /></small>
	                </blockquote>
	            </c:forEach>
            </div>
        </div>
    </jsp:body>
</t:layout>
