<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/js/table.js" var="tableJs" />
<div class="sensor-storage-value-container">
    <input type="hidden" id="table-id-list"/>
    <input type="hidden" id="table-start"/>
    <input type="hidden" id="table-end"/>
    <input type="hidden" id="table-page"/>
    <input type="hidden" id="table-page-count" value="1"/>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Блок</th>
            <th>Начало</th>
            <th>Конец</th>
            <th>Значение</th>
            <th>Дата и время</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
<nav aria-label="...">
    <ul class="pager">
        <li><a id="table-prev" href="#">Предыдущие</a></li>
        <li><a id="table-next" href="#">Следующие</a></li>
    </ul>
</nav>

<script src="${tableJs}"></script>