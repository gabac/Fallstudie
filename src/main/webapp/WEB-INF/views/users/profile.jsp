<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Next Social Network">
    <div class="page-header">
        <h1>
            ${user.prename} ${user.surname} <small>${user.city}</small>
        </h1>
    </div>

    <p>
        born
        <joda:format value="${user.birthdate}" style="F-" />
    </p>

    <div class="row">
        <div class="span-one-third">
            <p>
                <img src="/v1/users/${user.id}/image" width="300" />
            </p>
        </div>
        <div class="span-one-third">
            <h3>Stream</h3>
            <ul>
                <c:forEach items="${activity}" var="activity">
                    <li><form:label for="activities" path="activities" cssErrorClass="error">${activity.typ} ${activity.text}</form:label></li>
                </c:forEach>
            </ul>
        </div>
        <div class="span-one-third">
            <h3>Friends</h3>
            <ul>
                <c:forEach items="${friends}" var="friend">
                    <li><form:label for="friends_surname" path="friends_surname" cssErrorClass="error">${friend.secondaryUser.surname} ${friend.secondaryUser.prename}</form:label></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</t:layout>