<%@ tag description="Layout Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>
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
                <a class="brand" href="/">Next Social Network</a>
                <p class="pull-right">
                    <input type="text" placeholder="Search">
                    <c:if test="${not empty pageContext.request.remoteUser}" scope="request" var="remoteUser">
                        &nbsp; <a href="/v1/users/${user.id}"><c:out value="${user.prename} ${user.surname}" /></a>(<a href="/v1/users/${user.id}/e">edit</a>) – <a href="/v1/auth/logout">Logout</a>
                    </c:if>
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

