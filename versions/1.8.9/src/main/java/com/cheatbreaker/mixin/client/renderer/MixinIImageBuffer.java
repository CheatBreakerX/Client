package com.cheatbreaker.mixin.client.renderer;

import com.cheatbreaker.bridge.client.renderer.IImageBufferBridge;
import net.minecraft.client.renderer.IImageBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.image.BufferedImage;

@Mixin(IImageBuffer.class)
public interface MixinIImageBuffer extends IImageBufferBridge {
    @Shadow BufferedImage parseUserSkin(BufferedImage image);
    @Shadow void skinAvailable();

    default BufferedImage bridge$parseUserSkin(BufferedImage p_78432_1_) {
        return this.parseUserSkin(p_78432_1_);
    }

    default void bridge$func_152634_a() {
        this.skinAvailable();
    }
}
