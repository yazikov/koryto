package ru.rushydro.vniig.model;

/**
 * Created by yazik on 15.10.2016.
 */
public class GraphicData {

    String graphicData;

    Long minTime;

    Long maxTime;

    Double min;

    Double max;

    public String getGraphicData() {
        return graphicData;
    }

    public void setGraphicData(String graphicData) {
        this.graphicData = graphicData;
    }

    public Long getMinTime() {
        return minTime;
    }

    public void setMinTime(Long minTime) {
        this.minTime = minTime;
    }

    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
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
