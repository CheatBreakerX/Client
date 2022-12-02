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
import com.cheatbreaker.main.identification.MinecraftVersion;
import lombok.Getter;
import lombok.Setter;

public class Ref {
    // These fields can be set from the version specific mod and be used equally throughout all versions without having to change much.
    @Getter @Setter
    private static MinecraftVersion minecraftVersion = null;
    @Getter @Setter
    private static GLBridge glBridge = null;
    @Getter @Setter
    private static IDrawingUtils drawingUtils = null;
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
        getDrawingUtils().drawRect(left, top, right, bottom, color);
    }

    public static void modified$drawGradientRect(float left, float top, float right, float bottom, int startColor, int endColor) {
        getDrawingUtils().drawGradientRect(left, top, right, bottom, startColor, endColor);
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
