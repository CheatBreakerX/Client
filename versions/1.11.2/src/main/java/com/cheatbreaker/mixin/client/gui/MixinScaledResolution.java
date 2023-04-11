package com.cheatbreaker.mixin.client.gui;

import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ScaledResolution.class)
public abstract class MixinScaledResolution implements ScaledResolutionBridge {
    @Shadow public abstract int getScaledWidth();
    @Shadow public abstract int getScaledHeight();
    @Shadow public abstract double getScaledHeight_double();
    @Shadow public abstract double getScaledWidth_double();
    @Shadow public abstract int getScaleFactor();

    public float bridge$getScaledWidth() {
        return this.getScaledWidth();
    }

    public float bridge$getScaledHeight() {
        return this.getScaledHeight();
    }

    public double bridge$getScaledWidth_double() {
        return this.getScaledWidth_double();
    }

    public double bridge$getScaledHeight_double() {
        return this.getScaledHeight_double();
    }

    public int bridge$getScaleFactor() {
        return this.getScaleFactor();
    }
}
