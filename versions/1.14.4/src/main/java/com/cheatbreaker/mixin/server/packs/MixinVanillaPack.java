package com.cheatbreaker.mixin.server.packs;

import com.cheatbreaker.bridge.client.resources.DefaultResourcePackBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.VanillaPack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Optional;

@Mixin(VanillaPack.class)
public abstract class MixinVanillaPack implements DefaultResourcePackBridge {
    @Shadow public abstract String getName();
    @Shadow protected abstract InputStream getExtraInputStream(PackType type, String resource);

    public InputStream bridge$getInputStream(ResourceLocationBridge var1) {
        return this.getExtraInputStream(PackType.CLIENT_RESOURCES, var1.bridge$getResourcePath());
    }

    public String bridge$getPackName() {
        return this.getName();
    }

    public Optional<BufferedImage> bridge$getPackImage() {
        return DefaultResourcePackBridge.super.bridge$getPackImage();
    }
}
