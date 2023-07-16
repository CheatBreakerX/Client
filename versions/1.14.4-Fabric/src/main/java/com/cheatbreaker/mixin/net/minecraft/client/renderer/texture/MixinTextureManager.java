package com.cheatbreaker.mixin.net.minecraft.client.renderer.texture;

import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import com.cheatbreaker.bridge.client.renderer.texture.DynamicTextureBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextureManager.class)
public abstract class MixinTextureManager implements TextureManagerBridge {
    @Shadow public abstract void bind(ResourceLocation resource);
    @Shadow public abstract ResourceLocation register(String name, DynamicTexture texture);
    @Shadow public abstract void release(ResourceLocation textureLocation);
    @Shadow public abstract boolean register(ResourceLocation textureLocation, TextureObject textureObj);

    public void bridge$loadTexture(ResourceLocationBridge textureLocation, ThreadDownloadImageDataBridge textureObj) {
        this.register((ResourceLocation) textureLocation, (TextureObject) textureObj);
    }

    public void bridge$bindTexture(ResourceLocationBridge location) {
        this.bind((ResourceLocation) location);
    }

    public ResourceLocationBridge bridge$getDynamicTextureLocation(String name, DynamicTextureBridge texture) {
        return (ResourceLocationBridge) this.register(name, (DynamicTexture) texture);
    }

    public void bridge$deleteTexture(ResourceLocationBridge location) {
        this.release((ResourceLocation) location);
    }
}
