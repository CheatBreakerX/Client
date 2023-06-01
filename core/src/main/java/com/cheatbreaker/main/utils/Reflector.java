package com.cheatbreaker.main.utils;

import com.cheatbreaker.main.CheatBreaker;

import java.lang.reflect.Field;

public class Reflector {
    public static Field findField(Class<?> clazz, String... fieldNames) {
        Exception failed = null;
        for (String fieldName : fieldNames) {
            try {
                Field f = clazz.getDeclaredField(fieldName);
                f.setAccessible(true);
                return f;
            }
            catch (Exception e) {
                failed = e;
            }
        }

        if (failed != null) {
            failed.printStackTrace();
        }

        return null;
    }

    public static <T> T getFieldValue(Object instance, String... fieldNames) {
        Object returnValue = null;
        try {
            if (instance != null) {
                Field field = findField(instance.getClass(), fieldNames);
                if (field != null) {
                    returnValue = field.get(instance);
                } else {
                    CheatBreaker.LOGGER.warn(Utility.fmt("Could not find field(s) [{}] in '{}'.",
                            String.join(",", fieldNames),
                            instance.getClass().getCanonicalName()
                    ));
                }
            } else {
                CheatBreaker.LOGGER.warn(Utility.fmt("Could not find field(s) [{}] in a null instance.", String.join(",", fieldNames)));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (T) returnValue;
    }
}
