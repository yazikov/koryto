<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<spring:url value="/virtual/sensors/save" var="editUrl" />



<form role="form" action="${editUrl}" method="post" class="form-horizontal validator-form" data-toggle="validator">
    <input type="hidden" name="id" value="${sensor.idSensors}"/>
    <t:combobox items="${types}" name="objMonitor" text="Измерительный створ" value="${sensor.objMonitor}" multiple="false" required="true"/>
    <t:combobox items="${sensorTypes}" name="measParamTypeSig" text="Тип датчика" value="${sensor.measParamTypeSig.discription}" multiple="false" required="true" />
    <t:text name="name" text="Номер датчика" value="${sensor.number}" required="true" />
    <t:text name="number" text="Пьезометр" value="${sensor.name}" required="true" />
    <c:choose>
        <c:when test="${sensor.idSensors == null}">
            <t:number name="k1Low" text="Коэффициент отпускания" required="true" />
            <t:number name="k1" text="Критерий k1" required="true" />

            <t:number name="value" text="Значение датчика" required="true" />
            <t:calendar name="date" text="Дата измерения" required="true" />
            <t:time name="time" text="Время измерения" required="true" />
        </c:when>
    </c:choose>
    <div class="form-group"  style="margin-bottom: 5px;">
        <button type="submit" class="btn btn-default">Добавить</button>
    </div>
</form>