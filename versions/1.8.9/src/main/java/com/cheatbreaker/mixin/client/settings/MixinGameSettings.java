package com.cheatbreaker.mixin.client.settings;

import com.cheatbreaker.bridge.client.audio.SoundCategoryBridge;
import com.cheatbreaker.bridge.client.settings.GameSettingsBridge;
import com.cheatbreaker.bridge.client.settings.KeyBindingBridge;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameSettings.class)
public abstract class MixinGameSettings implements GameSettingsBridge {
    @Shadow public boolean showDebugInfo;
    @Shadow public int guiScale;
    @Shadow public KeyBinding keyBindInventory;
    @Shadow public static String getKeyDisplayString(int key) { return null; }
    @Shadow public KeyBinding[] keyBindings;
    @Shadow public int thirdPersonView;
    @Shadow public int renderDistanceChunks;
    @Shadow public KeyBinding keyBindJump;
    @Shadow public KeyBinding keyBindForward;
    @Shadow public KeyBinding keyBindLeft;
    @Shadow public KeyBinding keyBindRight;
    @Shadow public KeyBinding keyBindBack;
    @Shadow public KeyBinding keyBindAttack;
    @Shadow public KeyBinding keyBindUseItem;
    @Shadow public abstract float getSoundLevel(SoundCategory sndCategory);

    @Shadow public abstract void setSoundLevel(SoundCategory sndCategory, float soundLevel);

    public boolean bridge$getShowDebugInfo() {
        return this.showDebugInfo;
    }

    public int bridge$getGuiScale() {
        return this.guiScale;
    }

    public KeyBindingBridge[] bridge$getKeyBindings() {
        return (KeyBindingBridge[]) this.keyBindings;
    }

    public void bridge$setKeyBindings(KeyBindingBridge[] keyBindings) {
        this.keyBindings = (KeyBinding[]) keyBindings;
    }

    public int bridge$getThirdPersonView() {
        return this.thirdPersonView;
    }

    public int bridge$getRenderDistanceChunks() {
        return this.renderDistanceChunks;
    }

    public KeyBindingBridge bridge$getKeyBindJump() {
        return (KeyBindingBridge) this.keyBindJump;
    }

    public KeyBindingBridge bridge$getKeyBindForward() {
        return (KeyBindingBridge) this.keyBindForward;
    }

    public KeyBindingBridge bridge$getKeyBindLeft() {
        return (KeyBindingBridge) this.keyBindLeft;
    }

    public KeyBindingBridge bridge$getKeyBindRight() {
        return (KeyBindingBridge) this.keyBindRight;
    }

    public KeyBindingBridge bridge$getKeyBindBack() {
        return (KeyBindingBridge) this.keyBindBack;
    }

    public KeyBindingBridge bridge$getKeyBindAttack() {
        return (KeyBindingBridge) this.keyBindAttack;
    }

    public KeyBindingBridge bridge$getKeyBindUseItem() {
        return (KeyBindingBridge) this.keyBindUseItem;
    }

    public float bridge$getSoundLevel(SoundCategoryBridge category) {
        SoundCategory asMc = null;

        for (SoundCategory category1 : SoundCategory.values()) {
            if (category1.getCategoryId() == category.bridge$getCategoryId()) {
                asMc = category1;
            }
        }

        return this.getSoundLevel(asMc);
    }

    public void bridge$setSoundLevel(SoundCategoryBridge category, float level) {
        SoundCategory asMc = null;

        for (SoundCategory category1 : SoundCategory.values()) {
            if (category1.getCategoryId() == category.bridge$getCategoryId()) {
                asMc = category1;
            }
        }

        this.setSoundLevel(asMc, level);
    }
}
