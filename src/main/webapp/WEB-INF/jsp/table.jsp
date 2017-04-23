<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: nikolay
  Date: 21.11.15
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/js/flotr2.min.js" var="js" />

<%
  SimpleDateFormat df = new SimpleDateFormat("dd.MM.yy");
  SimpleDateFormat hf = new SimpleDateFormat("hh:mm:ss");
  request.setAttribute("df", df);
  request.setAttribute("hf", hf);
%>

<t:page notShow="true">
  <jsp:body>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th>Участок объекта автоматизации</th>
        <th>Номер датчика</th>
        <th>Измеряемый параметр</th>
        <th>Значение</th>
        <th>Дата</th>
        <th>Время</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${sensors}" var="sensor">
        <tr>
          <td><c:out value="${sensor.passportParamSys.objMonitor}" /></td>
          <td><c:out value="${sensor.passportParamSys.number}" /></td>
          <td><c:out value="${sensor.passportParamSys.measParamTypeSig.discription}" /></td>
          <td><c:out value="${sensor.valueMeas}" /></td>
          <td><c:out value="${sensor.dateMeas != null ? df.format(sensor.dateMeas) : ''}" /></td>
          <td><c:out value="${sensor.timeMeas != null ? hf.format(sensor.timeMeas) : ''}" /></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

  </jsp:body>
</t:page>
