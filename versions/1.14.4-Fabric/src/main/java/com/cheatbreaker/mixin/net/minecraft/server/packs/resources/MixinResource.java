package com.cheatbreaker.mixin.net.minecraft.server.packs.resources;

import com.cheatbreaker.bridge.client.resources.IResourceBridge;
import net.minecraft.server.packs.resources.Resource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.InputStream;

@Mixin(Resource.class)
public interface MixinResource extends IResourceBridge {
    @Shadow InputStream getInputStream();

    default InputStream bridge$getInputStream() {
        return this.getInputStream();
    }
}
