package com.cheatbreaker.mixin.client.renderer.entity;

import com.cheatbreaker.bridge.client.renderer.entity.RenderBridge;
import com.cheatbreaker.bridge.entity.EntityBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Render.class)
public abstract class MixinRender implements RenderBridge {
    @Shadow protected abstract ResourceLocation getEntityTexture(Entity p_110775_1_);

    public ResourceLocationBridge bridge$getEntityTexture(EntityBridge entity) {
        return (ResourceLocationBridge) this.getEntityTexture((Entity) entity);
    }
}