<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="${profile.prename} ${profile.surname}, ${profile.city}">
    <jsp:attribute name="footer">
    	<h3>Search result</h3>
                <ul>
                    <c:forEach items="${users}" var="users">
                        <li><a href="/v1/users/${users.id}">${users.surname} ${users.prename}</a></li></br>
                    </c:forEach>
                </ul>
    </jsp:attribute>
</t:layout>