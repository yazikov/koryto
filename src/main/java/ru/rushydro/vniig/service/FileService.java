package ru.rushydro.vniig.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.rushydro.vniig.dao.DAO;
import ru.rushydro.vniig.entry.Range;
import ru.rushydro.vniig.entry.Sensor;
import ru.rushydro.vniig.entry.SensorValue;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yazik on 10.10.2016.
 */
@org.springframework.stereotype.Service
public class FileService {

    private boolean forceUpdate;

    @Autowired
    Service service;

    DAO dao;

    @Autowired
    public FileService(DAO dao) {
        this.dao = dao;
    }

    public void parseFile(final File file, final String oldFileName, Integer blockId) {

        Map<Double, Double> valueMap = new HashMap<>();

        List<Sensor> sensors = dao.getSensorsByBlock(blockId);
        if (sensors == null || sensors.isEmpty()) {
            throw new RuntimeException("Список датчиков по блоку: " + blockId + " не найден!");
        }

        Range range = service.getRange();
        SensorValue lastValue = service.getLastValueByBlock(blockId);

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            LocalDate date;
            LocalTime time;
            int i = 1;

            if (oldFileName.matches("\\d{2}.\\d{2}.\\d{4} \\d{2}-\\d{2}-\\d{2}.las")) {
                int index = oldFileName.indexOf(" ");
                date = LocalDate.parse(oldFileName.substring(0, index), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                time = LocalTime.parse(oldFileName.substring(index+1, oldFileName.lastIndexOf(".")),
                        DateTimeFormatter.ofPattern("HH-mm-ss"));
                if (!(forceUpdate || date.isAfter(lastValue.getDate())
                        || (date.isEqual(lastValue.getDate()) && time.isAfter(lastValue.getTime())))) {
                    return;
                } else if (forceUpdate) {
                    forceUpdate = false;
                }
            } else {
                throw new RuntimeException("В названии файла не содержит дата и время получения данных!");
            }

            while((line = br.readLine()) != null) {
                //Если строка больше 24, то парсим значения
                if (i > 24) {
                    String[] params = line.split("\\s");
                    Double length = Double.parseDouble(params[0]);
                    if (length >= range.getStart() && length <= range.getEnd()) {
                        Double value = Double.parseDouble(params[1]);
                        valueMap.put(length, value);
                    } else if (length > range.getEnd()) {
                        break;
                    }
                }
                i++;
            }

            for (Sensor sensor : sensors) {

                Double value = getValueBySensor(valueMap, sensor);

                if (value != null) {
                    SensorValue sensorValue = new SensorValue();

                    sensorValue.setId(sensor.getId());
                    sensorValue.setSensorId(sensor.getId());
                    sensorValue.setValue(value);
                    sensorValue.setDate(date);
                    sensorValue.setTime(time);
                    sensorValue.setLength(sensor.getStartLengthValue().doubleValue());
                    service.updateSensorValue(sensorValue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Не удалось распарсить файл!");
        }
    }

    private Double getValueBySensor(Map<Double, Double> valueMap, Sensor sensor) {
        if (sensor.getStartFileLengthValue() != null) {
            Double value = 0D;

            double startLength = new BigDecimal(sensor.getStartFileLengthValue().doubleValue() < sensor.getEndFileLengthValue().doubleValue()
                    ? sensor.getStartFileLengthValue().doubleValue() : sensor.getEndFileLengthValue().doubleValue())
                    .setScale(0, RoundingMode.HALF_UP).doubleValue();
            double endLength = new BigDecimal(sensor.getStartFileLengthValue().doubleValue() < sensor.getEndFileLengthValue().doubleValue()
                    ? sensor.getEndFileLengthValue().doubleValue() : sensor.getStartFileLengthValue().doubleValue())
                    .setScale(0, RoundingMode.HALF_UP).doubleValue();

            int count = 0;
            for (double d = startLength; d <= endLength; d++) {
                Double fileValue = valueMap.get(d);
                if (fileValue != null) {
                    value += fileValue;
                    count++;
                }
            }

            if (sensor.getStartFileLengthValue2() != null) {

                startLength = new BigDecimal(sensor.getStartFileLengthValue2().doubleValue() < sensor.getEndFileLengthValue2().doubleValue()
                        ? sensor.getStartFileLengthValue2().doubleValue() : sensor.getEndFileLengthValue2().doubleValue())
                        .setScale(0, RoundingMode.HALF_UP).doubleValue();
                endLength = new BigDecimal(sensor.getStartFileLengthValue2().doubleValue() < sensor.getEndFileLengthValue2().doubleValue()
                        ? sensor.getEndFileLengthValue2().doubleValue() : sensor.getStartFileLengthValue2().doubleValue())
                        .setScale(0, RoundingMode.HALF_UP).doubleValue();

                for (double d = startLength; d <= endLength; d++) {
                    Double fileValue = valueMap.get(d);
                    if (fileValue != null) {
                        value += fileValue;
                        count++;
                    }
                }
            }

            return new BigDecimal(value).divide(new BigDecimal(count), 2, RoundingMode.HALF_UP).doubleValue();
        }


        return null;
    }

    private Sensor getSensorByLength(final Double length) {
        List<Sensor> sensors = service.getSensors();
        for (Sensor sensor : sensors) {
           if (length.equals(Double.parseDouble(sensor.getLengthValue()))) {
               return sensor;
           }
        }
        return null;
    }

    public boolean isForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }
}
