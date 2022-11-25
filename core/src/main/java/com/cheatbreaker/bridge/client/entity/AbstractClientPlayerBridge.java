package com.cheatbreaker.bridge.client.entity;

import com.cheatbreaker.bridge.entity.player.EntityPlayerBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;

public interface AbstractClientPlayerBridge extends EntityPlayerBridge {
    void bridge$setLocationCape(ResourceLocationBridge location);
    String bridge$getDisplayName();
}
