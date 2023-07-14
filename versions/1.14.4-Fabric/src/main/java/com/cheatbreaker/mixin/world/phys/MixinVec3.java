package com.cheatbreaker.mixin.world.phys;

import com.cheatbreaker.bridge.util.Vec3Bridge;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Vec3.class)
public abstract class MixinVec3 implements Vec3Bridge {
    @Shadow @Final public double x;
    @Shadow @Final public double y;
    @Shadow @Final public double z;
    @Shadow public abstract double length();
    @Shadow public abstract Vec3 cross(Vec3 vec);
    @Shadow public abstract Vec3 normalize();
    @Shadow public abstract double dot(Vec3 vec);

    public float bridge$getXCoord() {
        return (float) this.x;
    }

    public float bridge$getYCoord() {
        return (float) this.y;
    }

    public float bridge$getZCoord() {
        return (float) this.z;
    }

    public double bridge$lengthVector() {
        return this.length();
    }

    public Vec3Bridge bridge$crossProduct(Vec3Bridge vec3) {
        return (Vec3Bridge) this.cross((Vec3) vec3);
    }

    public Vec3Bridge bridge$normalize() {
        return (Vec3Bridge) this.normalize();
    }

    public double bridge$dotProduct(Vec3Bridge vec3) {
        return this.dot((Vec3) vec3);
    }
}
