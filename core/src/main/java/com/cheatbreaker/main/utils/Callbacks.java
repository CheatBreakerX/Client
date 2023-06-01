package com.cheatbreaker.main.utils;

import com.cheatbreaker.main.CheatBreaker;

public class Callbacks {
    public interface ReturnCallback<T> {
        T run();
    }

    public interface ConnectionCallback {
        void run(String host, int port);
    }

    public static <T> void trySetValue(T value, String valueVisualName, ReturnCallback<T> setCallback) {
        try {
            value = setCallback.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (value == null) {
            CheatBreaker.LOGGER.error("Failed to set value of " + valueVisualName + ".");
        }
    }
}
