package org.ssafy.pasila.global.util;

public class TimeUtil {

    private static TimeUtil instance = new TimeUtil();

    private TimeUtil() {};

    public static TimeUtil getInstance() {
        return instance;
    }

    public String convertTime(Double second) {
        int secs = (int)Math.floor(second);
        return String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, secs % 60);
    }

}
