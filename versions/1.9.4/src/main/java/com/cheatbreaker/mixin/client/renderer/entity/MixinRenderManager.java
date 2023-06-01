package com.cheatbreaker.mixin.client.renderer.entity;

import com.cheatbreaker.bridge.client.renderer.entity.RenderManagerBridge;
import net.minecraft.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderManager.class)
public class MixinRenderManager implements RenderManagerBridge {
    @Shadow private double renderPosX;
    @Shadow private double renderPosY;
    @Shadow private double renderPosZ;
    @Shadow public float playerViewX;
    @Shadow public float playerViewY;

    public double bridge$getRenderPosX() {
        return this.renderPosX;
    }

    public double bridge$getRenderPosY() {
        return this.renderPosY;
    }

    public double bridge$getRenderPosZ() {
        return this.renderPosZ;
    }

    public float bridge$getPlayerViewX() {
        return this.playerViewX;
    }

    public float bridge$getPlayerViewY() {
        return this.playerViewY;
    }
}
