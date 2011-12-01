<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Next Social Network">
<body>
<h1>Profile Information</h1>

  <div class="container-fluid">
    <div class="sidebar">
	<h3>User Information</h3>
	</br>
      <span class="label" for="prename" path="prename" cssErrorClass="error">Prename:</span></br></br>
      <span class="label" for="surname" path="surname" cssErrorClass="error">Surname:</span></br></br>
      <span class="label" for="birthdate" path="birthdate" cssErrorClass="error">Birthdate:</span></br></br>
      <span class="label" for="city" path="city" cssErrorClass="error">City:</span></br></br>
      <span class="label" for="email" path="email" cssErrorClass="error">E-Mail:</span></br></br>
 	</br>
 	<h3>Friends</h3>
 	 	</br>
 	 	<c:forEach items="${friends}" var="friend">
  		<form:label for="friends_surname" path="friends_surname" cssErrorClass="error">${friend.secondaryUser.surname} ${friend.secondaryUser.prename}</form:label>
  		</br>
  		</c:forEach>
    </div>
    <div class="content">
	</br>
      <form:label for="prename" path="prename" cssErrorClass="error">${user.prename}</form:label></br></br>
      <form:label for="surname" path="surname" cssErrorClass="error">${user.surname}</form:label></br></br>
      <form:label for="birthday" path="birthday" cssErrorClass="error">${user.birthdate}</form:label></br></br>
      <form:label for="city" path="city" cssErrorClass="error">${user.city}</form:label></br></br>
      <address><p>${user.email}</p></address>
    </div>
  </div>

</body>
</t:layout>