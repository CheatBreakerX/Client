package com.cheatbreaker.mixin.potion;

import com.cheatbreaker.bridge.potion.PotionBridge;
import com.cheatbreaker.bridge.potion.PotionEffectBridge;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PotionEffect.class)
public abstract class MixinPotionEffect implements PotionEffectBridge {
    @Shadow public abstract int getDuration();
    @Shadow public abstract String getEffectName();
    @Shadow private int amplifier;

    @Shadow @Final private Potion potion;

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
        return Potion.getIdFromPotion(this.potion);
    }

    public String bridge$getDurationString() {
        return Potion.getPotionDurationString((PotionEffect) ((PotionEffectBridge) this), this.getDuration());
    }

    public PotionBridge bridge$toPotionType() {
        return (PotionBridge) Potion.REGISTRY.getObjectById(this.bridge$getPotionID());
    }
}
