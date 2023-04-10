package com.cheatbreaker.mixin.client.renderer;

import com.cheatbreaker.bridge.ext.GLColor;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GlStateManager.class)
public class MixinGlStateManager {
    @Inject(method = "color(FFFF)V", at = @At("HEAD"))
    private static void impl$colorFFFF(float r, float g, float b, float a, CallbackInfo callbackInfo) {
        GLColor.glsmCurrentColor.setValues(r, g, b, a);
    }
}
