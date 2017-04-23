package ru.rushydro.vniig.util.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nikolay on 07.11.15.
 */
public class DateConverter {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static Date parse (String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }

    public static String format (Date date) {
        return simpleDateFormat.format(date);
    }

}
