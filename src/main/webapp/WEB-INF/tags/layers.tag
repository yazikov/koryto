<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="layer-container">
    <div class="checkbox">
        <label>
            <input id="leakControlOn" class="mapLayerChange" type="checkbox"> Контроль протечек
        </label>
    </div>
    <div class="checkbox">
        <label>
            <input id="layoutPlatesOn" class="mapLayerChange"  type="checkbox"> Раскладка плит
        </label>
    </div>
</div>