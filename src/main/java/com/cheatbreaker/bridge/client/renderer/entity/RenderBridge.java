package com.cheatbreaker.bridge.client.renderer.entity;

import com.cheatbreaker.bridge.entity.EntityBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;

public interface RenderBridge {
    ResourceLocationBridge bridge$getEntityTexture(EntityBridge entity);
}
