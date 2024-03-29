package com.cheatbreaker.mixin.entity;

import com.cheatbreaker.bridge.entity.EntityLivingBaseBridge;
import com.cheatbreaker.bridge.potion.PotionEffectBridge;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends MixinEntity implements EntityLivingBaseBridge {
    @Shadow public abstract Collection<PotionEffect> getActivePotionEffects();
    @Shadow public abstract ItemStack getHeldItem(EnumHand par1);

    public Collection<PotionEffectBridge> bridge$getActivePotionEffects() {
        List<PotionEffectBridge> list = new ArrayList<>();

        for (PotionEffect effect : this.getActivePotionEffects()) {
            list.add((PotionEffectBridge) effect);
        }

        return list;
    }
}
