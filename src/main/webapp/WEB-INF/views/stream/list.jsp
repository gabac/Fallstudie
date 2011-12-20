<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Next Social Network">
    <jsp:attribute name="footer">
        <div id="modal-friendship" class="modal hide" style="display: none; ">
            <div class="modal-header">
                <a href="#" class="close">×</a>
                <h3>Friend request</h3>
            </div>
            <div class="modal-body">
                <p>Are you sure to accept the Friend Request? The other person will see all your status updates.</p>
            </div>
            <div class="modal-footer">
                <a href="/v1/users/${profile.id}/accept/${user.id}" class="btn primary">Accept Friendship</a>
                <a class="btn secondary">Cancel</a>
            </div>
        </div>
        <script>
        $('#modal-friendship .btn.secondary').click(function () {
            $('#modal-friendship').modal('hide');
        });
        $('.accept-friendship').click(function () {
            $('#modal-friendship a').attr('href', $(this).attr('href'));
        });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
        <div class="span-two-thirds status">
    		<form class="form-stacked share" action="" method="POST">
                <fieldset>
    			<div class="clearfix">
                    <div class="input">
                      <textarea class="xxlarge" id="statusUpdate" name="statusUpdate" rows="3" placeholder="Share your status…"></textarea>
                    </div>
                </div>
                <div class="clearfix">
                    <div class="input">
                      <select class="small"><option>Friends</option><option>Everyone</option></select>
                      <span class="help-inline">Who should be able to see it?</span>
                      <input type="submit" class="btn primary" value="Update status">
                    </div>
	            </div>
                </fieldset>
            </form>
          </div>
          <div class="span-one-third">
                    <h3>Friend Requests</h3>
                <ul class="flat">
                    <c:forEach items="${unaccepedFriends}" var="unaccepedFriends">
                        <li>
                            
                         <a href="/v1/users/${unaccepedFriends.secondaryUser.id}" class="user-preview">
                            <c:if test="${unaccepedFriends.secondaryUser.hasPhoto}">
                                <img src="/v1/users/${unaccepedFriends.secondaryUser.id}/preview" width="50" height="50" />
                             </c:if>
                             <c:if test="${not unaccepedFriends.secondaryUser.hasPhoto}">
                                 <img src="/resources/images/user.png" width="50" height="50" />
                             </c:if>
                             <span class="name">${unaccepedFriends.secondaryUser.prename} ${unaccepedFriends.secondaryUser.surname}</span>
                         </a>
                            <a href="/v1/users/${user.id}/accept/${unaccepedFriends.secondaryUser.id}" class="accept-friendship" data-controls-modal="modal-friendship" data-backdrop="true" data-keyboard="true">Accept</a>, 
                            <a href="/v1/users/${profile.id}/ignore/${unaccepedFriends.secondaryUser.id}" onclick="alert('${unaccepedFriends.secondaryUser.prename} ${unaccepedFriends.secondaryUser.surname} as friend ignored');">Ignore</a>
                        </li>
                    </c:forEach>
                </ul>
          </div>
          </div>
          <section>
        <div class="row">
            <div class="span-one-third">
                <div class="page-header">
                    <h2>
                        <joda:format value="${today}" style="L-" />
                    </h2>
                </div>
                <c:forEach items="${stream.todaysActivities}" var="todaysActivity">
                	<t:activity activity="${todaysActivity}" />
	            </c:forEach>
            </div>
            <div class="span-one-third">
                <div class="page-header">
                    <h2>
                        <joda:format value="${yesterday}" style="L-" />
                    </h2>
                </div>
                <c:forEach items="${stream.yesterdaysActivities}" var="yesterdaysActivity">
                	<t:activity activity="${yesterdaysActivity}" />
	            </c:forEach>
            </div>
            <div class="span-one-third">
                <div class="page-header">
                    <h2>Past</h2>
                </div>
               <c:forEach items="${stream.pastActivities}" var="pastActivity">
                	<t:activity activity="${pastActivity}" />
	            </c:forEach>
            </div>
        </div>
        </section>
    </jsp:body>
</t:layout>
