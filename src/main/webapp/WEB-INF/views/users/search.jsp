<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Search results">
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="span-one-third">
                <div class="page-header">
                    <h1>Users</h1>
                </div>
                <ul class="flat">
                    <c:forEach items="${users}" var="profile">
                        <li>
                         <a href="/v1/users/${profile.id}" class="user-preview">
                            <c:if test="${profile.hasPhoto}">
                                <img src="/v1/users/${profile.id}/preview" width="50" height="50" />
                             </c:if>
                             <c:if test="${not profile.hasPhoto}">
                                 <img src="/resources/images/user.png" width="50" height="50" />
                             </c:if>
                             <span class="name">${profile.prename} ${profile.surname}</span>
                             <span class="city">${profile.city}</span>
                         </a>
                        </li>
                    </c:forEach>
                </ul>          
            </div>
            <div class="span-one-third">
                <div class="page-header">
                    <h1>Activity</h1>
                </div>
                <ul class="results">
                </ul>          
            </div>
            <div class="span-one-third">
                <div class="page-header">
                    <h1>Other</h1>
                </div>
                <ul class="results">
                </ul>          
            </div>
        </div>
    </jsp:body>
</t:layout>




