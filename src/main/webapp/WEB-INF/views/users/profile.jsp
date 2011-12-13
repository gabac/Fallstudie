<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="${profile.prename} ${profile.surname}, ${profile.city}">
    <jsp:attribute name="footer">
        <div id="modal-friendship" class="modal hide" style="display: none; ">
            <div class="modal-header">
                <a href="#" class="close">×</a>
                <h3>Add as Friend</h3>
            </div>
            <div class="modal-body">
                <p>${profile.prename} needs to confirm your friendship. Ask him for a friendship?</p>
            </div>
            <div class="modal-footer">
                <a href="/v1/users/${profile.id}/ask/${user.id}" class="btn primary">Ask for Friendship</a>
                <a class="btn secondary">Cancel</a>
            </div>
        </div>
        <script>
        $('#modal-friendship .btn.secondary').click(function () {
            $('#modal-friendship').modal('hide');
        });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="page-header">
            <h1>
                ${profile.prename} ${profile.surname} <small>${profile.city}</small>
            </h1>
        </div>
    
        <div class="row">
            <div class="span-one-third">
                <p>
                    <c:if test="${profile.hasPhoto}">
                        <img src="/v1/users/${profile.id}/thumbnail" width="300" />
                    </c:if>
                    <c:if test="${not profile.hasPhoto}">
                        <img src="/resources/images/user.png" width="300" height="300" />
                    </c:if>
                </p>
            </div>
            <div class="span-one-third">
                <c:forEach items="${profile.activities}" var="activity">
                    <blockquote>
                        <p class="${activity.activityType}">${activity.content}</p>
                        <small><joda:format value="${activity.time}" style="FS" /></small>
                    </blockquote>
                </c:forEach>
            </div>
            <div class="span-one-third">
                <c:if test="${profile.id != user.id}">
                    <p><a class="btn primary" data-controls-modal="modal-friendship" data-backdrop="true" data-keyboard="true">Add as Friend</a></p>
                </c:if>
    
                <p>
                    E-Mail: <a href="mailto:${profile.email}">${profile.email}</a><br /> born
                    <joda:format value="${profile.birthdate}" style="F-" />
                </p>
    
                <h3>Friends</h3>
                <ul>
                    <c:forEach items="${accepedFriends}" var="accepedFriends">
                        <li><a href="/v1/users/${accepedFriends.secondaryUser.id}">${accepedFriends.secondaryUser.prename} ${accepedFriends.secondaryUser.surname}</a></li>
                    </c:forEach>
                </ul>
                <h3>Open friend requests</h3>
                <ul>
                    <c:forEach items="${unaccepedFriends}" var="unaccepedFriends">
                        <li><a href="/v1/users/${unaccepedFriends.secondaryUser.id}">${unaccepedFriends.secondaryUser.prename} ${unaccepedFriends.secondaryUser.surname} </a><a href="/v1/users/${profile.id}/accept/${unaccepedFriends.secondaryUser.id}"><button class="btn success" onclick="alert('${unaccepedFriends.secondaryUser.prename} ${unaccepedFriends.secondaryUser.surname} as friend accepted');">Accept</button></a>   <a href="/v1/users/${profile.id}/ignore/${unaccepedFriends.secondaryUser.id}"><button class="btn danger" onclick="alert('${unaccepedFriends.secondaryUser.prename} ${unaccepedFriends.secondaryUser.surname} as friend ignored');">Ignore</button></a></li></br>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </jsp:body>
</t:layout>