package com.cheatbreaker.mixin.blaze3d.platform;

import com.cheatbreaker.bridge.ext.GLColor;
import com.mojang.blaze3d.platform.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GlStateManager.class)
public class MixinGlStateManager {
    @Inject(method = "color4f(FFFF)V", at = @At("HEAD"))
    private static void impl$color4f(float r, float g, float b, float a, CallbackInfo callbackInfo) {
        GLColor.glsmCurrentColor.setValues(r, g, b, a);
    }

    @Inject(method = "color3f(FFF)V", at = @At("HEAD"))
    private static void impl$color3f(float r, float g, float b, CallbackInfo callbackInfo) {
        GLColor.glsmCurrentColor.setValues(r, g, b, 1f);
    }

    @Inject(method = "clearColor(FFFF)V", at = @At("HEAD"))
    private static void impl$clearColor(float r, float g, float b, float a, CallbackInfo callbackInfo) {
        GLColor.glsmCurrentColor.setValues(r, g, b, a);
    }
}
