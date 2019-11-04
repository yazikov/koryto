package ru.rushydro.vniig.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.rushydro.vniig.entry.Sensor;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yazik on 09.10.2016.
 */
public class SensorRowMapper implements RowMapper<Sensor> {

    @Override
    public Sensor mapRow(ResultSet rs, int i) throws SQLException {
        Sensor sensor = new Sensor();
        sensor.setId(rs.getLong("id"));
        sensor.setDependentFromSensorId(rs.getLong("id_dependent_from_sensor"));
        BigDecimal length = rs.getBigDecimal("start_length_value");
        if (length != null) {
            sensor.setStartLengthValue(length.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        length = rs.getBigDecimal("end_length_value");
        if (length != null) {
            sensor.setEndLengthValue(length.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        length = rs.getBigDecimal("start_file_length_value");
        if (length != null) {
            sensor.setStartFileLengthValue(length.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        length = rs.getBigDecimal("end_file_length_value");
        if (length != null) {
            sensor.setEndFileLengthValue(length.setScale(2, BigDecimal.ROUND_HALF_UP));
        }

        length = rs.getBigDecimal("start_length_value_2");
        if (length != null) {
            sensor.setStartLengthValue2(length.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        length = rs.getBigDecimal("end_length_value_2");
        if (length != null) {
            sensor.setEndLengthValue2(length.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        length = rs.getBigDecimal("start_file_length_value_2");
        if (length != null) {
            sensor.setStartFileLengthValue2(length.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        length = rs.getBigDecimal("end_file_length_value_2");
        if (length != null) {
            sensor.setEndFileLengthValue2(length.setScale(2, BigDecimal.ROUND_HALF_UP));
        }

        sensor.setX(rs.getInt("x"));
        sensor.setY(rs.getInt("y"));
        sensor.setWidth(rs.getInt("width"));
        sensor.setHeight(rs.getInt("height"));
        return sensor;
    }
}
