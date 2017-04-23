package ru.rushydro.vniig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by nikolay on 25.10.15.
 */
@RequestMapping("/enter")
@Controller
public class EnterController {

    @RequestMapping("/graphic")
    public String showGraphic (
            Model model,
            @RequestParam Map<String,String> allRequestParams,
            @RequestParam(value = "startDate", defaultValue = "") String startDate,
            @RequestParam(value = "endDate", defaultValue = "") String endDate) {

        List<Integer> sensors = allRequestParams.keySet().stream().filter(key -> key.contains("sensor_")).map(key -> Integer.parseInt(key.replace("sensor_", ""))).collect(Collectors.toList());

//        GraphicModel graphic = measParamSysStorageService.getGraphic(startDate, endDate, sensors);
//        model.addAttribute("graphic", graphic);

        return "graphic";
    }

}
