package com.cheatbreaker.mixin.client.settings;

import com.cheatbreaker.bridge.client.settings.KeyBindingBridge;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(KeyBinding.class)
public abstract class MixinKeyBinding implements KeyBindingBridge {
    @Shadow private int keyCode;
    @Shadow public abstract boolean isKeyDown();

    public int bridge$getKeyCode() {
        return this.keyCode;
    }

    public boolean bridge$isDown() {
        return this.isKeyDown();
    }
}
