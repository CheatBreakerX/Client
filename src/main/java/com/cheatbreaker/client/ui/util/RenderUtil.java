package com.cheatbreaker.client.ui.util;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import org.lwjgl.opengl.GL11;

public class RenderUtil {
    protected static float zLevel = 0.0f;

    public static void drawTexturedModalRect(float left, float top, float right, float bottom, int u, int v) {
        float multiplier = 0.00390625f;
        TessellatorBridge tessellator = Ref.getTessellator();
        tessellator.bridge$startDrawingQuads();
        tessellator.bridge$addVertexWithUV(left, top + (float)v, zLevel, right * multiplier, (bottom + (float)v) * multiplier);
        tessellator.bridge$addVertexWithUV(left + (float)u, top + (float)v, zLevel, (right + (float)u) * multiplier, (bottom + (float)v) * multiplier);
        tessellator.bridge$addVertexWithUV(left + (float)u, top, zLevel, (right + (float)u) * multiplier, bottom * multiplier);
        tessellator.bridge$addVertexWithUV(left, top, zLevel, right * multiplier, bottom * multiplier);
        tessellator.bridge$finish();
    }

    public static void drawIcon(ResourceLocationBridge location, float size, float x, float y) {
        float width = size * 2.0f;
        float height = size * 2.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        GL11.glEnable(3042);
        Ref.getMinecraft().bridge$getTextureManager().bridge$bindTexture(location);
        GL11.glBegin(7);
        GL11.glTexCoord2d(f6 / size, f7 / size);
        GL11.glVertex2d(x, y);
        GL11.glTexCoord2d(f6 / size, (f7 + size) / size);
        GL11.glVertex2d(x, y + height);
        GL11.glTexCoord2d((f6 + size) / size, (f7 + size) / size);
        GL11.glVertex2d(x + width, y + height);
        GL11.glTexCoord2d((f6 + size) / size, f7 / size);
        GL11.glVertex2d(x + width, y);
        GL11.glEnd();
        GL11.glDisable(3042);
    }

    public static void drawIcon(String location, float size, float x, float y) {
        drawIcon(Ref.getInstanceCreator().createResourceLocation(location), size, x, y);
    }

    public static void lIIIIlIIllIIlIIlIIIlIIllI(ResourceLocationBridge resourceLocation, float f, float f2, float f3, float f4) {
        float f5 = f3 / 2.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        GL11.glEnable(3042);
        Ref.getMinecraft().bridge$getTextureManager().bridge$bindTexture(resourceLocation);
        GL11.glBegin(7);
        GL11.glTexCoord2d(f6 / f5, f7 / f5);
        GL11.glVertex2d(f, f2);
        GL11.glTexCoord2d(f6 / f5, (f7 + f5) / f5);
        GL11.glVertex2d(f, f2 + f4);
        GL11.glTexCoord2d((f6 + f5) / f5, (f7 + f5) / f5);
        GL11.glVertex2d(f + f3, f2 + f4);
        GL11.glTexCoord2d((f6 + f5) / f5, f7 / f5);
        GL11.glVertex2d(f + f3, f2);
        GL11.glEnd();
        GL11.glDisable(3042);
    }

    public static void lIIIIlIIllIIlIIlIIIlIIllI(int x, int y, int width, int height, float f, int n5) {
        int n6 = height - y;
        int n7 = width - x;
        int n8 = n5 - height;
        GL11.glScissor((int)((float)x * f), (int)((float)n8 * f), (int)((float)n7 * f), (int)((float)n6 * f));
    }

    public static void drawRoundedRect(double left, double top, double right, double bottom, double rounding, int color) {
        int i;
        float a = (float)(color >> 24 & 0xFF) / (float)255;
        float r = (float)(color >> 16 & 0xFF) / (float)255;
        float g = (float)(color >> 8 & 0xFF) / (float)255;
        float b = (float)(color & 0xFF) / (float)255;

        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);

        left *= 2;
        top *= 2;
        right *= 2;
        bottom *= 2;

        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glColor4f(r, g, b, a);
        GL11.glEnable(2848);
        GL11.glBegin(9);
        for (i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(left + rounding + Math.sin((double)i * (Math.PI) / 180) * (rounding * -1), top + rounding + Math.cos((double)i * (Math.PI) / 180) * (rounding * -1));
        }
        for (i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(left + rounding + Math.sin((double)i * (Math.PI) / 180) * (rounding * -1), bottom - rounding + Math.cos((double)i * (Math.PI) / 180) * (rounding * -1));
        }
        for (i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(right - rounding + Math.sin((double)i * (Math.PI) / 180) * rounding, bottom - rounding + Math.cos((double)i * (Math.PI) / 180) * rounding);
        }
        for (i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(right - rounding + Math.sin((double)i * (Math.PI) / 180) * rounding, top + rounding + Math.cos((double)i * (Math.PI) / 180) * rounding);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glScaled(2, 2, 2);
        GL11.glPopAttrib();
    }

