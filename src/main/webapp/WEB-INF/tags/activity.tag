<%@ tag description="Activity Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ attribute name="activity" required="true" rtexprvalue="true" type="ch.hszt.mdp.domain.Activity" %>

<blockquote>
	<p class="${activity.activityType}">${activity.content}</p>
	<small>${activity.user.prename} ${activity.user.surname} - <joda:format value="${activity.time}" style="F-" /></small>
</blockquote>
