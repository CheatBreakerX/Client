package com.cheatbreaker.mixin.client.resources;

import com.cheatbreaker.bridge.client.resources.IResourceBridge;
import net.minecraft.client.resources.IResource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.InputStream;

@Mixin(IResource.class)
public interface MixinIResource extends IResourceBridge {
    @Shadow InputStream getInputStream();

    default InputStream bridge$getInputStream() {
        return this.getInputStream();
    }
}
