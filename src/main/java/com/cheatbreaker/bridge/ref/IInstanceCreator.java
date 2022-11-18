package com.cheatbreaker.bridge.ref;

import com.cheatbreaker.bridge.client.audio.ISoundBridge;
import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.bridge.client.renderer.IImageBufferBridge;
import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import com.cheatbreaker.bridge.client.renderer.entity.RenderItemBridge;
import com.cheatbreaker.bridge.client.renderer.texture.DynamicTextureBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.client.settings.KeyBindingBridge;
import com.cheatbreaker.bridge.client.shader.FrameBufferBridge;
import com.cheatbreaker.bridge.client.shader.ShaderGroupBride;
import com.cheatbreaker.bridge.item.ItemBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.bridge.util.ChatComponentTextBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.bridge.util.Vec3Bridge;

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
    DynamicTextureBridge createDynamicTexture(int width, int height);
    ISoundBridge createSoundFromPSR(ResourceLocationBridge location, float volume);
    KeyBindingBridge createKeyBinding(String description, int keyCode, String category);
    Vec3Bridge createVec3(double x, double y, double z);
    ItemStackBridge createItemStack(ItemBridge item);
    RenderItemBridge createRenderItem();
    ShaderGroupBride createShaderGroup(TextureManagerBridge p_i1050_1_, IResourceManagerBridge p_i1050_2_, FrameBufferBridge p_i1050_3_, ResourceLocationBridge p_i1050_4_);
    ChatComponentTextBridge createChatComponentText(String initialString);
}
