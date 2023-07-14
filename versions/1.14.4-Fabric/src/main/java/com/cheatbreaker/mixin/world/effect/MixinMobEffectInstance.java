package com.cheatbreaker.mixin.world.effect;

import com.cheatbreaker.bridge.potion.PotionBridge;
import com.cheatbreaker.bridge.potion.PotionEffectBridge;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobEffectInstance.class)
public abstract class MixinMobEffectInstance implements PotionEffectBridge {
    @Shadow private int duration;
    @Shadow @Final private MobEffect effect;
    @Shadow private int amplifier;
    @Shadow public abstract int getDuration();

    public int bridge$getDuration() {
        return this.duration;
    }

    public String bridge$getEffectName() {
        return this.effect.getDisplayName().getColoredString();
    }

    public int bridge$getAmplifier() {
        return this.amplifier;
    }

    public int bridge$getPotionID() {
        return Registry.MOB_EFFECT.getId(this.effect);
    }

    public String bridge$getDurationString() {
        return MobEffectUtil.formatDuration((MobEffectInstance) ((PotionEffectBridge) this), this.getDuration());
    }

    public PotionBridge bridge$toPotionType() {
        return (PotionBridge) this.effect;
    }
}
