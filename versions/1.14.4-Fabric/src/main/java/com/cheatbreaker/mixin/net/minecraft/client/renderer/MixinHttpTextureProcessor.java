package com.cheatbreaker.mixin.net.minecraft.client.renderer;

import com.cheatbreaker.bridge.client.renderer.IImageBufferBridge;
import com.cheatbreaker.util.Utils;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.HttpTextureProcessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.image.BufferedImage;

@Mixin(HttpTextureProcessor.class)
public interface MixinHttpTextureProcessor extends IImageBufferBridge {
    @Shadow void onTextureDownloaded();
    @Shadow NativeImage process(NativeImage arg);

    default BufferedImage bridge$parseUserSkin(BufferedImage p_78432_1_) {
        return Utils.nativeImageToBufferedImage(this.process(Utils.bufferedImageToNativeImage(p_78432_1_)));
    }

    default void bridge$func_152634_a() {
        this.onTextureDownloaded();
    }
}
