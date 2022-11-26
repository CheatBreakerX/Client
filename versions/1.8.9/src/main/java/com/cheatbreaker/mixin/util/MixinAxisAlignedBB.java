package com.cheatbreaker.mixin.util;

import com.cheatbreaker.bridge.util.AxisAlignedBBBridge;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AxisAlignedBB.class)
public class MixinAxisAlignedBB implements AxisAlignedBBBridge {
    @Shadow @Final public double minX;
    @Shadow @Final public double minY;
    @Shadow @Final public double minZ;
    @Shadow @Final public double maxX;
    @Shadow @Final public double maxY;
    @Shadow @Final public double maxZ;

    public double bridge$getMinX() {
        return this.minX;
    }

    public double bridge$getMinY() {
        return this.minY;
    }

    public double bridge$getMinZ() {
        return this.minZ;
    }

    public double bridge$getMaxX() {
        return this.maxX;
    }

    public double bridge$getMaxY() {
        return this.maxY;
    }

    public double bridge$getMaxZ() {
        return this.maxZ;
    }
}
