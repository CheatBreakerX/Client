package com.cheatbreaker.mixin.client.renderer;

import com.cheatbreaker.bridge.client.renderer.RenderGlobalBridge;
import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderGlobal.class)
public abstract class MixinRenderGlobal implements RenderGlobalBridge {
    @Shadow public abstract void loadRenderers();

    public void bridge$loadRenderers() {
        this.loadRenderers();
    }
}
