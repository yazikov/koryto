package ru.rushydro.vniig.entry;

import ru.rushydro.vniig.controller.IndexController;

/**
 * Created by yazik on 08.10.2016.
 */
public class Sensor {
    private long id;

    private int x;

    private int y;

    private int width = IndexController.SQUERE;

    private int height = IndexController.SQUERE;

    private String lengthValue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getLengthValue() {
        return lengthValue;
    }

    public void setLengthValue(String lengthValue) {
        this.lengthValue = lengthValue;
    }

    public int getOffset() {
        int offset = 5;
        if (lengthValue.length() < 6) {
            offset = 35 - lengthValue.length() * 5;
        }
        return offset;
    }
}
