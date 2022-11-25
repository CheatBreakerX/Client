package com.cheatbreaker.bridge.ref;

import com.cheatbreaker.bridge.block.BlockBridge;
import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.renderer.RenderHelperBridge;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.client.renderer.entity.RenderManagerBridge;
import com.cheatbreaker.bridge.client.resources.I18nBridge;
import com.cheatbreaker.bridge.entity.boss.BossStatusBridge;
import com.cheatbreaker.bridge.ext.GLBridge;
import com.cheatbreaker.bridge.forge.ForgeEventBusBridge;
import lombok.Getter;
import lombok.Setter;

public class Ref {
    // These fields can be set from the version specific mod and be used equally throughout all versions without having to change much.
    @Getter @Setter
    private static GLBridge glBridge = null;
    @Getter @Setter
    private static MinecraftBridge minecraft = null;
    @Getter @Setter
    private static I18nBridge i18n = null;
    @Getter @Setter
    private static Iterable<BlockBridge> blockRegistry = null;
    @Getter @Setter
    private static BossStatusBridge bossStatus = null;
    @Getter @Setter
    private static RenderHelperBridge renderHelper = null;
    @Getter @Setter
    private static RenderManagerBridge renderManager = null;
    @Getter @Setter
    private static ForgeEventBusBridge forgeEventBus = null;
    @Getter @Setter
    private static TessellatorBridge tessellator = null;
    @Getter @Setter
    private static IInstanceCreator instanceCreator;
    @Getter @Setter
    private static IRefUtils utils;

    public static void modified$drawRect(float left, float top, float right, float bottom, int color) {
        float tempVar;

        if (left < right)
        {
            tempVar = left;
            left = right;
            right = tempVar;
        }

        if (top < bottom)
        {
            tempVar = top;
            top = bottom;
            bottom = tempVar;
        }

        float a = (float)(color >> 24 & 255) / 255.0F;
        float r = (float)(color >> 16 & 255) / 255.0F;
        float g = (float)(color >> 8 & 255) / 255.0F;
        float b = (float)(color & 255) / 255.0F;

        TessellatorBridge tessellator = getTessellator();
        getGlBridge().bridge$enableBlend();
        getGlBridge().bridge$disableTexture2D();
        getGlBridge().bridge$glBlendFunc(770, 771, 1, 0);
        getGlBridge().bridge$color(r, g, b, a);
        tessellator.bridge$startDrawingQuads();
        tessellator.bridge$addVertex(left, bottom, 0.0D);
        tessellator.bridge$addVertex(right, bottom, 0.0D);
        tessellator.bridge$addVertex(right, top, 0.0D);
        tessellator.bridge$addVertex(left, top, 0.0D);
        tessellator.bridge$finish();
        getGlBridge().bridge$enableTexture2D();
        getGlBridge().bridge$disableBlend();
    }

    public static void modified$drawGradientRect(float left, float top, float right, float bottom, int startColor, int endColor)
    {
        float startA = (float)(startColor >> 24 & 255) / 255.0F;
        float startR = (float)(startColor >> 16 & 255) / 255.0F;
        float startG = (float)(startColor >> 8 & 255) / 255.0F;
        float startB = (float)(startColor & 255) / 255.0F;

        float endA = (float)(endColor >> 24 & 255) / 255.0F;
        float endR = (float)(endColor >> 16 & 255) / 255.0F;
        float endG = (float)(endColor >> 8 & 255) / 255.0F;
        float endB = (float)(endColor & 255) / 255.0F;

        getGlBridge().bridge$disableTexture2D();
        getGlBridge().bridge$enableBlend();
        getGlBridge().bridge$disableAlphaTest();
        getGlBridge().bridge$glBlendFunc(770, 771, 1, 0);
        getGlBridge().bridge$setShadeModel(7425);
        TessellatorBridge tessellator = getTessellator();
        tessellator.bridge$startDrawingQuads();
        tessellator.bridge$setColorRGBA_F(startR, startG, startB, startA);
        tessellator.bridge$addVertex(right, top, 0);
        tessellator.bridge$addVertex(left, top, 0);
        tessellator.bridge$setColorRGBA_F(endR, endG, endB, endA);
        tessellator.bridge$addVertex(left, bottom, 0);
        tessellator.bridge$addVertex(right, bottom, 0);
        tessellator.bridge$finish();
        getGlBridge().bridge$setShadeModel(7424);
        getGlBridge().bridge$disableBlend();
        getGlBridge().bridge$enableAlphaTest();
        getGlBridge().bridge$enableTexture2D();
    }

    public static void modified$drawBoxWithOutLine(float f, float f2, float f3, float f4, float f5, int n, int n2) {
        modified$drawRect(f, f2, f3, f4, n2);
        modified$drawRect(f - f5, f2 - f5, f, f4 + f5, n);
        modified$drawRect(f3, f2 - f5, f3 + f5, f4 + f5, n);
        modified$drawRect(f, f2 - f5, f3, f2, n);
        modified$drawRect(f, f4, f3, f4 + f5, n);
    }

    public static void modified$drawRectWithOutline(float f, float f2, float f3, float f4, float f5, int n, int n2) {
        modified$drawRect(f + f5, f2 + f5, f3 - f5, f4 - f5, n2);
        modified$drawRect(f, f2 + f5, f + f5, f4 - f5, n);
        modified$drawRect(f3 - f5, f2 + f5, f3, f4 - f5, n);
        modified$drawRect(f, f2, f3, f2 + f5, n);
        modified$drawRect(f, f4 - f5, f3, f4, n);
    }
}
