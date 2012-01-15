<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Next Social Network">
    <jsp:attribute name="footer">
        <script>
        $('.like').live('click', function () {
            $(this).text('Unlike').attr('class', 'unlike');
            var activity = $(this).parent();
            $.post('/v1/activity/' + activity.data('id') + '/like', function (data) {
                var fresh = $('blockquote', data);
                $('.easydate', fresh).easydate();
                activity.parent().replaceWith(fresh);
            });
            return false;
        });
        $('.unlike').live('click', function () {
            $(this).text('Like').attr('class', 'like');
            var activity = $(this).parent();
            $.post('/v1/activity/' + activity.data('id') + '/unlike', function (data) {
                var fresh = $('blockquote', data);
                $('.easydate', fresh).easydate();
                activity.parent().replaceWith(fresh);
            });
            return false;
        });
        </script>
    </jsp:attribute>
    <jsp:body>
            <t:activity activity="${activity}" />
    </jsp:body>
</t:layout>
