<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="${profile.prename} ${profile.surname}, ${profile.city}">
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="page-header">
            <h1>
                ${profile.prename} ${profile.surname} <small>${profile.city}</small>
            </h1>
        </div>
    
        <div class="row">
            <div class="span-one-third">
            	<h3>Search result</h3>
                		<ul>
                    		<c:forEach items="${users}" var="users">
                    			<a href="/v1/users/${users.id}">
                    			${profile.hasPhoto}
                    			<li><c:if test="${profile.hasPhoto}">
                        			<img src="/v1/users/${users.id}/thumbnailPeople" width="50" />
                    			</c:if>
                    			<c:if test="${not profile.hasPhoto}">
                        			<img src="/resources/images/user.png" width="50" height="50" />
                    			</c:if>
                        			${users.surname} ${users.prename}</a></li></br>
                    		</c:forEach>
                		</ul>          
            </div>
        </div>
    </jsp:body>
</t:layout>




