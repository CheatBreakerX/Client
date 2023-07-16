package com.cheatbreaker.mixin.net.minecraft.server.packs.resources;

import com.cheatbreaker.bridge.client.resources.IResourceBridge;
import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;

@Mixin(ResourceManager.class)
public interface MixinResourceManager extends IResourceManagerBridge {
    @Shadow Resource getResource(ResourceLocation arg) throws IOException;

    default IResourceBridge bridge$getResource(ResourceLocationBridge p_110536_1_) throws IOException {
        return (IResourceBridge) this.getResource((ResourceLocation) p_110536_1_);
    }
}
