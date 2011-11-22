<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>Welcome</title>
</head>

<body>
<h2><spring:message code="welcome.message"
        text="Welcome to Court Reservation System" /></h2>

Today is <fmt:formatDate value="${today}" pattern="yyyy-MM-dd:hh:mm:ss" />.
</body>
</html>