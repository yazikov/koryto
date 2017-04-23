<%@tag pageEncoding="UTF-8" %>
<%@ tag import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="row setting-up no-margin">
    <div class="col col-xs-4">
        <div class="panel panel-default criterion-setting">
            <c:set var="criterion" value="${criterions[0]}" />
            <div class="panel-heading">Редактирование критерия: <span name="id">${criterion.id}</span></div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <input type="hidden" id="criterion-id" name="criterion-id" value="${criterion.id}"/>
                    <div class="form-group margin-top">
                        <label class="control-label col-xs-2" for="criterion-start">Начало</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" name="start" id="criterion-start" value="${criterion.startValue}"/>
                        </div>
                        <label class="control-label col-xs-2" for="criterion-end">Конец</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" name="end" id="criterion-end"  value="${criterion.endValue}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-8 col-xs-4">
                            <button type="button" class="btn btn-default save-criterion">Обновить</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="col col-xs-4">
        <div class="panel panel-default criterion-setting">
            <c:set var="criterionStart" value="${criterions[0]}" />
            <c:set var="criterionEnd" value="${criterions[criterions.size() - 1]}" />
            <div class="panel-heading">Редактирование интервала критериев</div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <div class="form-group margin-top">
                        <label class="control-label col-xs-2" for="criterion-interval-start">Начало</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" name="start" id="criterion-interval-start" value=""/>
                        </div>
                        <label class="control-label col-xs-2" for="criterion-interval-end">Конец</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" name="end" id="criterion-interval-end"  value=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-8 col-xs-4">
                            <button type="button" class="btn btn-default save-criterion-interval">Обновить</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="col col-xs-4">
        <div class="panel panel-default">
            <div class="panel-heading">Редактирования данных начала кабеля</div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <div class="form-group margin-top">
                        <label class="control-label col-xs-6" for="startRange">Начало промежутка</label>
                        <div class="col-xs-6">
                            <input type="text" class="form-control" name="startRange" id="startRange" value="${range.start}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-8 col-xs-4">
                            <button type="button" class="btn btn-default save-range">Обновить</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<div class="row setting-down no-margin">
    <div class="col col-xs-12">
        <div class="panel panel-default">
        <div class="panel-heading">Список критериев</div>
            <div class="panel-body">
                <div class="criterions-container">
                    <table class="table table-responsive table-hover">
                        <thead>
                        <tr>
                            <td>Идентификатор</td>
                            <td>Начало</td>
                            <td>Конец</td>
                            <td>Цвет</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${criterions}" var="criterion">
                            <tr id="cr-${criterion.id}" class="criterion">
                                <td class="cr-id">${criterion.id}</td>
                                <td class="cr-start">${criterion.startValue}</td>
                                <td class="cr-end">${criterion.endValue}</td>
                                <td style="background: ${criterion.color};"></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>