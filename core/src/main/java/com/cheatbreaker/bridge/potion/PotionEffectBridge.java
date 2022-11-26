package com.cheatbreaker.bridge.potion;

public interface PotionEffectBridge {
    default String bridge$getDurationBridge() {
        return this.bridge$getDuration() + "";
    }
    int bridge$getDuration();
    String bridge$getEffectName();
    int bridge$getAmplifier();
    int bridge$getPotionID();
    String bridge$getDurationString();
    PotionBridge bridge$toPotionType();
}
