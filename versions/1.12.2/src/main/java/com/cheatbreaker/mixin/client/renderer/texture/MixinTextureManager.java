package com.cheatbreaker.mixin.client.renderer.texture;

import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import com.cheatbreaker.bridge.client.renderer.texture.DynamicTextureBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextureManager.class)
public abstract class MixinTextureManager implements TextureManagerBridge {
    @Shadow public abstract boolean loadTexture(ResourceLocation textureLocation, ITextureObject textureObj);
    @Shadow public abstract void bindTexture(ResourceLocation resource);
    @Shadow public abstract ResourceLocation getDynamicTextureLocation(String name, DynamicTexture texture);
    @Shadow public abstract void deleteTexture(ResourceLocation textureLocation);

    public void bridge$loadTexture(ResourceLocationBridge playerSkin, ThreadDownloadImageDataBridge skinData) {
        this.loadTexture((ResourceLocation) playerSkin, (ThreadDownloadImageData) skinData);
    }

    public void bridge$bindTexture(ResourceLocationBridge location) {
        this.bindTexture((ResourceLocation) location);
    }

    public ResourceLocationBridge bridge$getDynamicTextureLocation(String background, DynamicTextureBridge texture) {
        return (ResourceLocationBridge) this.getDynamicTextureLocation(background, (DynamicTexture) texture);
    }

    public void bridge$deleteTexture(ResourceLocationBridge location) {
        this.deleteTexture((ResourceLocation) location);
    }
}
