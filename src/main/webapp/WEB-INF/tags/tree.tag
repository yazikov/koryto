<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/js/tree.js" var="treeJs" />

<spring:url value="/resources/js/jstree.js" var="jsTreeJs" />
<spring:url value="/resources/css/tree/default/style.min.css" var="jsTreeCss" />
<spring:url value="/resources/images/type-" var="typeImg" />
<link href="${jsTreeCss}" rel="stylesheet" />
<script src="${jsTreeJs}"></script>

<script>
    var typeImg = '${typeImg}';
</script>

<div id="tree">
    <ul>
        <c:forEach items="${roots}" var="root" varStatus="i">
            <c:set var="type" value="1" />
            <c:forEach var="sensor" items="${sensors}">
                <c:if test="${sensor.objMonitor == root && sensor.type > type && sensor.type != 4}">
                    <c:set var="type" value="${sensor.type}" />
                </c:if>
            </c:forEach>
            <li data-id="${root}" data-jstree='{"icon":"${typeImg}${type}.png"}' data-type="${type}" data-litype="root">
                <c:out value="${root}" />
                <ul>
                    <c:forEach items="${sensors}" var="sensor">
                        <c:if test="${sensor.objMonitor == root}">
                            <li id="tree-sensor-${sensor.idSensors}" data-id="${sensor.idSensors}" data-jstree='{"icon":"${typeImg}${sensor.type}.png"}' data-litype="sensor" data-type="${sensor.type}"><c:out value="${sensor.name}" /></li>
                        </c:if>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
    </ul>
</div>

<script src="${treeJs}"></script>