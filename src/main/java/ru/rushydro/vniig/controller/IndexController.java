package ru.rushydro.vniig.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by nikolay on 06.09.15.
 */
@Controller
@RequestMapping(path = "/")
public class IndexController {
    final static Logger log = Logger.getLogger(IndexController.class);

    public static double SQUERE = 19.3;

    @Value("${show.layers:false}")
    public boolean showLayers;

    @Value("${map.image:map.jpg}")
    public String mapImage;

    @Value("${map.cabel.image:map_prod_cabel.jpg}")
    public String mapCabelImage;

    @Value("${map.block.image:map_prod_block.jpg}")
    public String mapBlockImage;

    @Value("${map.cabel.block.image:map_prod_cabel_block.jpg}")
    public String mapCabelBlockImage;

    @Value("${map.width:1231}")
    public String mapWidth;

    @Value("${map.height:941}")
    public String mapHeight;

    @Autowired
    Service service;

    @Autowired
    FileService fileService;

    @RequestMapping(path = "/")
    public String indexPage(Model model) {

        List<Sensor> sensors = service.getSensors();
        Map<Long, SensorValue> sensorValues = service.getSensorValues();

        model.addAttribute("showLayers", showLayers);
        model.addAttribute("sensors", sensors);
        model.addAttribute("sensorValues", sensorValues);
        model.addAttribute("minValue", sensorValues != null && !sensorValues.isEmpty()
                ? sensorValues.values()
                .stream().min((v1,v2) -> (int) (v1.getValue() - v2.getValue())).get().getValue() - 1 : 0);
        model.addAttribute("maxValue", sensorValues != null && !sensorValues.isEmpty()
                ? sensorValues.values()
                .stream().max((v1,v2) -> (int) (v1.getValue() - v2.getValue())).get().getValue() + 1 : 1);
        model.addAttribute("range", service.getRange());
        model.addAttribute("criterions", service.getCriterions());

        model.addAttribute("mapImage", mapImage);
        model.addAttribute("mapCabelImage", mapCabelImage);
        model.addAttribute("mapBlockImage", mapBlockImage);
        model.addAttribute("mapCabelBlockImage", mapCabelBlockImage);
        model.addAttribute("mapHeight", mapHeight);
        model.addAttribute("mapWidth", mapWidth);

        buildDataNew(model);
        return "index";
    }

    private void buildDataNew(Model model) {
        final List<Long> notInSensorIds = new ArrayList<>();

        notInSensorIds.addAll(LongStream.range(7, 47).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(59, 86).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(93, 99).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(111, 116).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(127, 132).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(145, 152).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(162, 169).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(197, 204).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(214, 222).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(248, 256).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(265, 274).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(298, 311).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(315, 328).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(350, 363).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(367, 381).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(401, 415).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(430, 433).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(1899, 1900).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(1949, 1956).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(1998, 2011).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(2047, 2066).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(2094, 2121).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(2143, 2176).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(2193, 2231).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(2240, 2286).boxed().collect(Collectors.toList()));

        List<Sensor> sensors = new ArrayList<>();
        BigDecimal start = new BigDecimal("0");

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

//        double y = 605;
//        double x = 260;

        double y = 228;
        double x = 180;

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

        int id = 1;

        for (int i = 1; i <= 2288; i++) {

            Sensor sensor = new Sensor();
            sensor.setId(id++);
            sensor.setX(x);
            sensor.setY(y);

            if (i % 26 == 0) {
                k = -1 * k;
                x += SQUERE;
            } else {
                y -= k * SQUERE;
            }

            sensor.setLengthValue("" + start.toString());
            start = start.add(new BigDecimal("0.2"));

            if (!notInSensorIds.contains(sensor.getId())) {
                sensors.add(sensor);
            }
        }

        x = sensors.get(1898).getX();
        y = sensors.get(1898).getY() - SQUERE;
        sensors.add(createSensor(id++, x,y));
        x += SQUERE;
        sensors.add(createSensor(id++, x,y));
        x += SQUERE;
        sensors.add(createSensor(id++, x,y));
        x += SQUERE;
        sensors.add(createSensor(id++, x,y));

        x += SQUERE;
        y -= 1.5 * SQUERE;
        Sensor sensorCustom = createSensor(id++, x,y);
        sensorCustom.setHeight(2.5 * SQUERE);
        sensorCustom.setWidth(1.5 * SQUERE);
        sensors.add(sensorCustom);

        x += 1.5 * SQUERE;
        sensors.add(createSensor(id, x,y));

        int j = 0;

        for (Sensor sensor : sensors) {
            SensorValue value = new SensorValue();
            value.setId(sensor.getId());
            value.setValue(j++);
            value.setDate(date);
            value.setTime(time);
            value.setColor(criterions.get(7).getColor());

            sensorValues.put(value.getId(), value);
        }

        model.addAttribute("sensors", sensors);
        model.addAttribute("sensorValues", sensorValues);
        model.addAttribute("minValue", sensorValues.values().stream().min((v1,v2) -> (int) (v1.getValue() - v2.getValue())).get().getValue() - 1);
        model.addAttribute("maxValue", sensorValues.values().stream().max((v1,v2) -> (int) (v1.getValue() - v2.getValue())).get().getValue() + 1);

//        criterions.forEach(c -> System.out.println("insert into criterion values(" + c.getId() +
//                "," + c.getStartValue() + "," + c.getEndValue() + "," + c.getRed() + "," + c.getBlue()
//                + "," + c.getGreen()  + "," + c.getIntensity() + ");"));
//
//        sensors.forEach(s -> System.out.println("insert into sensor values(" +
//                s.getId() + "," + s.getLengthValue() + "," + s.getX() + "," + s.getY()
//                + "," + s.getWidth() + "," + s.getHeight() + ");"));
//
//        sensorValues.values().forEach(sv -> System.out.println("insert into sensor_value values (" +
//                sv.getId() + "," + sv.getId() + "," + sv.getValue() + ",CURRENT_DATE,CURRENT_TIME" +");"));
//
//        sensorValues.values().forEach(sv -> System.out.println("insert into sensor_value_storage values (" +
//                sv.getId() + "," + sv.getId() + "," + sv.getValue() + ",CURRENT_DATE,CURRENT_TIME" +");"));
    }

    public Sensor createSensor(int id, double x, double y) {
        Sensor sensor = new Sensor();
        sensor.setId(id);
        sensor.setX(x);
        sensor.setY(y);

        sensor.setLengthValue("99999");

        return sensor;
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
