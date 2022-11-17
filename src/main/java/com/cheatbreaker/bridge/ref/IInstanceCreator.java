package com.cheatbreaker.bridge.ref;

import com.cheatbreaker.bridge.client.audio.ISoundBridge;
import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.bridge.client.renderer.IImageBufferBridge;
import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import com.cheatbreaker.bridge.client.renderer.texture.DynamicTextureBridge;
import com.cheatbreaker.bridge.client.settings.KeyBindingBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;

import java.awt.image.BufferedImage;
import java.io.File;

public interface IInstanceCreator {
    ResourceLocationBridge createResourceLocationBridge(String domain, String path);
    default ResourceLocationBridge createResourceLocationBridge(String path) {
        return this.createResourceLocationBridge("minecraft", path);
    }

    ThreadDownloadImageDataBridge createThreadDownloadImageData(File p_i1049_1_, String p_i1049_2_, ResourceLocationBridge p_i1049_3_, IImageBufferBridge p_i1049_4_);
    ScaledResolutionBridge createScaledResolutionBridge();
    DynamicTextureBridge createDynamicTexture(BufferedImage img);
    DynamicTextureBridge createDynamicTexture(int i, int i1);
    ISoundBridge createSoundFromPSR(ResourceLocationBridge location, float volume);
    KeyBindingBridge createKeyBinding(String description, int keyCode, String category);
}
