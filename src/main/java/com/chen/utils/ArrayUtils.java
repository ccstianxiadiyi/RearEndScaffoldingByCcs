package com.chen.utils;

import java.util.Objects;

public class ArrayUtils {
    public static boolean isExist(String[] arr,String value){
        for (String s : arr) {
            if (Objects.equals(s, value)){
                return true;
            }
        }
        return false;
    }


}
