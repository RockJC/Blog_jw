package com.awei.util;

/**
 * @author jiangwei
 * @create 2022-05-01 15:45
 */
public class StringUtils {
    public static boolean isNullString(String str){
        str = str.trim();
        if ("".equals(str)||str == null){
            return true;
        }
        return false;
    }

}
