package com.cheatbreaker.mixin.client.audio;

import com.cheatbreaker.CheatBreakerMod;
import com.cheatbreaker.bridge.client.audio.SoundManagerBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.main.utils.Callbacks;
import com.cheatbreaker.main.utils.Debug;
import com.cheatbreaker.main.utils.Reflector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.UUID;

@Mixin(SoundManager.class)
public abstract class MixinSoundManager implements SoundManagerBridge {
    @Shadow private boolean loaded;
    @Shadow protected abstract void loadSoundSystem();

    @Shadow
    private static URL getURLForSoundResource(ResourceLocation p_148612_0_) {
        return null;
    }

    private SoundSystem system;
    public void playSound(String sound, float pitch) {
        if (this.loaded) {
            ResourceLocation resourceLocation = new ResourceLocation("client/sound/" + sound + ".ogg");
            String string2 = UUID.randomUUID().toString();
            this.system.newStreamingSource(false, string2, getURLForSoundResource(resourceLocation),
                    resourceLocation.toString(), false, 0.0f, 0.0f, 0.0f, 0,
                    SoundSystemConfig.getDefaultRolloff());
            this.system.setPitch(string2, 1.0f);
            this.system.setVolume(string2, pitch);
            this.system.play(string2);
        }
    }

    public SoundSystem bridge$getSoundSystem() {
        return this.system;
    }

    private int timesLoaded = 0;
    private boolean cheatBreaker = false;

    @Inject(method = "loadSoundSystem", at = @At("HEAD"), cancellable = true)
    private synchronized void impl$head$loadSoundSystem(CallbackInfo callbackInfo) {
        if (this.timesLoaded > 0 && !this.cheatBreaker) {
            callbackInfo.cancel();
        }
        this.timesLoaded++;
    }

    @Inject(method = "loadSoundSystem", at = @At("RETURN"))
    private synchronized void impl$return$loadSoundSystem(CallbackInfo callbackInfo) {
        Callbacks.trySetValue(this.system, "CheatBreakerX sound system", () -> {
            return Reflector.getFieldValue(
                    (SoundManager) Ref.getMinecraft().bridge$getSoundHandler().bridge$getSoundManager(),
                    "field_148620_e"
            );
        });
    }

    public synchronized void bridge$loadSoundSystem() {
        this.cheatBreaker = true;
        this.loadSoundSystem();
        this.cheatBreaker = false;
    }
}