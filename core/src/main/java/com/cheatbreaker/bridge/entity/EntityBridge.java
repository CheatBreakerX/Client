package com.cheatbreaker.bridge.entity;

import com.cheatbreaker.bridge.util.AxisAlignedBBBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;

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
    AxisAlignedBBBridge bridge$getBoundingBox();
    float bridge$getHeight();
    IChatComponentBridge bridge$func_145748_c_();
}
