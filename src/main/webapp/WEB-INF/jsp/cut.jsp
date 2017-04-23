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

<t:page>
  <jsp:body>
    <div class="col col-center col-xs-9" data-size="9">
      <div class="block">
        <div class="panel panel-default">
          <div class="panel-heading text-center">Карта данных о протечках</div>
          <div class="panel-body">
            <div class="map_container">
              <c:set value="800" var="height" />
              <c:choose>
                <c:when test="${cutId == 1}">
                  <c:set value="316" var="height" />
                </c:when>
                <c:when test="${cutId == 2}">
                  <c:set value="416" var="height" />
                </c:when>
                <c:when test="${cutId == 3}">
                  <c:set value="395" var="height" />
                </c:when>
                <c:when test="${cutId == 4}">
                  <c:set value="399" var="height" />
                </c:when>
                <c:when test="${cutId == 5}">
                  <c:set value="321" var="height" />
                </c:when>
                <c:when test="${cutId == 6}">
                  <c:set value="487" var="height" />
                </c:when>
              </c:choose>
              <t:map cutId="${cutId}" height="${height}" />
            </div>
          </div>
        </div>
      </div>

    </div>
    <div class="col col-xs-3">

      <div class="block">
        <div class="block">
          <div class="panel panel-default">
            <div class="panel-heading text-center"><t:btnHide /> Информация о датчике</div>
            <div class="panel-body">
              <div id="sensor-info">

              </div>
            </div>
          </div>
        </div>
        <div class="block">
          <div class="panel panel-default">
            <div class="panel-heading text-center">Оперативный журнал событий</div>
            <div class="panel-body">
              <div id="sensor-logging">

              </div>
            </div>
          </div>
        </div>
      </div>

    </div>
  </jsp:body>
</t:page>
