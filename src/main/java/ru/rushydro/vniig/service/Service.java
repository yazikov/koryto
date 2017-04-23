package ru.rushydro.vniig.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.rushydro.vniig.dao.DAO;
import ru.rushydro.vniig.entry.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by nikolay on 22.09.15.
 */
@org.springframework.stereotype.Service
public class Service {
    DAO dao;

    List<Sensor> sensors;

    Range range;

    @Autowired
    public Service(DAO dao) {
        this.dao = dao;
    }

    public List<Sensor> getSensors() {
        if (sensors == null) {
            sensors = dao.getSensors();
        }
        return sensors;
    }

    public Map<Long, SensorValue> getSensorValues () {
        List<SensorValue> sensorValues = dao.getSensorValues();
        return sensorValues.stream().collect(Collectors.toMap(SensorValue::getId,
                Function.identity()));
    }

    public Range getRange() {
        if (range == null) {
            range = dao.getRange();
        }
        return range;
    }

    public SensorValue getLastValue () {
        return dao.getLastValue();
    }

    public void updateSensorValue(final SensorValue sensorValue) {
        dao.updateSensorValue(sensorValue);
    }

    public List<Criterion> getCriterions() {
        return dao.getCriterions();
    }

    public boolean updateCriterion(Long id, Double start, Double end) {
        return dao.updateCriterion(id, start, end);
    }

    public List<Criterion> updateCriterionInterval(Double start, Double end) {
        double step = (end - start) / 50;

        List<Criterion> criterions = getCriterions();
        double value = start;
        for (Criterion criterion : criterions) {
            criterion.setStartValue(new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP)
                    .doubleValue());
            value += step;
            criterion.setEndValue(new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP)
                    .doubleValue());
        }

        criterions.get(0).setStartValue(-9999999D);
        criterions.get(criterions.size() - 1).setEndValue(9999999);

        for (Criterion criterion : criterions) {
            dao.updateCriterion(criterion.getId(), criterion.getStartValue(), criterion.getEndValue());
        }

        return criterions;
    }

    public boolean updateRange(Double start) {
        range = null;
        return dao.updateRange(start);
    }

    public List<Sensor> clearAndGetSensors() {
        sensors = null;
        return getSensors();
    }

    public List<SensorValue> getStorageSensorValues(String start, String end, String list) {
        return dao.getStorageSensorValues(start, end, list);
    }
}
