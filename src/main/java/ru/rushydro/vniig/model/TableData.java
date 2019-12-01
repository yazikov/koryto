package ru.rushydro.vniig.model;

import ru.rushydro.vniig.entry.SensorValue;

import java.util.List;

/**
 * Created by yazik on 15.10.2016.
 */
public class TableData {

    int pageCount;

    List<SensorValue> sensorValues;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<SensorValue> getSensorValues() {
        return sensorValues;
    }

    public void setSensorValues(List<SensorValue> sensorValues) {
        this.sensorValues = sensorValues;
    }
}
