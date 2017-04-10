package com.ums.management.core.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DatetimeUtil {
    private static SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");

    private DatetimeUtil() {
    }

    public static String plus(String time) {
        return plus(time, 30);
    }

    public static String plus(String time, int seconds) {
        try {
            Date v = fmt.parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(v);
            c.add(Calendar.SECOND, seconds);
            return fmt.format(c.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parse(String str) {
        try {
            return fmt.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
