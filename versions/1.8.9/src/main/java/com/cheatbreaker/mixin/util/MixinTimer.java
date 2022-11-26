package com.cheatbreaker.mixin.util;

import com.cheatbreaker.bridge.util.TimerBridge;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Timer.class)
public class MixinTimer implements TimerBridge {
    @Shadow public float renderPartialTicks;

    public double bridge$getRenderPartialTicks() {
        return this.renderPartialTicks;
    }
}
