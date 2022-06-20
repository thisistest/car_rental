package com.ken.carrental.util;

import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    public static boolean validTime(Date startTime, Date endTime) {
        if (startTime == null || endTime == null
                || endTime.before(startTime) || endTime.equals(startTime)) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        int startMinutes = calendar.get(Calendar.MINUTE);
        calendar.setTime(endTime);
        int endMinutes = calendar.get(Calendar.MINUTE);

        return startMinutes % 30 == 0 && endMinutes % 30 == 0;
    }
}
