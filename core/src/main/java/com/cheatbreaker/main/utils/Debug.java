package com.cheatbreaker.main.utils;

import com.cheatbreaker.main.CheatBreaker;

import java.lang.reflect.Field;

public class Debug {
    public static void logClassFields(Class<?> _class) {
        for (Field field : _class.getDeclaredFields()) {
            CheatBreaker.LOGGER.info(Utility.fmt("{}: {}", field.getName(), field.getType().getCanonicalName()));
        }
    }
}
