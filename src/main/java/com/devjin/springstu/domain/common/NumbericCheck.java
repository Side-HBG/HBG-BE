package com.devjin.springstu.domain.common;

public class NumbericCheck {
    public static boolean isNumberric(String str) {
        return str != null && str.matches("[0-9.]+");
    }
}
