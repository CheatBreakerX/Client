package com.cheatbreaker.mixin.client.renderer;

import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ThreadDownloadImageData.class)
public class MixinThreadDownloadImageData implements ThreadDownloadImageDataBridge {
}
