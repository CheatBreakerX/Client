package com.cheatbreaker.mixin.potion;

import com.cheatbreaker.bridge.potion.PotionBridge;
import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Potion.class)
public abstract class MixinPotion implements PotionBridge {
    @Shadow public abstract int getStatusIconIndex();
    @Shadow public abstract boolean hasStatusIcon();

    public int bridge$getStatusIconIndex() {
        return this.getStatusIconIndex();
    }

    public boolean bridge$hasStatusIcon() {
        return this.hasStatusIcon();
    }
}
