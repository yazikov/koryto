<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="name" required="true" type="java.lang.String" %>
<%@ attribute name="text" required="true" type="java.lang.String" %>
<%@ attribute name="value" required="false" type="java.lang.String" %>
<%@ attribute name="items" required="true" type="java.util.List" %>
<%@ attribute name="multiple" required="false" type="java.lang.Boolean" %>
<%@attribute name="required" required="false" type="java.lang.Boolean" %>

<div class="form-group" style="margin-bottom: 5px;">
    <label for="${name}">${text}</label>
    <select class="form-control" id="${name}" name="${name}" <c:if test="${multiple}">multiple="multiple"</c:if> <c:if test="${required}">required</c:if>>
        <option value=""></option>
        <c:forEach items="${items}" var="item">
            <option ${value.contains(item.id) ? "selected" : ''} value="${item.id}">${item.name}</option>
        </c:forEach>
    </select>
    <span class="help-block with-errors"></span>
</div>