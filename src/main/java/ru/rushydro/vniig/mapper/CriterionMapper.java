package ru.rushydro.vniig.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.rushydro.vniig.entry.Criterion;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yazik on 12.10.2016.
 */
public class CriterionMapper implements RowMapper<Criterion> {
    @Override
    public Criterion mapRow(ResultSet rs, int i) throws SQLException {
        Criterion criterion = new Criterion();
        criterion.setId(rs.getLong("id"));
        criterion.setStartValue(new BigDecimal(rs.getDouble("start_value")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        criterion.setEndValue(new BigDecimal(rs.getDouble("end_value")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        criterion.setBlue(rs.getInt("blue"));
        criterion.setRed(rs.getInt("red"));
        criterion.setGreen(rs.getInt("green"));
        criterion.setIntensity(new BigDecimal(rs.getDouble("intensity")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        return criterion;
    }
}
