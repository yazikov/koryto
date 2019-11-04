package ru.rushydro.vniig.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.object.SqlCall;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.rushydro.vniig.entry.*;
import ru.rushydro.vniig.service.DataService;
import ru.rushydro.vniig.service.FileService;
import ru.rushydro.vniig.service.Service;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Autowired
    DataService dataService;

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

//        dataService.buildDataNew(model);
        return "index";
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
