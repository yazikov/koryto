package ru.rushydro.vniig.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.rushydro.vniig.entry.*;
import ru.rushydro.vniig.service.FileService;
import ru.rushydro.vniig.service.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by nikolay on 06.09.15.
 */
@Controller
@RequestMapping(path = "/")
public class IndexController {
    final static Logger log = Logger.getLogger(IndexController.class);

    public static int SQUERE = 96;

    @Autowired
    Service service;

    @Autowired
    FileService fileService;

    @RequestMapping(path = "/")
    public String indexPage(Model model) {

        List<Sensor> sensors = service.getSensors();
        Map<Long, SensorValue> sensorValues = service.getSensorValues();

        model.addAttribute("sensors", sensors);
        model.addAttribute("sensorValues", sensorValues);
        model.addAttribute("minValue", sensorValues.values().stream().min((v1,v2) -> (int) (v1.getValue() - v2.getValue())).get().getValue() - 1);
        model.addAttribute("maxValue", sensorValues.values().stream().max((v1,v2) -> (int) (v1.getValue() - v2.getValue())).get().getValue() + 1);
        model.addAttribute("range", service.getRange());
        model.addAttribute("criterions", service.getCriterions());

//        buildData(model);
        return "index";
    }

    public void buildData(Model model) {
        List<Sensor> sensors = new ArrayList<>();
        BigDecimal start = new BigDecimal("16.80");

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        int y = 664;
        int x = 117;

        int k = -1;

        List<Criterion> criterions = new ArrayList<>();

        int b = 255;

        int r = 0;

        for (int i = 0; i < 50; i++) {
            Criterion criterion = new Criterion();
            criterion.setId(i + 1);
            criterion.setStartValue(i != 0 ? i : -999999999);
            criterion.setEndValue(i != 49 ? i+1 : 999999999);
            criterion.setBlue(b);
            criterion.setRed(r);
            b -= 5;
            r += 5;
            criterions.add(criterion);
        }

        Map<Long, SensorValue> sensorValues = new HashMap<>();

        for (int i = 1; i <= 61; i++) {
            Sensor sensor = new Sensor();
            sensor.setId(i);
            sensor.setX(x);
            sensor.setY(y);

            if (i != 1 && (i - 1) % 6 == 0 && i != 61) {
                sensor.setWidth(sensor.getWidth() * 2);
            }

            if (i != 1 && i != 2 && (i - 1) % 6 == 0) {
                x += SQUERE;
            }

            if (i != 1 && (i - 1) % 6 == 0) {
                k = -1 * k;
            }

            y += k * SQUERE;

            sensor.setLengthValue("" + start.toString());
            sensors.add(sensor);

            start = start.add(new BigDecimal("0.2"));

            SensorValue value = new SensorValue();
            value.setId(sensor.getId());
            value.setValue(i);
            value.setDate(date);
            value.setTime(time);
            for (Criterion criterion : criterions) {
                if (value.getValue() >= criterion.getStartValue() && value.getValue() < criterion.getEndValue()) {
                    value.setColor(criterion.getColor());
                    break;
                }
            }


            sensorValues.put(value.getId(), value);
        }

        model.addAttribute("sensors", sensors);
        model.addAttribute("sensorValues", sensorValues);
        model.addAttribute("minValue", sensorValues.values().stream().min((v1,v2) -> (int) (v1.getValue() - v2.getValue())).get().getValue() - 1);
        model.addAttribute("maxValue", sensorValues.values().stream().max((v1,v2) -> (int) (v1.getValue() - v2.getValue())).get().getValue() + 1);

//        criterions.forEach(c -> System.out.println("insert into criterion values(" + c.getId() +
//                "," + c.getStartValue() + "," + c.getEndValue() + "," + c.getRed() + "," + c.getBlue()
//                + "," + c.getGreen()  + "," + c.getIntensity() + ");"));

//        sensors.forEach(s -> System.out.println("insert into sensor values(" +
//                s.getId() + "," + s.getLengthValue() + "," + s.getX() + "," + s.getY()
//                + "," + s.getWidth() + "," + s.getHeight() + ");"));

//        sensorValues.values().forEach(sv -> System.out.println("insert into sensor_value values (" +
//                sv.getId() + "," + sv.getId() + "," + sv.getValue() + ",CURRENT_DATE,CURRENT_TIME" +");"));
//
//        sensorValues.values().forEach(sv -> System.out.println("insert into sensor_value_storage values (" +
//                sv.getId() + "," + sv.getId() + "," + sv.getValue() + ",CURRENT_DATE,CURRENT_TIME" +");"));
    }

    @RequestMapping(path = "/loadData", method = RequestMethod.POST)
    public String loadData(@RequestParam MultipartFile file, Model model) {

//        fileService.parseFile(new File("C:\\работа\\Корыто\\2016-04-05_11_36_13.las"));

        try {
            Path tmp = Files.createTempFile("data", "las");
            Files.write(tmp, file.getBytes());
            fileService.parseFile(tmp.toFile());
            tmp.toFile().delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Получен файл: " + file.getOriginalFilename());

        return "success";
    }
}
