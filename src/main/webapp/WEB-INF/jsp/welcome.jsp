<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>Welcome</title>
</head>

<body>
<h2>New Social Network</h2>

Today is <fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />.
</body>
</html>