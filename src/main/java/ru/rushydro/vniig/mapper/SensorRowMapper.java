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
        sensor.setLengthValue(rs.getBigDecimal("length_value").setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        sensor.setX(rs.getInt("x"));
        sensor.setY(rs.getInt("y"));
        sensor.setWidth(rs.getInt("width"));
        sensor.setHeight(rs.getInt("height"));
        return sensor;
    }
}
