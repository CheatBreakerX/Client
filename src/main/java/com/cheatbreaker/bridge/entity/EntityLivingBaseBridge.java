package com.cheatbreaker.bridge.entity;

import com.cheatbreaker.bridge.potion.PotionEffectBridge;

import java.util.Collection;

public interface EntityLivingBaseBridge extends EntityBridge {
    Collection<PotionEffectBridge> bridge$getActivePotionEffects();
}
