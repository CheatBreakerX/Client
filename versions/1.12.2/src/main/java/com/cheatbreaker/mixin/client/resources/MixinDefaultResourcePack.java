package com.cheatbreaker.mixin.client.resources;

import com.cheatbreaker.bridge.client.resources.DefaultResourcePackBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Mixin(DefaultResourcePack.class)
public abstract class MixinDefaultResourcePack implements DefaultResourcePackBridge {
    @Shadow public abstract InputStream getInputStream(ResourceLocation location) throws IOException;
    @Shadow public abstract String getPackName();
    @Shadow public abstract BufferedImage getPackImage() throws IOException;

    public InputStream bridge$getInputStream(ResourceLocationBridge var1) {
        try {
            return this.getInputStream((ResourceLocation) var1);
        } catch (IOException e) {
            return null;
        }
    }

    public String bridge$getPackName() {
        return this.getPackName();
    }

    public Optional<BufferedImage> bridge$getPackImage() {
        try {
            return Optional.of(this.getPackImage());
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
