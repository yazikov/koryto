package ru.rushydro.vniig.util.data;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by nikolay on 11.10.15.
 */
@Component
public class UpdateTimeUtil {

    private Date lastUpdateTime = new Date();

    public synchronized Boolean isUpdateTime() {
        Calendar now = Calendar.getInstance();
        Calendar updateTime = Calendar.getInstance();
        updateTime.setTime(lastUpdateTime);
        updateTime.add(Calendar.SECOND, 5);
        if (updateTime.compareTo(now) > 0) {
            return false;
        } else {
            lastUpdateTime = now.getTime();
            return true;
        }
    }
}
