package ru.rushydro.vniig.entry;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by yazik on 08.10.2016.
 */
public class SensorValue {
    long id;

    long sensorId;

    long blockId;

    double value;

    String color;

    Double length;

    Double startLength;

    Double endLength;

    LocalDate date;

    LocalTime time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public long getSensorId() {
        return sensorId;
    }

    public void setSensorId(long sensorId) {
        this.sensorId = sensorId;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public long getBlockId() {
        return blockId;
    }

    public void setBlockId(long blockId) {
        this.blockId = blockId;
    }

    public Double getStartLength() {
        return startLength;
    }

    public void setStartLength(Double startLength) {
        this.startLength = startLength;
    }

    public Double getEndLength() {
        return endLength;
    }

    public void setEndLength(Double endLength) {
        this.endLength = endLength;
    }
}
