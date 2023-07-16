package com.cheatbreaker.mixin.net.minecraft.client;

import com.cheatbreaker.bridge.client.settings.KeyBindingBridge;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(KeyMapping.class)
public abstract class MixinKeyMapping implements KeyBindingBridge {
    @Shadow private InputConstants.Key key;
    @Shadow public abstract boolean isDown();

    public int bridge$getKeyCode() {
        return this.key.getValue();
    }

    public boolean bridge$isDown() {
        return this.isDown();
    }
}
