package ru.rushydro.vniig.entry;

import ru.rushydro.vniig.controller.IndexController;

import java.math.BigDecimal;

/**
 * Created by yazik on 08.10.2016.
 */
public class Sensor {
    private long id;

    private long dependentFromSensorId;

    private double x;

    private double y;

    private double width = IndexController.SQUERE;

    private double height = IndexController.SQUERE;

    private String lengthValue;

    private BigDecimal startLengthValue;

    private BigDecimal endLengthValue;

    private BigDecimal startFileLengthValue;

    private BigDecimal endFileLengthValue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getLengthValue() {
        return lengthValue;
    }

    public void setLengthValue(String lengthValue) {
        this.lengthValue = lengthValue;
    }

    public long getDependentFromSensorId() {
        return dependentFromSensorId;
    }

    public void setDependentFromSensorId(long dependentFromSensorId) {
        this.dependentFromSensorId = dependentFromSensorId;
    }

    public BigDecimal getStartLengthValue() {
        return startLengthValue;
    }

    public void setStartLengthValue(BigDecimal startLengthValue) {
        this.startLengthValue = startLengthValue;
    }

    public BigDecimal getEndLengthValue() {
        return endLengthValue;
    }

    public void setEndLengthValue(BigDecimal endLengthValue) {
        this.endLengthValue = endLengthValue;
    }

    public BigDecimal getStartFileLengthValue() {
        return startFileLengthValue;
    }

    public void setStartFileLengthValue(BigDecimal startFileLengthValue) {
        this.startFileLengthValue = startFileLengthValue;
    }

    public BigDecimal getEndFileLengthValue() {
        return endFileLengthValue;
    }

    public void setEndFileLengthValue(BigDecimal endFileLengthValue) {
        this.endFileLengthValue = endFileLengthValue;
    }

    public int getOffset() {
        int offset = 5;
        if (lengthValue.length() < 6) {
            offset = 35 - lengthValue.length() * 5;
        }
        return offset;
    }
}
