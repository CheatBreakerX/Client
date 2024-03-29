package com.cheatbreaker.bridge.client.gui;

public interface FontRendererBridge {
    int bridge$getColorForID(int id);
    void bridge$drawString(String string, float x, float y, int color);
    int bridge$getStringWidth(String string);
    int bridge$drawStringWithShadow(String message, float x, float y, int color);
}
