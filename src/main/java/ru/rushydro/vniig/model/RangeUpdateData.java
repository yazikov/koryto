package ru.rushydro.vniig.model;

import ru.rushydro.vniig.entry.Sensor;

import java.util.List;

/**
 * Created by yazik on 15.10.2016.
 */
public class RangeUpdateData {

    List<Sensor> sensors;

    Double min;

    Double max;

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
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
