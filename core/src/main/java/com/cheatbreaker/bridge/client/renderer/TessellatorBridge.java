package com.cheatbreaker.bridge.client.renderer;

public interface TessellatorBridge {
    void bridge$startDrawingQuads();
    void bridge$finish();
    void bridge$startDrawing(int mode);
    TessellatorBridge bridge$setColorOpaque_I(int color);
    void bridge$setColorRGBA_F(float r, float g, float b, float a);
    void bridge$setTranslation(double x, double y, double z);
    void bridge$setColorRGBA_I(int color, int alpha);

    TessellatorBridge bridge$pos(double x, double y, double z);
    default TessellatorBridge bridge$pos(double x, double y) {
        return this.bridge$pos(x, y, 0);
    }
    TessellatorBridge bridge$tex(double u, double v);
    TessellatorBridge bridge$color(float r, float g, float b, float a);
    TessellatorBridge bridge$color(int r, int g, int b, int a);
    TessellatorBridge bridge$inheritGLSMColor();
    void bridge$endVertex();
    TessellatorBridge bridge$begin(int glMode, String vertexFmt);
}
