package com.cheatbreaker.bridge.ext;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public interface GLBridge {
    void bridge$enableBlend();
    void bridge$disableBlend();
    void bridge$enableTexture2D();
    void bridge$disableTexture2D();
    void bridge$color(float r, float g, float b, float a);
    void bridge$glBlendFunc(int i, int i1, int i2, int i3);
    void bridge$enableAlphaTest();
    void bridge$disableAlphaTest();
    void bridge$setShadeModel(int i);

    boolean bridge$isFramebufferEnabled(); // OpenGlUtils
    boolean bridge$isShadersSupported(); // OpenGlUtils
    void bridge$gluProject(float objx, float objy, float objz, FloatBuffer modelMatrix, FloatBuffer projMatrix, IntBuffer viewport, FloatBuffer win_pos); // GLU
    void bridge$gluPerspective(float v, float v1, float v2, float v3);
}
