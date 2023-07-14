package com.cheatbreaker.mixin.client.renderer.texture;

import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import net.minecraft.client.renderer.texture.HttpTexture;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HttpTexture.class)
public class MixinHttpTexture implements ThreadDownloadImageDataBridge {
}
