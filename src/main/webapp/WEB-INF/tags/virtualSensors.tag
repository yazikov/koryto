<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<spring:url value="/virtual/sensors/add" var="addUrl" />
<spring:url value="/virtual/sensors/type/add" var="addTypeUrl" />
<spring:url value="/virtual/sensors/value/add/" var="addValueUrl" />



<div class="row" style="margin-bottom: 5px; width: 98%;">
    <div class="col-sm-8"></div>
    <div class="col-sm-2">
        <a class="btn btn-default" href="${addUrl}">Добавить датчик</a>
    </div>
    <div class="col-sm-2">
        <a class="btn btn-default" href="${addTypeUrl}">Добавить тип датчика</a>
    </div>
</div>

<table class="table table-bordered">
    <thead>
    <tr>
        <th>Измерительный створ</th>
        <th>Номер датчика</th>
        <th>Пьезометр</th>
        <th>Измеряемый параметр</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${sensors}" var="sensor" varStatus="i" >
        <tr>
            <td><c:out value="${sensor.objMonitor}" /></td>
            <td><c:out value="${sensor.number}" /></td>
            <td><c:out value="${sensor.name}" /></td>
            <td><c:out value="${sensor.measParamTypeSig.discription}" /></td>
            <td>
                <a href="${addValueUrl}${sensor.idSensors}" class="btn btn-default btn-on">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> <span class="text">Добавить значение</span>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>