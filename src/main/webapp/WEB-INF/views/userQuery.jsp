<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>Nick Query</title>
</head>

<body>
<form method="post">
Nick
<input type="text" name="email" value="${email}" />
<input type="submit" value="Query" />
</form>

<table border="1">
  <tr>
    <th>Name</th>
    <th>Email</th>
    <th>Nick</th>
  </tr>
  <c:forEach items="${users}" var="user">
  <tr>
    <td>${user.name}</td>
    <td>${user.email}</td>
    <td>${user.nick}</td>
  </tr>
  </c:forEach>
</table>
</body>
</html>