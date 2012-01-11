<%@tag import="ch.hszt.mdp.domain.Activity.ActivityType"%>
<%@ tag description="Activity Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="lk" uri="http://social.farnsworth.ch/tags/likes" %>
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
<c:if test="${fn:length(activity.likes) > 0}">
<p class="likes">
    <c:forEach items="${activity.likes}" var="like" varStatus="loop">
        ${like.user.prename}
        <c:if test="${!loop.last}">, </c:if>
    </c:forEach>
    likes this.
</p>
</c:if>
<small data-id="${activity.id}">
    <c:if test="${like ne activity.type}">
        <c:choose>
            <c:when test="${lk:likes(user, activity)}">
                <a href="" class="unlike">Unlike</a> -
            </c:when>
            <c:otherwise>
                <a href="" class="like">Like</a> -
            </c:otherwise>
        </c:choose>
    </c:if> 
    <a href="/v1/activity/${activity.id}" class="easydate" title="<joda:format value="${activity.time}" style="FS" />">
        <joda:format value="${activity.time}" style="FS" />
    </a> -
    ${activity.user.prename} ${activity.user.surname}
</small>

</blockquote>
