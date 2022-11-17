package com.cheatbreaker.bridge.entity;

import java.util.UUID;

public interface EntityBridge {
    double bridge$getPosX();
    double bridge$getPosY();
    double bridge$getPosZ();
    UUID bridge$getUniqueID();
    String bridge$getCommandSenderName();

    double bridge$getLastTickPosX();
    double bridge$getLastTickPosY();
    double bridge$getLastTickPosZ();

    float bridge$getRotationPitch();
    float bridge$getRotationYaw();
}
