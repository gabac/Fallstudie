<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Next Social Network">
    <jsp:body>
        <div class="row">
            <div class="span-one-third">
                <div class="page-header">
                    <h2><fmt:formatDate value="${today}" pattern="dd MMMM yyyy" /></h2>
                </div>
                <c:forEach items="${activities}" var="activity">
                	<blockquote>
	                	<p>${activity.activityType} ${activity.content}</p>
	                	<small>${activity.user_id.prename} ${activity.user_id.surname}</small>
	                </blockquote>
	            </c:forEach>
            </div>
            <div class="span-one-third">
                <div class="page-header">
                    <h2><fmt:formatDate value="${yesterday}" pattern="dd MMMM yyyy" /></h2>
                </div>
                <blockquote>
                    <p>Fabian Vogler and 5 others liked: Blim Blam Blum</p>
                    <small>Hans Muster</small>
                </blockquote>
            </div>
            <div class="span-one-third">
                <div class="page-header">
                    <h2><fmt:formatDate value="${dayBeforeYesterday}" pattern="dd MMMM yyyy" /></h2>
                </div>
                <blockquote>
                    <p>Lives now in Baden</p>
                    <small>Cyril Gabathuler</small>
                </blockquote>
                <blockquote>
                    <p>Has a new profile pic</p>
                    <small>Raphael Marques</small>
                </blockquote>
            </div>
        </div>
    </jsp:body>
</t:layout>
