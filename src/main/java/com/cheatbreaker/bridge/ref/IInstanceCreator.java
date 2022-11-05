package com.cheatbreaker.bridge.ref;

import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.bridge.client.renderer.IImageBufferBridge;
import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import com.cheatbreaker.bridge.client.renderer.texture.DynamicTextureBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;

import java.awt.image.BufferedImage;
import java.io.File;

public interface IInstanceCreator {
    ResourceLocationBridge createResourceLocation(String domain, String path);
    default ResourceLocationBridge createResourceLocation(String path) {
        return this.createResourceLocation("minecraft", path);
    }

    ThreadDownloadImageDataBridge createThreadDownloadImageData(File p_i1049_1_, String p_i1049_2_, ResourceLocationBridge p_i1049_3_, IImageBufferBridge p_i1049_4_);
    ScaledResolutionBridge createScaledResolution();
    DynamicTextureBridge createDynamicTexture(BufferedImage img);
    DynamicTextureBridge createDynamicTexture(int i, int i1);
}
