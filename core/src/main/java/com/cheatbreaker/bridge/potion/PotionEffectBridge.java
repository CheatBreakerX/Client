package com.cheatbreaker.bridge.potion;

public interface PotionEffectBridge {
    int bridge$getDuration();
    String bridge$getEffectName();
    int bridge$getAmplifier();
    int bridge$getPotionID();
    String bridge$getDurationString();
    PotionBridge bridge$toPotionType();
}
