package com.cheatbreaker.client.ui.util;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;

public class RenderUtil {
    protected static float zLevel = 0.0f;

    public static void drawTexturedModalRect(float left, float top, float right, float bottom, int u, int v) {
        float multiplier = 0.00390625f;
        TessellatorBridge tessellator = Ref.getTessellator();
        tessellator.bridge$startDrawingQuads();
        tessellator.bridge$pos(left, top + (float)v, zLevel).bridge$tex(right * multiplier, (bottom + (float)v) * multiplier).bridge$endVertex();
        tessellator.bridge$pos(left + (float)u, top + (float)v, zLevel).bridge$tex((right + (float)u) * multiplier, (bottom + (float)v) * multiplier).bridge$endVertex();
        tessellator.bridge$pos(left + (float)u, top, zLevel).bridge$tex((right + (float)u) * multiplier, bottom * multiplier).bridge$endVertex();
        tessellator.bridge$pos(left, top, zLevel).bridge$tex(right * multiplier, bottom * multiplier).bridge$endVertex();
        tessellator.bridge$finish();
    }

    public static void drawIcon(ResourceLocationBridge location, float size, float x, float y) {
        float width = size * 2.0f;
        float height = size * 2.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        Ref.getGlBridge().bridge$enableBlend();
        Ref.getMinecraft().bridge$getTextureManager().bridge$bindTexture(location);
        Ref.getGlBridge().bridge$begin(7);
        Ref.getGlBridge().bridge$texCoord2d(f6 / size, f7 / size);
        Ref.getGlBridge().bridge$vertex2d(x, y);
        Ref.getGlBridge().bridge$texCoord2d(f6 / size, (f7 + size) / size);
        Ref.getGlBridge().bridge$vertex2d(x, y + height);
        Ref.getGlBridge().bridge$texCoord2d((f6 + size) / size, (f7 + size) / size);
        Ref.getGlBridge().bridge$vertex2d(x + width, y + height);
        Ref.getGlBridge().bridge$texCoord2d((f6 + size) / size, f7 / size);
        Ref.getGlBridge().bridge$vertex2d(x + width, y);
        Ref.getGlBridge().bridge$end();
        Ref.getGlBridge().bridge$disableBlend();
    }

    public static void drawIcon(String location, float size, float x, float y) {
        drawIcon(Ref.getInstanceCreator().createResourceLocation(location), size, x, y);
    }

    public static void renderIcon(ResourceLocationBridge location, float x, float y, float width, float height) {
        float midWidth = width / 2.0f;
        float u = 0.0f;
        float v = 0.0f;
        Ref.getGlBridge().bridge$enableBlend();
        Ref.getMinecraft().bridge$getTextureManager().bridge$bindTexture(location);
        Ref.getGlBridge().bridge$begin(7);
        Ref.getGlBridge().bridge$texCoord2d(u / midWidth, v / midWidth);
        Ref.getGlBridge().bridge$vertex2d(x, y);
        Ref.getGlBridge().bridge$texCoord2d(u / midWidth, (v + midWidth) / midWidth);
        Ref.getGlBridge().bridge$vertex2d(x, y + height);
        Ref.getGlBridge().bridge$texCoord2d((u + midWidth) / midWidth, (v + midWidth) / midWidth);
        Ref.getGlBridge().bridge$vertex2d(x + width, y + height);
        Ref.getGlBridge().bridge$texCoord2d((u + midWidth) / midWidth, v / midWidth);
        Ref.getGlBridge().bridge$vertex2d(x + width, y);
        Ref.getGlBridge().bridge$end();
        Ref.getGlBridge().bridge$disableBlend();
    }

    public static void scissorArea(int left, int top, int right, int bottom, float scale, int scaledHeight) {
        int width = right - left;
        int height = bottom - top;
        int invertedBottomPos = scaledHeight - bottom;
        Ref.getGlBridge().bridge$scissor((int)((float)left * scale), (int)((float)invertedBottomPos * scale), (int)((float)width * scale), (int)((float)height * scale));
    }

