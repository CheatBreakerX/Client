package com.cheatbreaker.bridge.ref;

import com.cheatbreaker.bridge.util.ResourceLocationBridge;

public interface IDrawingUtils {
    void drawGradientRect(float left, float top, float right, float bottom, int startColor, int endColor);
    void drawRect(float left, float top, float right, float bottom, int color);
    void renderSkybox(ResourceLocationBridge[] images, float speed, int scaledWidth, int scaledHeight, int panoramaTimer, ResourceLocationBridge backgroundLocation);
}
