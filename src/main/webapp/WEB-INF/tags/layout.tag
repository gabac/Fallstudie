<%@tag description="Layout Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" required="true"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>${title}</title>
<!-- Le styles -->
<link href="/resources/bootstrap/bootstrap.css" rel="stylesheet">
<link href="/resources/stylesheets/layout.css" rel="stylesheet">
<style type="text/css">
body {
    padding-top: 60px;
}
</style>

<jsp:invoke fragment="header" />
</head>

<body>

    <div class="topbar">
        <div class="fill">
            <div class="container">
                <a class="brand" href="#">Next Social Nextwork</a>
                <p class="pull-right">
                    <c:if test="${not empty pageContext.request.remoteUser}" scope="request" var="remoteUser">hello <a href="#"><c:out value="${pageContext.request.remoteUser}" /></a> – <a href="users/logout">logout</a></c:if>
                </p>
            </div>
        </div>
    </div>

    <div class="container">

        <jsp:doBody />

        <footer>
            <p>&copy; Next Social Network 2011</p>
        </footer>

    </div>
    <!-- /container -->


    <script src="http://yui.yahooapis.com/3.4.1/build/yui/yui-min.js"></script>
    <jsp:invoke fragment="footer" />
</body>
</html>

