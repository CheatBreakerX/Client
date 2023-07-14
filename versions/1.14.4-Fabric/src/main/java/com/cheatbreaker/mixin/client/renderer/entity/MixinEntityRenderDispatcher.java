package com.cheatbreaker.mixin.client.renderer.entity;

import com.cheatbreaker.bridge.client.renderer.entity.RenderManagerBridge;
import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.client.shader.ShaderGroupBridge;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityRenderDispatcher.class)
public class MixinEntityRenderDispatcher implements RenderManagerBridge {
    @Shadow private double xOff;
    @Shadow private double yOff;
    @Shadow private double zOff;
    @Shadow public float playerRotX;
    @Shadow public float playerRotY;

    public double bridge$getRenderPosX() {
        return this.xOff;
    }

    public double bridge$getRenderPosY() {
        return this.yOff;
    }

    public double bridge$getRenderPosZ() {
        return this.zOff;
    }

    public float bridge$getPlayerViewX() {
        return this.playerRotX;
    }

    public float bridge$getPlayerViewY() {
        return this.playerRotY;
    }
}
