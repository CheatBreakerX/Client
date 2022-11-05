package com.cheatbreaker.bridge.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;

public interface RendererLivingEntityBridge {
    ModelBase bridge$getRenderPassModel();
    ModelBase bridge$getMainModel();
    int bridge$shouldRenderPass(EntityLivingBase entity, int i, float v);
    void bridge$preRenderCallback(EntityLivingBase par1EntityLivingBase, float v);
}
