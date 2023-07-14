package com.cheatbreaker.mixin.client;

import com.cheatbreaker.bridge.util.TimerBridge;
import net.minecraft.client.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Timer.class)
public class MixinTimer implements TimerBridge {
    @Shadow public float tickDelta;

    public double bridge$getRenderPartialTicks() {
        return this.tickDelta;
    }
}
