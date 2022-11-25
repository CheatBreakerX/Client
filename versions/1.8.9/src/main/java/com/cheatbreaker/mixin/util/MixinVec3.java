package com.cheatbreaker.mixin.util;

import com.cheatbreaker.bridge.util.Vec3Bridge;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Vec3.class)
public abstract class MixinVec3 implements Vec3Bridge {
    @Shadow @Final public double xCoord;
    @Shadow @Final public double yCoord;
    @Shadow @Final public double zCoord;
    @Shadow public abstract double lengthVector();
    @Shadow public abstract Vec3 crossProduct(Vec3 vec);
    @Shadow public abstract Vec3 normalize();
    @Shadow public abstract double dotProduct(Vec3 vec);

    public float bridge$getXCoord() {
        return (float) this.xCoord;
    }

    public float bridge$getYCoord() {
        return (float) this.yCoord;
    }

    public float bridge$getZCoord() {
        return (float) this.zCoord;
    }

    public double bridge$lengthVector() {
        return this.lengthVector();
    }

    public Vec3Bridge bridge$crossProduct(Vec3Bridge vec3) {
        return (Vec3Bridge) this.crossProduct((Vec3) vec3);
    }

    public Vec3Bridge bridge$normalize() {
        return (Vec3Bridge) this.normalize();
    }

    public double bridge$dotProduct(Vec3Bridge vec3) {
        return this.dotProduct((Vec3) vec3);
    }
}
