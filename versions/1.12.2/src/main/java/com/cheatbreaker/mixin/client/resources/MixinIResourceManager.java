package com.cheatbreaker.mixin.client.resources;

import com.cheatbreaker.bridge.client.resources.IResourceBridge;
import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;

@Mixin(IResourceManager.class)
public interface MixinIResourceManager extends IResourceManagerBridge {
    @Shadow IResource getResource(ResourceLocation location) throws IOException;

    default IResourceBridge bridge$getResource(ResourceLocationBridge p_110536_1_) throws IOException {
        return (IResourceBridge) this.getResource((ResourceLocation) p_110536_1_);
    }
}