    public static void lIIIIlIIllIIlIIlIIIlIIllI(double d, double d2, double d3) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        TessellatorBridge tessellator = Ref.getTessellator();
        tessellator.bridge$startDrawing(6);
        tessellator.bridge$addVertex(d, d2, zLevel);
        double d4 = 3.0 * 2.0943951023931953;
        double d5 = d4 / (double)30;
        for (double d6 = -d5; d6 < d4; d6 += d5) {
            tessellator.bridge$addVertex(d + d3 * Math.cos(-d6), d2 + d3 * Math.sin(-d6), zLevel);
        }
        tessellator.bridge$finish();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static void lIIIIlIIllIIlIIlIIIlIIllI(double d, double d2, double d3, double d4, double d5, int n, double d6) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        d5 = (d5 + (double)n) % (double)n;
        TessellatorBridge tessellator = Ref.getTessellator();
        for (double d7 = (double)360 / (double)n * d5; d7 < (double)360 / (double)n * (d5 + d6); d7 += 1.0) {
            double d8 = d7 * (0.6976743936538696 * 4.502949631183398) / (double)180;
            double d9 = (d7 - 1.0) * (1.9384295391612096 * 1.6206896305084229) / (double)180;
            double[] arrd = new double[]{Math.cos(d8) * d3, -Math.sin(d8) * d3, Math.cos(d9) * d3, -Math.sin(d9) * d3};
            double[] arrd2 = new double[]{Math.cos(d8) * d4, -Math.sin(d8) * d4, Math.cos(d9) * d4, -Math.sin(d9) * d4};
            tessellator.bridge$startDrawing(7);
            tessellator.bridge$addVertex(d + arrd2[0], d2 + arrd2[1], 0.0);
            tessellator.bridge$addVertex(d + arrd2[2], d2 + arrd2[3], 0.0);
            tessellator.bridge$addVertex(d + arrd[2], d2 + arrd[3], 0.0);
            tessellator.bridge$addVertex(d + arrd[0], d2 + arrd[1], 0.0);
            tessellator.bridge$finish();
        }
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
    }

    public static void drawHorizontalLine(float left, float right, float top, int color) {
        if (right < left) {
            float temp = left;
            left = right;
            right = temp;
        }
        Ref.modified$drawRect(left, top, right + 1.0f, top + 1.0f, color);
    }

    public static void drawVerticalLine(float left, float top, float bottom, int color) {
        if (bottom < top) {
            float temp = top;
            top = bottom;
            bottom = temp;
        }
        Ref.modified$drawRect(left, top + 1.0f, left + 1.0f, bottom, color);
    }

    public static void lIIIIlIIllIIlIIlIIIlIIllI(float f, float f2, float f3, float f4, int n, int n2, int n3) {
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Ref.modified$drawGradientRect((f *= 2.0f) + 1.0f, (f2 *= 2.0f) + 1.0f, (f3 *= 2.0f) - 1.0f, (f4 *= 2.0f) - 1.0f, n2, n3);
        RenderUtil.drawVerticalLine(f, f2 + 1.0f, f4 - 2.0f, n);
        RenderUtil.drawVerticalLine(f3 - 1.0f, f2 + 1.0f, f4 - 2.0f, n);
        RenderUtil.drawHorizontalLine(f + 2.0f, f3 - (float)3, f2, n);
        RenderUtil.drawHorizontalLine(f + 2.0f, f3 - (float)3, f4 - 1.0f, n);
        RenderUtil.drawHorizontalLine(f + 1.0f, f + 1.0f, f2 + 1.0f, n);
        RenderUtil.drawHorizontalLine(f3 - 2.0f, f3 - 2.0f, f2 + 1.0f, n);
        RenderUtil.drawHorizontalLine(f3 - 2.0f, f3 - 2.0f, f4 - 2.0f, n);
        RenderUtil.drawHorizontalLine(f + 1.0f, f + 1.0f, f4 - 2.0f, n);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }

    public static long timeOfPreviousFrame = 0L;
    public static long timeOfCurrentFrame = 0L;
    public static long frameTimeNs = 0L;

    public static int minFps = 2147483647;
    public static int maxFps = 0;

    public static void updateFrameTime(long previous) {
        timeOfPreviousFrame = previous;
        timeOfCurrentFrame = System.nanoTime();
        frameTimeNs = timeOfCurrentFrame - timeOfPreviousFrame;
    }

    public static int getTimeAccurateFrameRate() {
        float frameRateAsFloat = 1000000000f / (frameTimeNs < 1 ? 1 : frameTimeNs);
        int frameRateAsInt = (int) frameRateAsFloat;
        if (frameRateAsInt > maxFps) {
            maxFps = frameRateAsInt;
        }
        if (frameRateAsInt < minFps) {
            minFps = frameRateAsInt;
        }
        return frameRateAsInt;
    }

    public static float getFrameTimeAsMs() {
        return (frameTimeNs < 1 ? 1 : frameTimeNs) / 1000000f;
    }
}
