package ru.rushydro.vniig.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nikolay on 22.11.15.
 */
public class GraphicItem {

    String name;

    String color;

    List<GraphicPoint> points = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GraphicPoint> getPoints() {
        return points;
    }

    public void setPoints(List<GraphicPoint> points) {
        this.points = points;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toJSON() {

        return "{ " + (color != null ? " lines:{lineWidth:5}, color: '" + color + "'," : "") + " label: '" + getName() + "' , data: [" + points.stream().sorted((first,second) -> (int) (first.getX() - second.getX())).map(point -> "[" + String.format("%.0f",point.getX()) + "," + point.getY() + "]").collect(Collectors.joining(",")) + "]}";
    }
}
