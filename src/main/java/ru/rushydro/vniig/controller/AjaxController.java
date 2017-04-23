package ru.rushydro.vniig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rushydro.vniig.entry.Criterion;
import ru.rushydro.vniig.entry.Sensor;
import ru.rushydro.vniig.entry.SensorValue;
import ru.rushydro.vniig.model.GraphicData;
import ru.rushydro.vniig.model.RangeUpdateData;
import ru.rushydro.vniig.model.SensorUpdateData;
import ru.rushydro.vniig.service.FileService;
import ru.rushydro.vniig.service.Service;
import ru.rushydro.vniig.util.data.UpdateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by nikolay on 11.10.15.
 */
@Controller
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    Service service;

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/updateSensor", method = RequestMethod.GET)
    public @ResponseBody SensorUpdateData getSensorUpdateData(@RequestParam String date, @RequestParam String time, @RequestParam boolean force) {
        SensorUpdateData sensorUpdateData = new SensorUpdateData();
        if (force) {
            sensorUpdateData.setUpdate(true);
        } else {
            SensorValue last = service.getLastValue();
            LocalDate lastDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalTime lastTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
            if (lastDate.isBefore(last.getDate()) || (lastDate.isEqual(last.getDate()) && lastTime.isBefore(last.getTime()))) {
                sensorUpdateData.setUpdate(true);
            } else {
                sensorUpdateData.setUpdate(false);
            }
        }

        if (sensorUpdateData.getUpdate()) {
            List<SensorValue> sensorValues = new ArrayList<>(service.getSensorValues().values());
            sensorUpdateData.setSensors(sensorValues);
            sensorUpdateData.setMin(sensorValues.stream().mapToDouble(SensorValue::getValue).min().getAsDouble());
            sensorUpdateData.setMax(sensorValues.stream().mapToDouble(SensorValue::getValue).max().getAsDouble());
        }

        return sensorUpdateData;
    }

    @RequestMapping(value = "/updateCriterion", method = RequestMethod.GET)
    public @ResponseBody boolean updateCriterion(@RequestParam Long id, @RequestParam Double start, @RequestParam Double end) {
        return service.updateCriterion(id, start, end);
    }

    @RequestMapping(value = "/updateCriterionInterval", method = RequestMethod.GET)
    public @ResponseBody List<Criterion> updateCriterionInterval(@RequestParam Double start, @RequestParam Double end) {
        return service.updateCriterionInterval(start, end);
    }


    @RequestMapping(value = "/updateRange", method = RequestMethod.GET)
    public @ResponseBody
    List<Sensor> updateRange (@RequestParam Double start) {
        fileService.setForceUpdate(true);
        service.updateRange(start);
        return service.clearAndGetSensors();
    }

    @RequestMapping(value = "/customGraphic", method = RequestMethod.GET)
    public @ResponseBody
    GraphicData customGraphic (@RequestParam(required = false) String start,
                               @RequestParam(required = false) String end,
                               @RequestParam(required = false) String list) {
        final GraphicData graphicData = new GraphicData();

        List<SensorValue> sensorValues = service.getStorageSensorValues(start, end, list);

        if (!sensorValues.isEmpty()) {

            final Map<Double, List<SensorValue>> map = new HashMap<>();

            sensorValues.forEach(sv -> {
                List<SensorValue> sensors = map.get(sv.getLength());
                if (sensors == null){
                    sensors = new ArrayList<>();
                    map.put(sv.getLength(), sensors);
                }
                sensors.add(sv);
            });

            graphicData.setGraphicData("[" + map.entrySet().stream()
                    .map(entry -> "{" + "\"lines\":{\"lineWidth\":5},\"label\":\"" + entry.getKey() + "\",\"data\":[" +
                            entry.getValue().stream().map(sv -> "[" + Date.from(LocalDateTime.of(sv.getDate(), sv.getTime())
                                    .atZone(ZoneId.systemDefault()).toInstant()).getTime() + "," + sv.getValue() +  "]"
                            ).collect(Collectors.joining(","))
                            + "]}").collect(Collectors.joining(",")) + "]");

            graphicData.setMin(sensorValues.stream().mapToDouble(SensorValue::getValue).min().getAsDouble());
            graphicData.setMax(sensorValues.stream().mapToDouble(SensorValue::getValue).max().getAsDouble());
            graphicData.setMinTime(sensorValues.stream()
                    .mapToLong(sv -> Date.from(LocalDateTime.of(sv.getDate(), sv.getTime())
                            .atZone(ZoneId.systemDefault()).toInstant()).getTime()).min().getAsLong());
            graphicData.setMaxTime(sensorValues.stream()
                    .mapToLong(sv -> Date.from(LocalDateTime.of(sv.getDate(), sv.getTime())
                            .atZone(ZoneId.systemDefault()).toInstant()).getTime()).max().getAsLong());
        } else {
            graphicData.setMin(0d);
            graphicData.setMax(0d);
            graphicData.setMinTime(0L);
            graphicData.setMaxTime(0L);
        }
        return graphicData;
    }
}
