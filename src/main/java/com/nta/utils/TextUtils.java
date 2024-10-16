package com.nta.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TextUtils {
    public static String toSnakeCase(String input){
        return input.replaceAll("\\s", "_").toLowerCase();
    }
}
