<%@tag import="ch.hszt.mdp.domain.Activity.ActivityType"%>
<%@ tag description="Activity Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ attribute name="activity" required="true" rtexprvalue="true" type="ch.hszt.mdp.domain.Activity" %>

<c:set var="profile" value="<%=ch.hszt.mdp.domain.Activity.ActivityType.PROFILE%>"/>
<c:set var="status" value="<%=ch.hszt.mdp.domain.Activity.ActivityType.STATUS%>"/>
<c:set var="friend" value="<%=ch.hszt.mdp.domain.Activity.ActivityType.FRIEND%>"/>
<c:set var="like" value="<%=ch.hszt.mdp.domain.Activity.ActivityType.LIKE%>"/>
<c:set var="dislike" value="<%=ch.hszt.mdp.domain.Activity.ActivityType.DISLIKE%>"/>

<blockquote>

<c:choose>
	<c:when test="${profile eq activity.type}">
		<p class="${activity.type}">${activity.content}</p>
	</c:when>
	<c:when test="${status eq activity.type}">
		<p class="${activity.type}">${activity.content}</p>
	</c:when>
	<c:when test="${friend eq activity.type}">
		<p class="${activity.type}">${activity.content}</p>
	</c:when>
	<c:when test="${like eq activity.type}">
		<p class="${activity.type}">Likes «${activity.content}»</p>
	</c:when>
	<c:when test="${dislike eq activity.type}">
		<p class="${activity.type}">${activity.content}</p>
	</c:when>
</c:choose>
<small>
    <c:if test="${like ne activity.type}"><a href="" class="like" data-id="${activity.id}">Like</a> -</c:if> 
    <span class="easydate" title="<joda:format value="${activity.time}" style="FS" />">
        <joda:format value="${activity.time}" style="FS" />
    </span> -
    ${activity.user.prename} ${activity.user.surname}
</small>

</blockquote>
