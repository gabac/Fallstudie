<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Next Social Network">
<body>
    <div class="page-header">
        <h1>${user.prename} ${user.surname} <small>${user.city}</small></h1>
    </div>
    
    <p>born <joda:format value="${user.birthdate}" style="F-" /></p>

    <h3>Friends</h3>
        </br>
        <c:forEach items="${friends}" var="friend">
        <form:label for="friends_surname" path="friends_surname" cssErrorClass="error">${friend.secondaryUser.surname} ${friend.secondaryUser.prename}</form:label>
        </br>
        </c:forEach>
</body>
</t:layout>