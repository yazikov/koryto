package ru.rushydro.vniig.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.rushydro.vniig.dao.DAO;
import ru.rushydro.vniig.entry.Range;
import ru.rushydro.vniig.entry.Sensor;
import ru.rushydro.vniig.entry.SensorValue;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public void parseFile(final File file) {

        Range range = service.getRange();
        SensorValue lastValue = service.getLastValue();

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            LocalDate date = null;
            LocalTime time = null;
            int i = 1;
            while((line = br.readLine()) != null) {
                //Если это 17 строка, то считываем дату
                if (i == 17) {
                    int index = line.indexOf("2");
                    String str = line.substring(index);
                    index = str.indexOf(":");
                    if (index != -1) {
                        str = str.substring(0, index);
                    }
                    index = str.indexOf("_");
                    date = LocalDate.parse(str.substring(0, index), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    time = LocalTime.parse(str.substring(index+1).replaceAll("\\s", ""), DateTimeFormatter.ofPattern("HH_mm_ss"));
                    if (!(forceUpdate || date.isAfter(lastValue.getDate())
                            || (date.isEqual(lastValue.getDate()) && time.isAfter(lastValue.getTime())))) {
                        break;
                    } else if (forceUpdate) {
                        forceUpdate = false;
                    }
                }
                //Если строка больше 24, то парсим значения
                if (i > 24) {
                    String[] params = line.split("\\s");
                    Double length = Double.parseDouble(params[0]);
                    if (length >= range.getStart() && length <= range.getEnd()) {
                        Double value = Double.parseDouble(params[1]);
                        SensorValue sensorValue = new SensorValue();
                        Sensor sensor = getSensorByLength(length);
                        if (sensor != null) {
                            sensorValue.setId(sensor.getId());
                            sensorValue.setSensorId(sensor.getId());
                            sensorValue.setValue(value);
                            sensorValue.setDate(date);
                            sensorValue.setTime(time);
                            sensorValue.setLength(length);
                            service.updateSensorValue(sensorValue);
                        }
                    } else if (length > range.getEnd()) {
                        break;
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
