package com.cheatbreaker.mixin.net.minecraft.client.sounds;

import com.cheatbreaker.bridge.client.audio.ISoundBridge;
import com.cheatbreaker.bridge.client.audio.SoundHandlerBridge;
import com.cheatbreaker.bridge.client.audio.SoundManagerBridge;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SoundManager.class)
public abstract class MixinSoundManager implements SoundHandlerBridge {
    @Shadow @Final private SoundEngine soundEngine;
    @Shadow public abstract void play(SoundInstance soundInstance);

    public SoundManagerBridge bridge$getSoundManager() {
        return null;
    }

    public void bridge$playSound(ISoundBridge sound) {
        this.play((SoundInstance) sound);
    }
}
