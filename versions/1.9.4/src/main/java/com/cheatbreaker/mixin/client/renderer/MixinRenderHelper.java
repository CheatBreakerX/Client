package com.cheatbreaker.mixin.client.renderer;

import com.cheatbreaker.bridge.client.renderer.RenderHelperBridge;
import net.minecraft.client.renderer.RenderHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderHelper.class)
public abstract class MixinRenderHelper implements RenderHelperBridge {
    @Shadow public static void enableStandardItemLighting() {}
    @Shadow public static void disableStandardItemLighting() {}
    @Shadow public static void enableGUIStandardItemLighting() {}

    public void bridge$enableStandardItemLighting() {
        enableStandardItemLighting();
    }

    public void bridge$disableStandardItemLighting() {
        disableStandardItemLighting();
    }

    public void bridge$enableGUIStandardItemLighting() {
        enableGUIStandardItemLighting();
    }
}
