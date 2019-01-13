<%--
  Created by IntelliJ IDEA.
  User: nikolay
  Date: 06.09.15
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page>
  <jsp:body>
    <div class="col col-left col-xs-2">
      <c:if test="${showLayers}">
        <div class="block">
          <div class="panel panel-default">
            <div class="panel-heading">Слои</div>
            <div class="panel-body">
              <t:layers />
            </div>
          </div>
        </div>
      </c:if>
      <div class="block">
        <div class="panel panel-default panel-graphic-setting">
          <div class="panel-heading">Построение графика</div>
          <div class="panel-body">
            <form action='#' method="get" style="padding: 3px;">
              <t:calendar name="startDate" text="Дата с" value="" />
              <t:calendar name="endDate" text="Дата по" value="" />
              <div class="form-group"  style="margin-bottom: 5px;">
                <button type="button" class="btn btn-default show-graphic">Построить график</button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="block">
        <div class="panel panel-default">
          <div class="panel-heading">Список датчиков</div>
          <div class="panel-body">
            <t:sensors />
          </div>
        </div>
      </div>
    </div>
    <div class="col col-center col-xs-10" data-size="8">
      <div class="block">
        <div class="panel panel-default">
          <div class="panel-heading text-center">Карта данных о протечках</div>
          <div class="panel-body">
            <ul class="nav nav-tabs">
              <li role="presentation" class="active"><a data-toggle="tab" href="#map">Схема</a></li>
              <li role="presentation"><a id="graphic-tab" data-toggle="tab" href="#graphic">Оперативный график</a></li>
              <li role="presentation"><a id="storage-graphic-tab" data-toggle="tab" href="#storage-graphic">График</a></li>
              <li role="presentation"><a id="settings-tab" data-toggle="tab" href="#settings">Настройки</a></li>
            </ul>
            <div class="tab-content">
              <div id="map" class="map_container tab-pane fade in active">
                <t:map />
              </div>
              <div id="graphic" class="tab-pane fade">
                <t:graphic />
              </div>
              <div id="storage-graphic" class="tab-pane fade">
                <div class="custom-graphic">

                </div>
              </div>
              <div id="settings" class="tab-pane fade">
                <t:settings />
              </div>
            </div>



          </div>
        </div>
      </div>
    </div>

  </jsp:body>
</t:page>