package com.cheatbreaker.bridge.client.gui;

public interface FontRendererBridge {
    int[] bridge$getColorCode();
    void bridge$drawString(String string, float x, float y, int color);
    int bridge$getStringWidth(String string);
    void bridge$drawStringWithShadow(String message, float x, float y, int color);
}
