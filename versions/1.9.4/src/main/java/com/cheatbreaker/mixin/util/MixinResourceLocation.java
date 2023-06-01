package com.cheatbreaker.mixin.util;

import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ResourceLocation.class)
public class MixinResourceLocation implements ResourceLocationBridge {
    @Shadow @Final protected String resourceDomain;
    @Shadow @Final protected String resourcePath;

    public String bridge$getResourceDomain() {
        return this.resourceDomain;
    }

    public String bridge$getResourcePath() {
        return this.resourcePath;
    }
}
