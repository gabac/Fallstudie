<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Next Social Network">
    <jsp:body>
    	<div class="status">
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
        <div class="row">
            <div class="span-one-third">
                <div class="page-header">
                    <h2><joda:format value="${today}" style="L-" /></h2>
                </div>
                <c:forEach items="${stream.todaysActivities}" var="todaysActivity">
                	<t:activity activity="${todaysActivity}" />
	            </c:forEach>
            </div>
            <div class="span-one-third">
                <div class="page-header">
                    <h2><joda:format value="${yesterday}" style="L-" /></h2>
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
