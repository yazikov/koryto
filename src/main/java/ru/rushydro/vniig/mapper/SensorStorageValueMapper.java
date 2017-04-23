package ru.rushydro.vniig.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.rushydro.vniig.entry.SensorValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by yazik on 10.10.2016.
 */
public class SensorStorageValueMapper implements RowMapper<SensorValue> {
    @Override
    public SensorValue mapRow(ResultSet rs, int i) throws SQLException {
        SensorValue sensorValue = new SensorValue();
        sensorValue.setId(rs.getLong("id"));
        sensorValue.setSensorId(rs.getLong("id_sensor"));
        sensorValue.setValue(rs.getDouble("value"));
        sensorValue.setDate(LocalDate.parse(rs.getString("v_date"), DateTimeFormatter.ISO_LOCAL_DATE));
        sensorValue.setTime(LocalTime.parse(rs.getString("v_time"), rs.getString("v_time").contains(".")
                ? DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS") : DateTimeFormatter.ofPattern("HH:mm:ss")));
        sensorValue.setLength(rs.getDouble("length_value"));
        return sensorValue;
    }
}
