<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="subTitle" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="notShow" required="false" type="java.lang.Boolean" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>АСИУ</title>

    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/resources/js/jquery-1.11.3.min.js" var="jqueryJs" />
    <spring:url value="/resources/js/jquery-ui/jquery-ui.min.js" var="jqueryUIJs" />
    <spring:url value="/resources/css/jquery-ui/jquery-ui.min.css" var="jqueryUICss" />
    <spring:url value="/resources/js/main.js" var="mainJs" />
    <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
    <spring:url value="/resources/js/validator.min.js" var="validatorJs" />

    <spring:url value="/resources/js/select2/select2.css" var="select2Css" />
    <spring:url value="/resources/js/select2/select2-bootstrap.css" var="select2BootstrapCss" />

    <spring:url value="/resources/js/select2/select2.min.js" var="select2Js" />

    <spring:url value="/resources/js/flotr2.min.js" var="graphicJs" />

    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${jqueryUICss}" rel="stylesheet" />
    <link href="${select2Css}" rel="stylesheet" />
    <link href="${select2BootstrapCss}" rel="stylesheet" />
    <link href="${mainCss}" rel="stylesheet" />

    <script src="${jqueryJs}"></script>
    <script src="${jqueryUIJs}"></script>
    <script src="${select2Js}"></script>
    <script src="${bootstrapJs}"></script>
    <script src="${validatorJs}"></script>
    <script src="${graphicJs}"></script>

    <spring:url value="/" var="baseURL"/>

    <script>
        var baseURL = '${baseURL}';
    </script>

</head>

<body>
<div class="wrapper">
    <div class="container-wrapper row">
        <jsp:doBody/>
    </div>

</div>

<script src="${mainJs}"></script>

</body>
</html>