package com.crawl.api.common;

public class StringUtils {
    public static boolean stringIsNull(String text){
        if(text == null && text.isBlank()) return true;
        return false;
    }

    public static boolean integerIsNull(Integer text){
        if(text == null) return true;
        return false;
    }
}
