package com.cheatbreaker.mixin.client.renderer;

import com.cheatbreaker.bridge.client.renderer.IImageBufferBridge;
import net.minecraft.client.renderer.IImageBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.image.BufferedImage;

@Mixin(IImageBuffer.class)
public abstract class MixinIImageBuffer implements IImageBufferBridge {
    @Shadow public abstract BufferedImage parseUserSkin(BufferedImage image);
    @Shadow public abstract void skinAvailable();

    public BufferedImage bridge$parseUserSkin(BufferedImage p_78432_1_) {
        return this.parseUserSkin(p_78432_1_);
    }

    public void bridge$func_152634_a() {
        this.skinAvailable();
    }
}