    public static void drawRoundedRect(double left, double top, double right, double bottom, double rounding, int color) {
        int i;
        float a = (float)(color >> 24 & 0xFF) / (float)255;
        float r = (float)(color >> 16 & 0xFF) / (float)255;
        float g = (float)(color >> 8 & 0xFF) / (float)255;
        float b = (float)(color & 0xFF) / (float)255;

        Ref.getGlBridge().bridge$pushAttrib(0);
        Ref.getGlBridge().bridge$scale(0.5f, 0.5f, 0.5f);

        left *= 2;
        top *= 2;
        right *= 2;
        bottom *= 2;

        Ref.getGlBridge().bridge$enableBlend();
        Ref.getGlBridge().bridge$disableTexture2D();
        Ref.getGlBridge().bridge$color(r, g, b, a);
        Ref.getGlBridge().bridge$enableLineSmooth();
        Ref.getGlBridge().bridge$begin(9);
        for (i = 0; i <= 90; i += 3) {
            Ref.getGlBridge().bridge$vertex2d(left + rounding + Math.sin((double)i * (Math.PI) / 180) * (rounding * -1), top + rounding + Math.cos((double)i * (Math.PI) / 180) * (rounding * -1));
        }
        for (i = 90; i <= 180; i += 3) {
            Ref.getGlBridge().bridge$vertex2d(left + rounding + Math.sin((double)i * (Math.PI) / 180) * (rounding * -1), bottom - rounding + Math.cos((double)i * (Math.PI) / 180) * (rounding * -1));
        }
        for (i = 0; i <= 90; i += 3) {
            Ref.getGlBridge().bridge$vertex2d(right - rounding + Math.sin((double)i * (Math.PI) / 180) * rounding, bottom - rounding + Math.cos((double)i * (Math.PI) / 180) * rounding);
        }
        for (i = 90; i <= 180; i += 3) {
            Ref.getGlBridge().bridge$vertex2d(right - rounding + Math.sin((double)i * (Math.PI) / 180) * rounding, top + rounding + Math.cos((double)i * (Math.PI) / 180) * rounding);
        }
        Ref.getGlBridge().bridge$end();
        Ref.getGlBridge().bridge$enableTexture2D();
        Ref.getGlBridge().bridge$disableBlend();
        Ref.getGlBridge().bridge$disableLineSmooth();
        Ref.getGlBridge().bridge$disableBlend();
        Ref.getGlBridge().bridge$enableTexture2D();
        Ref.getGlBridge().bridge$scale(2, 2, 2);
        Ref.getGlBridge().bridge$popAttrib();
    }

    public static void renderCircle(double x, double y, double radius) {
        Ref.getGlBridge().bridge$enableBlend();
        Ref.getGlBridge().bridge$disableTexture2D();
        Ref.getGlBridge().bridge$blendFunc(770, 771);
        TessellatorBridge tessellator = Ref.getTessellator();

        tessellator.bridge$begin(6 /* GL_TRIANGLE_FAN */, "position");
        tessellator.bridge$pos(x, y, 1.0D).bridge$endVertex();

        int numSides = 30;

        double doublePi = Math.PI * 2D;
        double vertexGap = doublePi / numSides;

        for (double angle = -vertexGap; angle < doublePi; angle += vertexGap) {
            tessellator.bridge$pos(x + radius * Math.cos(-angle), y + radius * Math.sin(-angle), 10.0D)
                    .bridge$endVertex();
        }

        tessellator.bridge$finish();
        Ref.getGlBridge().bridge$enableTexture2D();
        Ref.getGlBridge().bridge$disableBlend();
    }

