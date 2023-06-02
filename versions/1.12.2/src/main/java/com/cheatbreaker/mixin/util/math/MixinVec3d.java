package com.cheatbreaker.mixin.util.math;

import com.cheatbreaker.bridge.util.Vec3Bridge;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Vec3d.class)
public abstract class MixinVec3d implements Vec3Bridge {
    @Shadow public abstract Vec3d crossProduct(Vec3d vec);
    @Shadow public abstract Vec3d normalize();
    @Shadow public abstract double dotProduct(Vec3d vec);
    @Shadow @Final public double x;
    @Shadow @Final public double y;
    @Shadow @Final public double z;

    @Shadow public abstract double length();

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
        return (Vec3Bridge) this.crossProduct((Vec3d) vec3);
    }

    public Vec3Bridge bridge$normalize() {
        return (Vec3Bridge) this.normalize();
    }

    public double bridge$dotProduct(Vec3Bridge vec3) {
        return this.dotProduct((Vec3d) vec3);
    }
}
