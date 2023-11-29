package com.shrodinger.common.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeUtils {
    public static String TimeFormat(LocalDateTime pastDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration duration = Duration.between(pastDateTime, currentDateTime);

        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 0) {
            return days + (days == 1 ? "일전" : "일전");
        } else if (hours > 0) {
            return hours + (hours == 1 ? "시간전" : "시간전");
        } else if (minutes > 0) {
            return minutes + (minutes == 1 ? "분전" : "분전");
        } else {
            return "방금전";
        }
    }
}
