package com.cheatbreaker.bridge.client.renderer.texture;

import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;

public interface TextureManagerBridge {
    void bridge$loadTexture(ResourceLocationBridge playerSkin, ThreadDownloadImageDataBridge skinData);
    void bridge$bindTexture(ResourceLocationBridge location);
    ResourceLocationBridge bridge$getDynamicTextureLocation(String background, DynamicTextureBridge texture);
}
