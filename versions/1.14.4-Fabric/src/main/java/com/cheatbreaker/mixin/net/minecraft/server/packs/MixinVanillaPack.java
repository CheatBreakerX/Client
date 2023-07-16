package com.cheatbreaker.mixin.net.minecraft.server.packs;

import com.cheatbreaker.bridge.client.resources.DefaultResourcePackBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import net.minecraft.resources.ResourceLocation;
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
    @Shadow protected abstract InputStream getResourceAsStream(PackType packType, ResourceLocation resourceLocation);

    public InputStream bridge$getInputStream(ResourceLocationBridge var1) {
        return this.getResourceAsStream(PackType.CLIENT_RESOURCES, (ResourceLocation) var1);
    }

    public String bridge$getPackName() {
        return this.getName();
    }

    public Optional<BufferedImage> bridge$getPackImage() {
        return DefaultResourcePackBridge.super.bridge$getPackImage();
    }
}
