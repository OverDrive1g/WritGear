package com.tnninc.writgear.utils;

public class Converter {
    public static String getTimeAgoByDeltatime(long deltaTime){
        int second = (int) (deltaTime / 1000);
        int minutes = (int) (deltaTime / 60000);
        int hours = (int) (deltaTime / 3600000);
        int day = (int) (deltaTime / 86400000);
        int month = (int) (deltaTime / 2592000000L);
        String time = "";
        if (second >= 0 && minutes == 0 && hours == 0 && day == 0 && month == 0) {
            time = second + " сек. назад";
        } else if (minutes >= 0 && hours == 0 && day == 0 && month == 0) {
            time = minutes + " мин. назад";
        } else if (hours >= 0 && day == 0 && month == 0) {
            time = hours + " час назад";
        } else if (day >= 0 && month == 0) {
            time = day + " д. назад";
        } else if (month >= 0) {
            time = month + " мес. назад";
        }
        return time;
    }
}
