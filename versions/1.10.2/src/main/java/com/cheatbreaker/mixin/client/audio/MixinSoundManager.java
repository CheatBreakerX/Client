package com.cheatbreaker.mixin.client.audio;

import com.cheatbreaker.bridge.client.audio.SoundManagerBridge;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;

import java.net.URL;
import java.util.UUID;

@Mixin(SoundManager.class)
public abstract class MixinSoundManager implements SoundManagerBridge {
    @Shadow private SoundManager.SoundSystemStarterThread sndSystem;
    @Shadow private boolean loaded;

    @Shadow
    private static URL getURLForSoundResource(ResourceLocation p_148612_0_) {
        return null;
    }

    @Shadow protected abstract void loadSoundSystem();

    public void playSound(String sound, float pitch) {
        if (this.loaded) {
            ResourceLocation resourceLocation = new ResourceLocation("client/sound/" + sound + ".ogg");
            String string2 = UUID.randomUUID().toString();
            this.sndSystem.newStreamingSource(false, string2, getURLForSoundResource(resourceLocation),
                    resourceLocation.toString(), false, 0.0f, 0.0f, 0.0f, 0,
                    SoundSystemConfig.getDefaultRolloff());
            this.sndSystem.setPitch(string2, 1.0f);
            this.sndSystem.setVolume(string2, pitch);
            this.sndSystem.play(string2);
        }
    }

    public SoundSystem bridge$getSoundSystem() {
        return this.sndSystem;
    }

    int timesLoaded = 0;
    boolean cheatBreaker = false;

    @Inject(method = "loadSoundSystem", at = @At("HEAD"), cancellable = true)
    private synchronized void impl$loadSoundSystem(CallbackInfo callbackInfo) {
        if (timesLoaded > 0 && !cheatBreaker) {
            callbackInfo.cancel();
        }
        timesLoaded++;
    }

    public synchronized void bridge$loadSoundSystem() {
        this.cheatBreaker = true;
        this.loadSoundSystem();
        this.cheatBreaker = false;
    }
}
