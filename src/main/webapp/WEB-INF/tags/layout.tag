<%@tag description="Layout Tag" pageEncoding="UTF-8"%>
<%@attribute name="title" fragment="true"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><jsp:invoke fragment="title" /></title>
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
                    Logged in as <a href="#">Fabian Vogler</a>
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

