package com.tnninc.writgear.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExHelper {

    public static String getFirstSimbolFromString(String string){

        String regex = "^\\w";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        if (matcher.find()){
            return string.substring(0,1);
        } else {
            return string.substring(0, 2);
        }
    }
}
