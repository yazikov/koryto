<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="name" required="true" type="java.lang.String" %>
<%@ attribute name="text" required="true" type="java.lang.String" %>
<%@ attribute name="value" required="false" type="java.lang.String" %>
<%@attribute name="inline" required="false" type="java.lang.Boolean" %>
<%@attribute name="required" required="false" type="java.lang.Boolean" %>

<c:choose>
    <c:when test="${inline}">
        <label class="col-sm-1 control-label" for="${name}">${text}</label>
        <div class="col-sm-2">
            <input class="form-control datepicker" type="text" pattern="\d\d\.\d\d\.\d\d\d\d" id="${name}" name="${name}" value="${value}" placeholder="дд.мм.гггг" <c:if test="${required}">required</c:if> >
        </div>
    </c:when>
    <c:otherwise>
        <div class="form-group" style="margin-bottom: 5px;">
            <label for="${name}">${text}</label>
            <input type="text" class="form-control datepicker" id="${name}" name="${name}" value="${value}" placeholder="дд.мм.гггг" <c:if test="${required}">required</c:if> >
            <span class="help-block with-errors"></span>
        </div>

    </c:otherwise>
</c:choose>
