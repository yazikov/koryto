package ru.rushydro.vniig.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.rushydro.vniig.entry.Range;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yazik on 10.10.2016.
 */
public class RangeMapper implements RowMapper<Range> {
    @Override
    public Range mapRow(ResultSet rs, int i) throws SQLException {
        Range range = new Range();
        range.setStart(new BigDecimal(rs.getDouble("start_range")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        range.setEnd(new BigDecimal(rs.getDouble("end_range")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        return range;
    }
}
