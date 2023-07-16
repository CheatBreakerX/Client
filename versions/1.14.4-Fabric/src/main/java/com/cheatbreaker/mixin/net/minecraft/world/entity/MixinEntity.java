package com.cheatbreaker.mixin.net.minecraft.world.entity;

import com.cheatbreaker.bridge.entity.EntityBridge;
import com.cheatbreaker.bridge.util.AxisAlignedBBBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@Mixin(Entity.class)
public abstract class MixinEntity implements EntityBridge {
    @Shadow public double x;
    @Shadow public double y;
    @Shadow public double z;
    @Shadow public abstract UUID getUUID();
    @Shadow public abstract Component getName();
    @Shadow public double xOld;
    @Shadow public double yOld;
    @Shadow public double zOld;
    @Shadow public abstract Vec2 getRotationVector();
    @Shadow public abstract AABB getBoundingBox();
    @Shadow public abstract float getBbHeight();

    @Shadow public abstract Component getDisplayName();

    public double bridge$getPosX() {
        return this.x;
    }

    public double bridge$getPosY() {
        return this.y;
    }

    public double bridge$getPosZ() {
        return this.z;
    }

    public UUID bridge$getUniqueID() {
        return this.getUUID();
    }

    public String bridge$getCommandSenderName() {
        return this.getName().getColoredString();
    }

    public double bridge$getLastTickPosX() {
        return this.xOld;
    }

    public double bridge$getLastTickPosY() {
        return this.yOld;
    }

    public double bridge$getLastTickPosZ() {
        return this.zOld;
    }

    public float bridge$getRotationPitch() {
        return this.getRotationVector().x;
    }

    public float bridge$getRotationYaw() {
        return this.getRotationVector().y;
    }

    public AxisAlignedBBBridge bridge$getBoundingBox() {
        return (AxisAlignedBBBridge) this.getBoundingBox();
    }

    public float bridge$getHeight() {
        return this.getBbHeight();
    }

    public IChatComponentBridge bridge$func_145748_c_() {
        return (IChatComponentBridge) this.getDisplayName();
    }
}
