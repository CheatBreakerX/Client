package com.cheatbreaker.mixin.net.minecraft.client.resources.sound;

import com.cheatbreaker.bridge.client.audio.ISoundBridge;
import net.minecraft.client.resources.sounds.SoundInstance;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SoundInstance.class)
public interface MixinSoundInstance extends ISoundBridge {
}
