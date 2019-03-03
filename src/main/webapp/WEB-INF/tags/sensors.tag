<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="sensor-container">
    <table class="table table-striped table-hover">
        <c:forEach items="${sensors}" var="sensor" varStatus="i">
            <c:set var="value" value="${sensorValues.get(sensor.id)}" />
            <tr class="sen sen-${sensor.id}" data-id="${sensor.id}" data-title="${sensor.lengthValue}">
                <td><input type="checkbox" name="sensor-id-${sensor.id}"/></td>
                <td class="length">${sensor.id}</td>
                <td class="length">${sensor.startLengthValue}</td>
                <td class="length">${sensor.endFileLengthValue}</td>
                <td class="length">${sensor.x}</td>
                <td class="length">${sensor.y}</td>
                <td class="val">${value.value} C<sup>0</sup></td>
            </tr>
        </c:forEach>
    </table>
</div>

<%--<div class="list-group sensor-list">--%>
    <%--<c:forEach items="${sensors}" var="sensor" varStatus="i">--%>
        <%--<c:set var="value" value="${sensorValues.get(sensor.id)}" />--%>
        <%--<a href="#" class="list-group-item list-block"--%>
           <%--data-id="${sensor.id}" data-title="${sensor.lengthValue}">${sensor.lengthValue} : ${value.value}</a>--%>
    <%--</c:forEach>--%>
<%--</div>--%>