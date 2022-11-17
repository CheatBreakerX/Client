package com.cheatbreaker.bridge.client.renderer.entity;

import com.cheatbreaker.bridge.client.model.ModelBaseBridge;
import com.cheatbreaker.bridge.entity.EntityLivingBaseBridge;

public interface RendererLivingEntityBridge {
    ModelBaseBridge bridge$getRenderPassModel();
    ModelBaseBridge bridge$getMainModel();
    int bridge$shouldRenderPass(EntityLivingBaseBridge entity, int i, float v);
    void bridge$preRenderCallback(EntityLivingBaseBridge par1EntityLivingBase, float v);
}
