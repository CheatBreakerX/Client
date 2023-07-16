package com.cheatbreaker.mixin.net.minecraft.client.renderer.texture;

import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import com.cheatbreaker.bridge.client.renderer.texture.DynamicTextureBridge;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DynamicTexture.class)
public abstract class MixinDynamicTexture extends AbstractTexture implements AutoCloseable, DynamicTextureBridge,
        ThreadDownloadImageDataBridge {
    public int bridge$getGlTextureId() {
        return this.getId();
    }
}
