package ru.rushydro.vniig.model;

import ru.rushydro.vniig.entry.SensorValue;

import java.util.List;

/**
 * Created by nikolay on 11.10.15.
 */
public class SensorUpdateData {

    Boolean update;

    List<SensorValue> sensors;

    Double min;

    Double max;

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public List<SensorValue> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorValue> sensors) {
        this.sensors = sensors;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
