package com.cheatbreaker.mixin.client.sounds;

import com.cheatbreaker.bridge.client.audio.SoundManagerBridge;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SoundEngine.class)
public abstract class MixinSoundEngine implements SoundManagerBridge {
    public void playSound(String sound, float volume) {

    }


    public void bridge$loadSoundSystem() {

    }
}
