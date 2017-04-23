<%--
  Created by IntelliJ IDEA.
  User: nikolay
  Date: 18.10.15
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/enter/history" var="formAction" />

<t:page>
  <jsp:body>
    <div class="col1">
      <div class="col1_resize"></div>
      <c:if test="${!operate}">
        <div class="block">
          <div class="panel panel-default">
            <div class="panel-heading">Фильтрация архива</div>
            <div class="panel-body">
              <form action='${formAction}' method="get" style="padding: 3px;">

                <t:calendar name="startDate" text="Дата с" value="${startDate}" />
                <t:calendar name="endDate" text="Дата по" value="${endDate}" />
                <t:combobox name="signal" text="Сигнал" items="${signals}" value="${signal}" multiple="true"/>
                <t:combobox name="type" text="Тип объекта" items="${types}" value="${type}" multiple="true"/>

                <div class="form-group"  style="margin-bottom: 5px;">
                    <button type="submit" class="btn btn-default">Фильтрация</button>
                </div>

              </form>
            </div>
          </div>
        </div>
      </c:if>

      <div class='block'>

        <div class="panel panel-default">
          <div class="panel-heading">Условные обозначения</div>
          <div class="panel-body">
            <div id="sensor-info" class="alert">
              <table class="table table-noborder">
                <tr>
                  <td><div class="sensor_type_3 legend_block"></div></td>
                  <td>Аварийные сигналы</td>
                </tr>
                <tr>
                  <td><div class="sensor_type_2 legend_block"></div></td>
                  <td>Предупреждающие сигналы</td>
                </tr>
                <tr>
                  <td><div class="sensor_type_1 legend_block"></div></td>
                  <td>Сигналы состояния</td>
                </tr>
              </table>
            </div>
          </div>
        </div>


      </div>
    </div>
    <div class="col2 no_right">
      <h3 style="text-align: center;">
        <c:choose>
          <c:when test="${operate}">
            Оперативный журнал
          </c:when>
          <c:otherwise>
            Архив событий
          </c:otherwise>
        </c:choose>
      </h3>
      <div class="journal_container">
        <t:journal operate="${operate}" />
      </div>
    </div>
  </jsp:body>
</t:page>
