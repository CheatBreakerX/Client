package com.cheatbreaker.mixin.client;

import com.cheatbreaker.bridge.client.audio.SoundCategoryBridge;
import com.cheatbreaker.bridge.client.settings.GameSettingsBridge;
import com.cheatbreaker.bridge.client.settings.KeyBindingBridge;
import com.cheatbreaker.main.CheatBreaker;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Options.class)
public abstract class MixinOptions implements GameSettingsBridge {
    @Shadow public boolean renderDebug;
    @Shadow public int guiScale;
    @Shadow public KeyMapping[] keyMappings;
    @Shadow public int thirdPersonView;
    @Shadow public int renderDistance;
    @Shadow @Final public KeyMapping keyJump;
    @Shadow @Final public KeyMapping keyUp;
    @Shadow @Final public KeyMapping keyLeft;
    @Shadow @Final public KeyMapping keyRight;
    @Shadow @Final public KeyMapping keyDown;
    @Shadow @Final public KeyMapping keyAttack;
    @Shadow @Final public KeyMapping keyUse;
    @Shadow public abstract float getSoundSourceVolume(SoundSource category);
    @Shadow public abstract void setSoundCategoryVolume(SoundSource category, float volume);

    public boolean bridge$getShowDebugInfo() {
        return this.renderDebug;
    }

    public int bridge$getGuiScale() {
        return this.guiScale;
    }

    public KeyBindingBridge[] bridge$getKeyBindings() {
        return (KeyBindingBridge[]) this.keyMappings;
    }

    public void bridge$setKeyBindings(KeyBindingBridge[] keyBindings) {
        //this.keyMappings = (KeyMapping[]) keyBindings;
    }

    public int bridge$getThirdPersonView() {
        return this.thirdPersonView;
    }

    public int bridge$getRenderDistanceChunks() {
        return this.renderDistance;
    }

    public KeyBindingBridge bridge$getKeyBindJump() {
        return (KeyBindingBridge) this.keyJump;
    }

    public KeyBindingBridge bridge$getKeyBindForward() {
        return (KeyBindingBridge) this.keyUp;
    }

    public KeyBindingBridge bridge$getKeyBindLeft() {
        return (KeyBindingBridge) this.keyLeft;
    }

    public KeyBindingBridge bridge$getKeyBindRight() {
        return (KeyBindingBridge) this.keyRight;
    }

    public KeyBindingBridge bridge$getKeyBindBack() {
        return (KeyBindingBridge) this.keyDown;
    }

    public KeyBindingBridge bridge$getKeyBindAttack() {
        return (KeyBindingBridge) this.keyAttack;
    }

    public KeyBindingBridge bridge$getKeyBindUseItem() {
        return (KeyBindingBridge) this.keyUse;
    }

    public float bridge$getSoundLevel(SoundCategoryBridge category) {
        SoundSource asMc = null;

        for (SoundSource nativeCategory : SoundSource.values()) {
            if (nativeCategory.getName().equals(category.bridge$getCategoryName())) {
                asMc = nativeCategory;
            }
        }

        if (asMc != null) {
            return this.getSoundSourceVolume(asMc);
        } else {
            CheatBreaker.LOGGER.warn("Unable to get sound level of '{}' category.", category.bridge$getCategoryName());
            return 0f;
        }
    }

    public void bridge$setSoundLevel(SoundCategoryBridge category, float level) {
        SoundSource asMc = null;

        for (SoundSource nativeCategory : SoundSource.values()) {
            if (nativeCategory.getName().equals(category.bridge$getCategoryName())) {
                asMc = nativeCategory;
            }
        }

        if (asMc != null) {
            this.setSoundCategoryVolume(asMc, level);
        } else {
            CheatBreaker.LOGGER.warn("Unable to set sound level of '{}' category.", category.bridge$getCategoryName());
        }
    }
}
