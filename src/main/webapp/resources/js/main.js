/**
 * Created by nikolay on 06.09.15.
 */
var mapContainer;

$(document).ready(function() {

    mapContainer = $('.map_container');

    //setColumnSize();
    $(window).resize(function() {
        setCorrectHeight();
    });

    setCorrectHeight();

    $('select').select2();

    $(".datepicker").datepicker({
        firstDay: 1,
        dateFormat: "dd.mm.yy",
        dayNames: [ "Воскресение", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота" ],
        dayNamesMin: [ "Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб" ],
        dayNamesShort: [ "Воск", "Пон", "Втор", "Сред", "Четв", "Пят", "Суб" ],
        monthNames: [ "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" ],
        monthNamesShort: [ "Янв", "Фев", "Март", "Апр", "Май", "Июнь", "Июль", "Авг", "Сен", "Окт", "Нояб", "Дек" ]
    });

    $(".validator-form").validator().on('submit', function (e) {
        if ($("#startDate") && $("#endDate")) {
            var start = $("#startDate").val();
            var end = $("#endDate").val();
            var parts = start.split('\.');
            var startDate = new Date(parts[2] + '-' + parts[1] + '-' + parts[0]);
            parts = end.split('\.');
            var endDate = new Date(parts[2] + '-' + parts[1] + '-' + parts[0]);
            if (endDate.getTime() - startDate.getTime() < 0) {
                alert("Начальная дата должна быть меньше или равна чем конечная!");
                e.preventDefault();
            }
            var action = $(".validator-form").attr("action");
            if (action.indexOf('graphic') != -1 && endDate.getTime() - startDate.getTime() > 30 * 24 * 60 * 60 * 1000) {
                alert("Период для построения графика должен быть менее 30 суток!");
                e.preventDefault();
            }
        }
    });

    $('body').on('click', '.btnHide', function() {
       var me = $(this);
        var span = me.find('span');
        var col = me.parents('.col').first();
        var centerCol = $('.col-center');
        var centerSize = centerCol.data('size');
        if(span.hasClass('glyphicon-minus')) {
            span.removeClass('glyphicon-minus');
            span.addClass('glyphicon-plus');
            centerCol.removeClass('col-xs-' + centerSize);
            centerCol.addClass('col-xs-' + (centerSize + 1));
            centerCol.data('size', centerSize + 1);

            col.removeClass('col-xs-2');
            col.addClass('col-xs-1');

        } else {
            span.removeClass('glyphicon-plus');
            span.addClass('glyphicon-minus');
            centerCol.removeClass('col-xs-' + centerSize);
            centerCol.addClass('col-xs-' + (centerSize - 1));
            centerCol.data('size', centerSize - 1);

            col.removeClass('col-xs-1');
            col.addClass('col-xs-2');
        }
    });

    $('#settings-tab').on('shown.bs.tab', function(){
        setSettingHeight();
    });

    setInterval(updateSensorsByTime, 15 * 1000);

    $('.save-criterion').click(updateCriterion);

    $('.save-criterion-interval').click(updateCriterionInterval);

    $('.save-range').click(updateRange);

    $('.criterion').click(function() {
        var tr = $(this);
        var id = tr.find('.cr-id').html();
        var start = tr.find('.cr-start').html();
        var end = tr.find('.cr-end').html();

        var criterionSetting = $('.criterion-setting');
        criterionSetting.find('span [name=id]').empty().html(id);
        criterionSetting.find('#criterion-id').val(id);
        criterionSetting.find('#criterion-start').val(start);
        criterionSetting.find('#criterion-end').val(end);
    });

});

function updateSensorsByTime() {
    updateSensors(false);
}

function updateSensors(force) {
    $.ajax({
        url: "/ajax/updateSensor",
        data: {
            date: $('#lastDate').val(),
            time: $('#lastTime').val(),
            force: force
        }
    }).done(function (data) {
        if (data.update) {
            for (var i = 0; i < data.sensors.length; i++) {
                var sensorValue = data.sensors[i];
                var sensor = $('.sensor-' + sensorValue.sensorId);
                sensor.attr('data-value', sensorValue.value);
                sensor.find('rect').attr('fill', sensorValue.color);
                $('.sen-' + sensorValue.sensorId).find('.val').empty().html(sensorValue.value);
                if (i == 0) {
                    $('#lastDate').val(formatDate(sensorValue));
                    $('#lastTime').val(formatTime(sensorValue));
                }
            }
            $('#minValue').val(data.min);
            $('#maxValue').val(data.max);
            if ($('#graphic').hasClass('active')) {
                drawGraphic();
            }
        }
    }).fail(function(jqXHR, textStatus, e ) {
        alert("Ошибка при получение данных с сервера: " + textStatus);
    });
}

function updateRange () {
    var start = $('#startRange').val();
    $.ajax({
        url: "/ajax/updateRange",
        data: {
            start: start
        }
    }).done(function (sensors) {

        for (var i = 0; i < sensors.length; i++) {
            var sensor = sensors[i];
            $('.sensor-' + sensor.id).attr('data-title', sensor.lengthValue).find('title').html(sensor.lengthValue);
            $('.sen-' + sensor.id).find('.length').html(sensor.lengthValue);
            if (i == 0){
                $('#minSensorLength').val(sensor.lengthValue);
            } else if (i == sensors.length - 1) {
                $('#maxSensorLength').val(sensor.lengthValue);
            }
        }

        updateSensors(true);
    }).fail(function(jqXHR, textStatus, e ) {
        alert("Ошибка обновления критерия: " + textStatus);
    });
}

function updateCriterion () {
    var id = $('#criterion-id').val();
    var start = $('#criterion-start').val();
    var end = $('#criterion-end').val();
    $.ajax({
        url: "/ajax/updateCriterion",
        data: {
            id: id,
            start: start,
            end: end
        }
    }).done(function (data) {
        if (data) {
            var tr = $('#cr-'+id);
            tr.find('.cr-start').empty().html(start);
            tr.find('.cr-end').empty().html(end);
            updateSensors(true);
        }
    }).fail(function(jqXHR, textStatus, e ) {
        alert("Ошибка обновления критерия: " + textStatus);
    });
}

function updateCriterionInterval () {
    var id = $('#criterion-id').val();
    var start = $('#criterion-interval-start').val();
    var end = $('#criterion-interval-end').val();
    $.ajax({
        url: "/ajax/updateCriterionInterval",
        data: {
            start: start,
            end: end
        }
    }).done(function (data) {
        if (data) {
            for (var i = 0; i < data.length; i++) {
                var criterion = data[i];
                var tr = $('#cr-' + criterion.id);
                tr.find('.cr-start').empty().html(criterion.startValue);
                tr.find('.cr-end').empty().html(criterion.endValue);
                if (id == criterion.id) {
                    $('#criterion-start').val(criterion.startValue);
                    $('#criterion-end').val(criterion.endValue);
                }
            }
            updateSensors(true);
        }
    }).fail(function(jqXHR, textStatus, e ) {
        alert("Ошибка обновления критерия: " + textStatus);
    });
}

function setCorrectHeight() {
    var container = $('.sensor-container');
    var height = $('body').height();
    var panelSettingHeight = $('.panel-graphic-setting').height();
    var titleHeight = container.parents('.panel').first().find('.panel-heading').height();
    container.height((height - panelSettingHeight - titleHeight - 30) + "px");
}

function setSettingHeight() {
    var height = $(window).height();
    $('.criterions-container').height((height - 300) + "px");
}

function formatDate (sensorValue) {
    var date = sensorValue.date.year + "-";
    date += sensorValue.date.monthValue > 9 ? sensorValue.date.monthValue : "0" + sensorValue.date.monthValue;
    date += "-";
    date += sensorValue.date.dayOfMonth > 9 ? sensorValue.date.dayOfMonth : "0" + sensorValue.date.dayOfMonth;
    return date;
}

function formatTime (sensorValue) {
    var time = sensorValue.time.hour > 9 ? sensorValue.time.hour : "0" + sensorValue.time.hour;
    time += ":";
    time += sensorValue.time.minute > 9 ? sensorValue.time.minute : "0" + sensorValue.time.minute;
    time += ":";
    time += sensorValue.time.second > 9 ? sensorValue.time.second : "0" + sensorValue.time.second;
    return time;
}

function formatDateTime (date) {

    var dateStr = date.getDay() > 9 ? date.getDay() : "0" + date.getDay();
    dateStr += "/";
    dateStr += date.getMonth() + 1 > 9 ? date.getMonth() + 1 : "0" + date.getMonth() + 1;
    dateStr += "/";
    dateStr += date.getFullYear();
    dateStr += " ";

    dateStr += date.getHours() > 9 ? date.getHours() : "0" + date.getHours();
    dateStr += ":";
    dateStr += date.getUTCMinutes() > 9 ? date.getUTCMinutes() : "0" + date.getUTCMinutes();
    dateStr += ":";
    dateStr += date.getUTCSeconds() > 9 ? date.getUTCSeconds() : "0" + date.getUTCSeconds();
    return dateStr;
}
