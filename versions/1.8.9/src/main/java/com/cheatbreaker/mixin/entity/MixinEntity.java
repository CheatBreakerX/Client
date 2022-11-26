package com.cheatbreaker.mixin.entity;

import com.cheatbreaker.bridge.entity.EntityBridge;
import com.cheatbreaker.bridge.util.AxisAlignedBBBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@Mixin(Entity.class)
public abstract class MixinEntity implements EntityBridge {
    @Shadow public double posX;
    @Shadow public double posY;
    @Shadow public double posZ;
    @Shadow public abstract UUID getUniqueID();
    @Shadow public abstract Entity getCommandSenderEntity();
    @Shadow public double lastTickPosX;
    @Shadow public double lastTickPosY;
    @Shadow public double lastTickPosZ;
    @Shadow public float rotationPitch;
    @Shadow public float rotationYaw;
    @Shadow private AxisAlignedBB boundingBox;
    @Shadow public float height;
    @Shadow public abstract IChatComponent getDisplayName();

    public double bridge$getPosX() {
        return this.posX;
    }

    public double bridge$getPosY() {
        return this.posY;
    }

    public double bridge$getPosZ() {
        return this.posZ;
    }

    public UUID bridge$getUniqueID() {
        return this.getUniqueID();
    }

    public String bridge$getCommandSenderName() {
        return this.getCommandSenderEntity().getName();
    }

    public double bridge$getLastTickPosX() {
        return this.lastTickPosX;
    }

    public double bridge$getLastTickPosY() {
        return this.lastTickPosY;
    }

    public double bridge$getLastTickPosZ() {
        return this.lastTickPosZ;
    }

    public float bridge$getRotationPitch() {
        return this.rotationPitch;
    }

    public float bridge$getRotationYaw() {
        return this.rotationYaw;
    }

    public AxisAlignedBBBridge bridge$getBoundingBox() {
        return (AxisAlignedBBBridge) this.boundingBox;
    }

    public float bridge$getHeight() {
        return this.height;
    }

    public IChatComponentBridge bridge$func_145748_c_() {
        return (IChatComponentBridge) this.getDisplayName();
    }
}
