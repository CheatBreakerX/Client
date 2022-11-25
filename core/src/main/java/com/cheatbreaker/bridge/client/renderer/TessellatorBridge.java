package com.cheatbreaker.bridge.client.renderer;

public interface TessellatorBridge {
    void bridge$startDrawingQuads();
    void bridge$addVertexWithUV(double x, double y, double z, double u, double v);
    void bridge$finish();
    void bridge$addVertex(double x, double y, double z);
    void bridge$startDrawing(int mode);
    void bridge$setColorOpaque_I(int color);
    void bridge$setColorRGBA_F(float r, float g, float b, float a);
    void bridge$setTranslation(double x, double y, double z);
    void bridge$setColorRGBA_I(int color, int alpha);
}
