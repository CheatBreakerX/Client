package com.cheatbreaker.mixin.net.minecraft.client.renderer;

import com.cheatbreaker.bridge.client.renderer.RenderGlobalBridge;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LevelRenderer.class)
public abstract class MixinLevelRenderer implements RenderGlobalBridge {
    @Shadow public abstract void allChanged();

    public void bridge$loadRenderers() {
        this.allChanged();
    }
}
