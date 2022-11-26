package com.cheatbreaker.mixin.client.audio;

import com.cheatbreaker.bridge.client.audio.ISoundBridge;
import net.minecraft.client.audio.ISound;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ISound.class)
public class MixinISound implements ISoundBridge {
}
