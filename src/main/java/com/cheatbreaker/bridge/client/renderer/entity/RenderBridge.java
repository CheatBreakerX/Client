package com.cheatbreaker.bridge.client.renderer.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocationBridge;

public interface RenderBridge {
    ResourceLocationBridge bridge$getEntityTexture(Entity entity);
}
