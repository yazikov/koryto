<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="name" required="true" type="java.lang.String" %>
<%@ attribute name="text" required="true" type="java.lang.String" %>
<%@ attribute name="value" required="false" type="java.lang.String" %>
<%@attribute name="inline" required="false" type="java.lang.Boolean" %>
<%@attribute name="required" required="false" type="java.lang.Boolean" %>

<div class="form-group" style="margin-bottom: 5px;">
    <label for="${name}">${text}</label>
    <input type="number" step="any" class="form-control" id="${name}" name="${name}" value="${value}" <c:if test="${required}">required</c:if> />
    <span class="help-block with-errors"></span>
</div>