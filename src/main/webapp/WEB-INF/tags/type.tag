<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<spring:url value="/virtual/sensors/type/save" var="editUrl" />



<form role="form" action="${editUrl}" method="post" class="form-horizontal validator-form" data-toggle="validator">
    <input type="hidden" name="id" value="${type.idUstavka}"/>
    <t:text name="description" text="Наименование" value="${type.discription}" required="true" />
    <div class="form-group"  style="margin-bottom: 5px;">
        <button type="submit" class="btn btn-default">Добавить</button>
    </div>
</form>