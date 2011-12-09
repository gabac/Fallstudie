<%@ tag description="Layout Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" required="true" rtexprvalue="true"%>
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

<link rel="shortcut icon" href="/favicon.ico" />
<jsp:invoke fragment="header" />
</head>

<body>

    <div class="topbar">
        <div class="fill">
            <div class="container">
                <a class="brand" href="/">Next Social Network</a>
                <c:if test="${not empty pageContext.request.remoteUser}" scope="request" var="remoteUser">
                <div class="pull-right">
                    <ul class="tabs">
                        <li class="dropdown" data-dropdown="dropdown"><a href="/v1/users/${user.id}" class="dropdown-toggle"><c:out value="${user.prename} ${user.surname}" /></a>
                            <ul class="dropdown-menu">
                                <li><a href="/v1/users/${user.id}">View Profile</a></li>
                                <li><a href="/v1/users/${user.id}/edit">Edit Profile</a></li>
                                <li class="divider"></li>
                                <li><a href="/v1/auth/logout">Logout</a></li>
                            </ul></li>
                    </ul>
                </div>
                </c:if>
                <form class="pull-right">
                    <input type="text" placeholder="Search">
                </form>
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


    <!-- <script src="http://yui.yahooapis.com/3.4.1/build/yui/yui-min.js"></script> -->
    <script src="/resources/javascripts/jquery-1.7.1.min.js"></script>
    <script src="/resources/javascripts/jquery.easydate.js"></script>
    <script src="/resources/bootstrap/js/bootstrap-dropdown.js"></script>
    <script>
    $(function () {
        $(".easydate").easydate();
    });
    </script>
    <jsp:invoke fragment="footer" />
</body>
</html>

