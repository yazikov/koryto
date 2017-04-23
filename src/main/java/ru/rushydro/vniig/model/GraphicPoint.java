package ru.rushydro.vniig.model;

import java.math.BigDecimal;

/**
 * Created by nikolay on 22.11.15.
 */
public class GraphicPoint {
    double x;

    double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = new BigDecimal(x).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = new BigDecimal(y).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
