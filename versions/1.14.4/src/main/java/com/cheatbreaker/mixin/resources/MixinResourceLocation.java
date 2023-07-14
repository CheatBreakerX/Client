package com.cheatbreaker.mixin.resources;

import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ResourceLocation.class)
public class MixinResourceLocation implements ResourceLocationBridge {
    @Shadow @Final protected String namespace;
    @Shadow @Final protected String path;

    public String bridge$getResourceDomain() {
        return this.namespace;
    }

    public String bridge$getResourcePath() {
        return this.path;
    }
}
