package com.material.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String dateFormat(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}