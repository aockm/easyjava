package com.tockm.utils;

public class StringUtils {
    public static String upperCaseFirstLetter(String str) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(str)) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String lowerCaseFirstLetter(String str) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(str)) return str;
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}