    public static void renderHollowCircle(double d, double d2, double d3, double d4, double d5, int n, double d6) {
        Ref.getGlBridge().bridge$enableBlend();
        Ref.getGlBridge().bridge$disableTexture2D();
        Ref.getGlBridge().bridge$enableLineSmooth();
        d5 = (d5 + n) % n;
        TessellatorBridge tessellator = Ref.getTessellator();

        for (double d7 = 360d / n * d5; d7 < 360d / n * (d5 + d6); d7 += 1.0) {
            double d8 = d7 * Math.PI / 180d;
            double d9 = (d7 - 1.0) * Math.PI / 180d;

            double[] arrd = new double[] {
                    Math.cos(d8) * d3,
                    -Math.sin(d8) * d3,
                    Math.cos(d9) * d3,
                    -Math.sin(d9) * d3
            };

            double[] arrd2 = new double[] {
                    Math.cos(d8) * d4,
                    -Math.sin(d8) * d4,
                    Math.cos(d9) * d4,
                    -Math.sin(d9) * d4
            };

            tessellator.bridge$startDrawing(7);
            tessellator.bridge$pos(d + arrd2[0], d2 + arrd2[1], 0.0).bridge$inheritGLSMColor().bridge$endVertex();
            tessellator.bridge$pos(d + arrd2[2], d2 + arrd2[3], 0.0).bridge$inheritGLSMColor().bridge$endVertex();
            tessellator.bridge$pos(d + arrd[2], d2 + arrd[3], 0.0).bridge$inheritGLSMColor().bridge$endVertex();
            tessellator.bridge$pos(d + arrd[0], d2 + arrd[1], 0.0).bridge$inheritGLSMColor().bridge$endVertex();
            tessellator.bridge$finish();
        }
        Ref.getGlBridge().bridge$enableTexture2D();
        Ref.getGlBridge().bridge$disableBlend();
        Ref.getGlBridge().bridge$disableLineSmooth();
        Ref.getGlBridge().bridge$disableBlend();
        Ref.getGlBridge().bridge$enableTexture2D();
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

    public static void drawCorneredGradientRectWithOutline(float left, float top, float right, float bottom,
                                                           int outlineColour, int gradientStart, int gradientEnd) {
        float scaleFactor = Ref.getInstanceCreator().createScaledResolution().bridge$getScaleFactor();
        float scaleModifier = 1f / Ref.getInstanceCreator().createScaledResolution().bridge$getScaleFactor();

        Ref.getGlBridge().bridge$scale(scaleModifier, scaleModifier, scaleModifier);
        Ref.modified$drawGradientRect((left *= scaleFactor) + 1.0f,
                (top *= scaleFactor) + 1f,
                (right *= scaleFactor) - 1f,
                (bottom *= scaleFactor) - 1f,
                gradientStart, gradientEnd);
        RenderUtil.drawVerticalLine(left, top + 1f, bottom - 2.0f, outlineColour);
        RenderUtil.drawVerticalLine(right - 1f, top + 1f, bottom - 2f, outlineColour);
        RenderUtil.drawHorizontalLine(left + 2f, right - 3f, top, outlineColour);
        RenderUtil.drawHorizontalLine(left + 2f, right - 3f, bottom - 1f, outlineColour);
        RenderUtil.drawHorizontalLine(left + 1f, left + 1f, top + 1f, outlineColour);
        RenderUtil.drawHorizontalLine(right - 2f, right - 2f, top + 1f, outlineColour);
        RenderUtil.drawHorizontalLine(right - 2f, right - 2f, bottom - 2f, outlineColour);
        RenderUtil.drawHorizontalLine(left + 1f, left + 1f, bottom - 2f, outlineColour);
        Ref.getGlBridge().bridge$scale(scaleFactor, scaleFactor, scaleFactor);
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
        float frameRateAsFloat = 1000000000f / (frameTimeNs == 0 ? 1 : frameTimeNs);
        if (frameTimeNs == 0) {
            frameRateAsFloat = 0f;
        }
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
        return frameTimeNs == 0 ? 0f : frameTimeNs / 1000000f;
    }
}
