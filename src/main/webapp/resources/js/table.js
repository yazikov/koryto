$(document).ready(function() {

    $('.show-table').click(showCustomTable);

    $('#table-prev').click(function () {
        prevPage();
    });

    $('#table-next').click(function () {
        nextPage();
    });
});

function showCustomTable() {
    $('#storage-table-tab').tab('show');

    var list = '';
    var start = $('#startDate').val();
    var end = $('#endDate').val();

    $('.sen').each(function() {
        var me = $(this);
        var check = me.find('input').prop("checked");
        if (check) {
            if (list.length > 0) {
                list += ',';
            }
            list += me.find('.sensor-id').html();
        }
    });

    $('#table-start').val(start);
    $('#table-end').val(end);
    $('#table-list').val(list);

    showPage(1);
}

function nextPage() {
   showPage(Number($('#table-page').val()) + 1);
}

function prevPage() {
    showPage(Number($('#table-page').val()) - 1);
}

function showPage(page) {
    if (page < 1) {
        page = 1;
    }

    var pageCount = $('#table-page-count').val();

    if (page > pageCount) {
        page = pageCount;
    }

    $('#table-page').val(page);

    var prev = $('#table-prev').parent();
    var next = $('#table-next').parent();

    if (prev.hasClass('disabled')) {
        prev.addClass('disabled');
    }
    if (next.hasClass('disabled')) {
        next.addClass('disabled');
    }

    var start = $('#table-start').val();
    var end = $('#table-end').val();
    var list = $('#table-list').val();

    $.ajax({
        url: "/ajax/customTable",
        data: {
            start: start,
            end: end,
            list: list,
            page: page
        },
        dataType: "json",
        method: "POST"
    }).done(function (data) {
        if (page == 1) {
            if (prev.hasClass('disabled')) {
                prev.addClass('disabled');
            }
        } else {
            prev.removeClass('disabled');
        }

        if (data.pageCount == page) {
            if (next.hasClass('disabled')) {
                next.addClass('disabled');
            }
        } else {
            next.removeClass('disabled');
        }

        $('#table-page-count').val(data.pageCount);

        var tbody = $('.sensor-storage-value-container').find('tbody').empty();

        if (data.sensorValues != null) {
            for (var i = 0; i < data.sensorValues.length; i++) {
                var sensorValue = data.sensorValues[i];
                tbody.append('<tr><td>' + sensorValue.sensorId + '</td><td>' + sensorValue.blockId
                    + '</td><td>' + sensorValue.startLength + '</td><td>' + sensorValue.endLength + '</td><td>'
                    + sensorValue.value + '</td><td>' + dateToString(sensorValue.date) + ' ' + timeToString(sensorValue.time) + '</td></tr>')
            }
        }

    }).fail(function(jqXHR, textStatus, e ) {
        alert("Ошибка построения таблицы: " + textStatus);
    });
}

function dateToString(date) {
    return addZero(date.dayOfMonth) + '.' + addZero(date.monthValue) + '.' + date.year;
}

function timeToString(time) {
    return addZero(time.hour) + ':' + addZero(time.minute) + ':' + addZero(time.second);
}

function addZero(val) {
    return val < 10 ? '0' + val : val;
}

function numberToTime(fn) {
    var date = new Date(fn);
    return formatDateTime(date);
}