<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/js/map.js" var="mapJs" />


<c:set value="${sensorValues.get(sensors.get(0).getId())}" var="sensor" />
<c:set value="${sensors.get(0)}" var="firstSensor" />
<c:set value="${sensors.get(sensors.size() - 1)}" var="lastSensor" />
<input type="hidden" id="minValue" value="${minValue}" />
<input type="hidden" id="maxValue" value="${maxValue}" />
<input type="hidden" id="minSensorLength" value="${firstSensor.lengthValue}" />
<input type="hidden" id="maxSensorLength" value="${lastSensor.lengthValue}" />
<input type="hidden" id="lastDate" value="${sensor.date}" />
<input type="hidden" id="lastTime" value="${sensor.time}" />
<script>
    var mapImage = '${mapImage}',
        mapCabelImage = '${mapCabelImage}',
        mapBlockImage = '${mapBlockImage}',
        mapCabelBlockImage = '${mapCabelBlockImage}';
</script>
<svg id="map" width="100%" height="100%" class="map" viewBox="0 0 ${mapHeight} ${mapWidth}"
     preserveAspectRatio="none" style="background-image: url('/resources/images/${mapImage}')">
    <c:forEach items="${sensors}" var="sensor" varStatus="i">
        <c:set var="value" value="${sensorValues.get(sensor.id)}" />
        <g class="sensor sensor-${sensor.id}" data-id="${sensor.id}" data-value="${value.value}" data-title="${sensor.lengthValue}">
            <rect class="region-rect" x="${sensor.x}" y="${sensor.y}"
                  width="${sensor.width}" height="${sensor.height}" fill="${value.color}" stroke="blue"></rect>
            <title>${sensor.lengthValue}</title>
        </g>
    </c:forEach>
</svg>

<script src="${mapJs}"></script>
