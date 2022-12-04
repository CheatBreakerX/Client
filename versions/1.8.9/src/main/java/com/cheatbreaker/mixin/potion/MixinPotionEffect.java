package com.cheatbreaker.mixin.potion;

import com.cheatbreaker.bridge.potion.PotionBridge;
import com.cheatbreaker.bridge.potion.PotionEffectBridge;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PotionEffect.class)
public abstract class MixinPotionEffect implements PotionEffectBridge {
    @Shadow public abstract int getDuration();

    @Shadow public abstract String getEffectName();

    @Shadow private int amplifier;

    @Shadow private int potionID;

    public int bridge$getDuration() {
        return this.getDuration();
    }

    public String bridge$getEffectName() {
        return this.getEffectName();
    }

    public int bridge$getAmplifier() {
        return this.amplifier;
    }

    public int bridge$getPotionID() {
        return this.potionID;
    }

    public String bridge$getDurationString() {
        return Potion.getDurationString((PotionEffect) ((PotionEffectBridge) this));
    }

    public PotionBridge bridge$toPotionType() {
        return (PotionBridge) Potion.potionTypes[this.bridge$getPotionID()];
    }
}
