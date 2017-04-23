<%--
  Created by IntelliJ IDEA.
  User: nikolay
  Date: 21.11.15
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:page>
  <jsp:body>
    <div class="col2 no_right no_left">
      <h3 style="text-align: center;">
            Неавтоматизированная КИА
      </h3>

      <c:choose>
        <c:when test="${message != null && !message.isEmpty()}">
          <div class="alert alert-success" role="alert"><c:out value="${message}"/></div>
        </c:when>
        <c:when test="${error != null && !error.isEmpty()}">
          <div class="alert alert-danger" role="alert"><c:out value="${error}"/></div>
        </c:when>
      </c:choose>

      <div class="journal_container">
        <t:virtualSensors />
      </div>
    </div>
  </jsp:body>
</t:page>
