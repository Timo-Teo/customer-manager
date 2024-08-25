package com.camuendo.timoteo.customermanager.common.util;

import java.lang.reflect.Field;

public class NullFieldChecker {

    public static boolean areAllFieldsNull(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(obj) != null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                System.out.println("Error accessing field " + field.getName());
            }
        }
        return true;
    }
}