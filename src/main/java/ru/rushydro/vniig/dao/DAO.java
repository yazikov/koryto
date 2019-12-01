package ru.rushydro.vniig.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rushydro.vniig.entry.Criterion;
import ru.rushydro.vniig.entry.Range;
import ru.rushydro.vniig.entry.Sensor;
import ru.rushydro.vniig.entry.SensorValue;
import ru.rushydro.vniig.mapper.*;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by nikolay on 20.09.15.
 */
@Component
@Transactional("transactionManager")
public class DAO {

    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    JdbcTemplate jdbcTemplate;

    public DAO() {
    }

    public JdbcTemplate getJdbcTemplate () {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

    public List<Sensor> getSensors() {
        return getJdbcTemplate().query("select * from sensor order by id", new SensorRowMapper());
    }

    public List<Sensor> getSensorsByBlock(Integer blockId) {
        return getJdbcTemplate().query("select * from sensor where id_block = " + blockId  + " order by id", new SensorRowMapper());
    }

    public List<SensorValue> getSensorValues() {
        return getJdbcTemplate().query("select *, " +
                "(select ('rgba(' || red || ',' || green || ',' || blue || ',' || intensity || ')') " +
                "as color from criterion c where sv.value >= c.start_value and sv.value < c.end_value limit 1) " +
                "from sensor_value sv order by id", new SensorValueMapper());
    }

    public Range getRange() {
        return getJdbcTemplate().queryForObject("select * from sensor_range", new RangeMapper());
    }

    public SensorValue getLastValue() {
        return getJdbcTemplate().queryForObject("select *, 'c' as color " +
                "from sensor_value sv order by id desc limit 1", new SensorValueMapper());
    }

    public SensorValue getLastValueByBlock(Integer blockId) {
        return getJdbcTemplate().queryForObject("select sv.*, 'c' as color " +
                "from sensor_value sv join sensor s on sv.id_sensor = s.id " +
                "where s.id_block = " + blockId +
                " order by sv.id desc limit 1", new SensorValueMapper());
    }

    public void updateSensorValue(SensorValue sensorValue) {
        getJdbcTemplate().update("update sensor_value set value = ?, v_date = '" + sensorValue.getDate().toString()
                + "', v_time = ' " + sensorValue.getTime().toString() + "' where id_sensor = ?",
                sensorValue.getValue(), sensorValue.getSensorId());

        getJdbcTemplate().update("insert into sensor_value_storage(value, v_date, v_time, id_sensor, length_value) " +
                "values(?,'" + sensorValue.getDate().toString() + "','" + sensorValue.getTime().toString() + "',?,?)",
                sensorValue.getValue(), sensorValue.getSensorId(), sensorValue.getLength());
    }

    public List<Criterion> getCriterions() {
        return getJdbcTemplate().query("select * from criterion order by id", new CriterionMapper());
    }

    public boolean updateCriterion(Long id, Double start, Double end) {
        return getJdbcTemplate().update("update criterion set start_value = ?, end_value = ? where id = ?",
                start, end, id) > 0;
    }

    public boolean updateRange (Double start) {
        getJdbcTemplate().update("update sensor set length_value = length_value - (select start_range - ? from sensor_range) ", start);

        getJdbcTemplate().update("update sensor_range set start_range = ?, end_range = ? ", start, start + 12);

        getJdbcTemplate().update("update sensor_value set value = 0");

        return true;
    }

    public List<SensorValue> getStorageSensorValues(String start, String end, String list, int limit, int offset) {
        return getJdbcTemplate().query("select svs.*, " +
                "s.id_block, " +
                "s.start_length_value, " +
                "s.end_length_value " +
                "from sensor_value_storage svs join sensor s on svs.id_sensor = s.id where "
                + generateStorageSensorValuesWhere(start, end, list)
                + " order by s.id, v_date, v_time limit " + limit + " offset " + offset, new SensorStorageValueMapper());
    }

    public List<SensorValue> getStorageSensorValuesForTable(String start, String end, String list, int limit, int offset) {
        return getJdbcTemplate().query("select svs.*, " +
                "s.id_block, " +
                "s.start_length_value, " +
                "s.end_length_value " +
                "from sensor_value_storage svs join sensor s on svs.id_sensor = s.id where "
                + generateStorageSensorValuesWhere(start, end, list)
                + " order by v_date desc, v_time desc, s.id limit " + limit + " offset " + offset, new SensorStorageValueMapper());
    }

    public int countStorageSensorValues(String start, String end, String list) {
        return getJdbcTemplate().queryForObject("select count(*) "
                + "from sensor_value_storage svs join sensor s on svs.id_sensor = s.id where "
                + generateStorageSensorValuesWhere(start, end, list), Integer.class);
    }

    public String generateStorageSensorValuesWhere(String start, String end, String list) {
        StringBuilder sql = new StringBuilder(" s.start_length_value != 0 and s.start_length_value is not null ");
        if (list != null && !list.isEmpty()) {
            sql.append(" and id_sensor in (").append(list).append(") ");
        }

        if (start != null && !start.isEmpty() && end != null && !end.isEmpty()) {
            sql.append(" and v_date between '").append(start).append("' and '").append(end).append("' ");
        } else if (start != null && !start.isEmpty()) {
            sql.append(" and v_date >= '").append(start).append("' ");
        } else if (end != null && !end.isEmpty()) {
            sql.append(" and v_date <= '").append(end).append("' ");
        }

        return sql.toString();
    }
}